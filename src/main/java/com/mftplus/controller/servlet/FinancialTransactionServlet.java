package com.mftplus.controller.servlet;

import com.mftplus.model.*;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import com.mftplus.service.impl.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/financialTransaction.do")
public class FinancialTransactionServlet extends HttpServlet {
    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private DepartmentServiceImp departmentService;

    @Inject
    private FinancialTransaction financialTransaction;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String username = req.getParameter("username");
            Optional<User> userOptional = userService.findByUsername(username);
            Long id = Long.valueOf(req.getParameter("dId"));
            Optional<Department> departmentOptional = departmentService.findById(id);

            if (userOptional.isPresent() && departmentOptional.isPresent()) {
                String faDate = req.getParameter("date").replace("/", "-");
                Long amount = Long.valueOf(req.getParameter("amount"));
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType))
                        .amount(amount)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
                resp.sendRedirect("/financialTransaction.do");
            } else {
                log.info("Invalid Information");
                resp.sendRedirect("/financialTransaction.do");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("paymentTypes", Arrays.asList(PaymentType.values()));
            req.getSession().setAttribute("transactionTypes", Arrays.asList(FinancialTransactionType.values()));
            req.getSession().setAttribute("userList", userService.findAll());
            req.getSession().setAttribute("departmentList", departmentService.findAll());
            req.getSession().setAttribute("financialTransactionList", financialTransactionService.findAll());
            req.getRequestDispatcher("/jsp/financialTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            financialTransactionService.removeById(id);

            log.info("FinancialTransactionServlet - FinancialTransaction Removed");
            resp.sendRedirect("/financialTransaction.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}