package com.mftplus.controller.servlet;

import com.mftplus.model.*;
import com.mftplus.service.impl.BankServiceImpl;
import com.mftplus.service.impl.CardPaymentServiceImp;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@WebServlet(name = "cardPaymentServlet", urlPatterns = "/cardPayment.do")
public class CardPaymentServlet extends HttpServlet {

    @Inject
    private CardPaymentServiceImp cardPaymentService;

    @Inject
    private CardPayment cardPayment;

    @Inject
    private BankServiceImpl bankService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String depositCode=req.getParameter("depositCode");
            long amount= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");
            Optional<Bank> bank=bankService.findByAccountNumber(req.getParameter("accountNumber"));

            if (bank.isPresent()) {
                cardPayment = CardPayment
                        .builder()
                        .depositCode(depositCode)
                        .bankInvolved(bank.get())
                        .amount(amount)
                        .faDateTime(LocalDateTime.parse(faDateTime2))
                        .deleted(false)
                        .build();

                cardPaymentService.save(cardPayment);
                log.info("CardPaymentServlet - CardPayment Saved");
                resp.sendRedirect("/cardPayment.do");
            }
            else {
                log.info("Invalid Bank");
                resp.sendRedirect("/cardPayment.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String depositCode=req.getParameter("depositCode");
            long amount= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");
            Optional<Bank> bank=bankService.findByAccountNumber(req.getParameter("accountNumber"));

            if (bank.isPresent()) {
                cardPayment = CardPayment
                        .builder()
                        .depositCode(depositCode)
                        .bankInvolved(bank.get())
                        .amount(amount)
                        .faDateTime(LocalDateTime.parse(faDateTime2))
                        .deleted(false)
                        .build();

                cardPaymentService.save(cardPayment);
                log.info("CardPaymentServlet - CardPayment Saved");
                resp.sendRedirect("/cardPayment.do");
            }
            else {
                log.info("Invalid Bank");
                resp.sendRedirect("/cardPayment.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("cardPaymentList", cardPaymentService.findAll());
            req.getRequestDispatcher("/jsp/cardPayment.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            cardPaymentService.removeById(id);

            log.info("CardPaymentServlet - Card Payment Removed");
            resp.sendRedirect("/cardPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
