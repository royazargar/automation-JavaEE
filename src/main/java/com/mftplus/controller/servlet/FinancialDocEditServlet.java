package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.*;
import com.mftplus.service.impl.FinancialDocServiceImpl;
import com.mftplus.service.impl.FinancialTransactionServiceImpl;
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
@WebServlet(urlPatterns = "/financialDocEdit.do")
public class FinancialDocEditServlet extends HttpServlet {

    @Inject
    private FinancialDocServiceImpl financialDocService;

    @Inject
    private FinancialDoc financialDoc;

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("id") == null) {
                resp.sendRedirect("/financialDoc.do");
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                Optional<FinancialDoc> financialDoc = financialDocService.findById(id);
                if (financialDoc.isPresent()) {
                    req.getSession().setAttribute("financialDocEdit", financialDoc.get());
                }
                req.getSession().setAttribute("financialTransaction", financialTransactionService.findAll());
                req.getRequestDispatcher("/jsp/editFinancialDoc.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Integer.parseInt(req.getParameter("id"));
            long docNumber = Long.parseLong((req.getParameter("docNumber")));
            String faDate = req.getParameter("date").replace("/", "-");
            String description = req.getParameter("description");
            Long fId = Long.valueOf(req.getParameter("fId"));
            Optional<FinancialTransaction> financialTransaction = financialTransactionService.findById(fId);

            if (financialTransaction.isPresent()) {
                financialDoc = FinancialDoc
                        .builder()
                        .id(id)
                        .docNumber(docNumber)
                        .faDate(faDate)
                        .description(description)
                        .financialTransaction(financialTransaction.get())
                        .deleted(false)
                        .build();
                financialDoc.setFaDate(faDate);

                //validate
                BeanValidator<FinancialDoc> validator = new BeanValidator<>();

                if (validator.validate(financialDoc) != null) {
                    resp.setStatus(500);
                    resp.getWriter().write(validator.validate(financialDoc).toString());
                }

                financialDocService.edit(financialDoc);
                log.info("FinancialDocEditServlet - FinancialDoc Edited");
                resp.setStatus(200);
            } else {
                throw new NoContentException("The required financialTransaction does not exist !");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
