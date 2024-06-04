package com.mftplus.controller.servlet;

import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.Bank;
import com.mftplus.model.enums.AccountType;
import com.mftplus.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/bank.do")
@Slf4j
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 10 MB
)
public class BankServlet extends HttpServlet {

    @Inject
    private BankServiceImpl bankService;

    @Valid
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("BankServlet - Post");
        try {
            req.setCharacterEncoding("utf-8");
            String name = req.getParameter("name");
            String accountNumber = req.getParameter("accountNumber");
            int branchCode = Integer.parseInt(req.getParameter("branchCode"));
            String branchName = req.getParameter("branchName");
            String accountType = req.getParameter("accountType");
            long accountBalance = Long.parseLong(req.getParameter("accountBalance"));
            System.out.println(name+" " + branchName+ " "+accountNumber);
            Bank bank =
                    Bank
                            .builder()
                            .name(name)
                            .accountNumber(accountNumber)
                            .branchCode(branchCode)
                            .branchName(branchName)
                            .accountType(AccountType.valueOf(accountType))
                            .accountBalance(accountBalance)
                            .deleted(false)
                            .build();

            //validate
            BeanValidator<Bank> validator = new BeanValidator<>();

            if (validator.validate(bank) != null) {
                resp.setStatus(500);
                resp.getWriter().write(validator.validate(bank).toString());
            }

            bankService.save(bank);
            log.info("BankServlet - Bank Saved");
            req.getSession().setAttribute("bankList", bankService.findAll());
            resp.sendRedirect("/bank.do?id=" + bank.getId());
            String msg = "بانک با موفقیت ثبت شد !";
            req.getSession().setAttribute("ok", msg);

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("BankServlet - Get");
        try {
            req.getSession().setAttribute("accessTypes", Arrays.asList(AccountType.values()));
            req.getSession().setAttribute("bankList", bankService.findAll());
            req.getRequestDispatcher("/jsp/form/save/bank-form.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}