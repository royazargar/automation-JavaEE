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
    private BankServiceImpl bankService;

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private DepartmentServiceImp departmentService;

    @Inject
    private FinancialTransaction financialTransaction;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            Optional<User> userOptional = userService.findByUsername(username);
            Long id = Long.valueOf(req.getParameter("id"));
            Optional<Department> departmentOptional = departmentService.findById(id);

            if (userOptional.isPresent() && departmentOptional.isPresent()) {
                String faDateTime = req.getParameter("dateTime");
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
                        .faDateTime(faDateTime)
                        .deleted(false)
                        .build();

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
                resp.sendRedirect("/financialTransaction.do");
            } else {
                log.info("Invalid Information");
                resp.sendRedirect("/financialTransaction.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Optional<CashDesk> cashDesk = cashDeskService.findByCashDeskNumber(Integer.parseInt(req.getParameter("cashDeskNumber")));
            Optional<Bank> bank = bankService.findByAccountNumber(req.getParameter("accountNumber"));
            Optional<User> user = userService.findByUsername(req.getParameter("username"));

            if (cashDesk.isPresent() && bank.isPresent() && user.isPresent()) {
                String faDateTime1 = req.getParameter("faDateTime");
                Long amount1 = Long.valueOf(req.getParameter("amount"));
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(user.get())
                        .paymentType(PaymentType.valueOf(paymentType))
                        .amount(amount1)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDateTime(faDateTime1)
                        .deleted(false)
                        .build();

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
                resp.sendRedirect("/financialTransaction.do");
            } else {
                log.info("Invalid Information");
                resp.sendRedirect("/financialTransaction.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
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
