package com.mftplus.controller.servlet;

import com.mftplus.model.Bank;
import com.mftplus.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/bankEdit.do")
public class BankEditServlet extends HttpServlet {
    @Inject
    private BankServiceImpl bankService;

    @Inject
    private Bank bank;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if(req.getParameter("id")==null){
                resp.sendRedirect("/bank.do");
            }else {
                Long id= Long.valueOf(req.getParameter("id"));
                Optional<Bank> bank=bankService.findById(id);
                req.getSession().setAttribute("bankEdit",bank.get());
                req.getRequestDispatcher("/jsp/editBank.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id= Long.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            String accountNumber = req.getParameter("accountNumber");
            int branchCode = Integer.parseInt(req.getParameter("branchCode"));
            String branchName = req.getParameter("branchName");
            String accountType = req.getParameter("accountType");
            long accountBalance = Long.parseLong(req.getParameter("accountBalance"));

            bank = Bank
                    .builder()
                    .id(id)
                    .name(name)
                    .accountNumber(accountNumber)
                    .branchCode(branchCode)
                    .branchName(branchName)
                    .accountType(accountType)
                    .accountBalance(accountBalance)
                    .deleted(false)
                    .build();

            bankService.edit(bank);
            resp.sendRedirect("/bank.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
