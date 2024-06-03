package com.mftplus.controller.servlet;

import com.mftplus.model.*;
import com.mftplus.service.impl.CheckPaymentServiceImp;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "/checkPaymentServlet", urlPatterns = "/checkPayment.do")
public class CheckPaymentServlet extends HttpServlet {

    @Inject
    private CheckPaymentServiceImp checkPaymentService;

    @Inject
    private CheckPayment checkPayment;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String checkNumber=req.getParameter("checkNumber");
            Long amount= Long.parseLong((req.getParameter("amount")));
            String faCheckDueDate=req.getParameter("checkDueDate").replace("/", "-");


                checkPayment = CheckPayment
                        .builder()
                        .checkNumber(checkNumber)
                        .amount(amount)
                        .faCheckDueDate(faCheckDueDate)
                        .deleted(false)
                        .build();
                checkPayment.setFaCheckDueDate(faCheckDueDate);

                checkPaymentService.save(checkPayment);
                log.info("CheckPaymentServlet - CheckPayment Save");
                resp.sendRedirect("/checkPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("checkPaymentList", checkPaymentService.findAll());
            req.getRequestDispatcher("/jsp/checkPayment.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            checkPaymentService.removeById(id);

            log.info("CheckPaymentServlet - CheckPayment Edited");
            resp.sendRedirect("/checkPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
