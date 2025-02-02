package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.FinancialDoc;
import com.mftplus.model.FinancialTransaction;
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
@WebServlet(name = "financialDocServlet", urlPatterns = "/financialDoc.do")
public class FinancialDocServlet extends HttpServlet {

    @Inject
    private FinancialDocServiceImpl financialDocService;

    @Inject
    private FinancialDoc financialDoc;

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long docNumber = Long.parseLong((req.getParameter("docNumber")));
            String faDate = req.getParameter("date").replace("/", "-");
            String description = req.getParameter("description");
            Long id = Long.valueOf(req.getParameter("fId"));
            Optional<FinancialTransaction> financialTransaction = financialTransactionService.findById(id);

            if (financialTransaction.isPresent()) {
                financialDoc = FinancialDoc
                        .builder()
                        .docNumber(docNumber)
                        .faDate(faDate)
                        .description(description)
                        .financialTransaction(financialTransaction.get())
                        .deleted(false)
                        .build();
                financialDoc.setFaDate(faDate);

                //validate
                BeanValidator<FinancialDoc> validator = new BeanValidator<>();

                if (validator.validate(financialDoc) != null){
                    resp.setStatus(500);
                    resp.getWriter().write(validator.validate(financialDoc).toString());
                }

                financialDocService.save(financialDoc);
                log.info("FinancialDocServlet - FinancialDoc Saved");
                req.getSession().setAttribute("financialDocId", financialDoc.getId());
                resp.sendRedirect("/financialDoc.do");

            } else {
                throw new NoContentException("The required financialTransaction does not exist !");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("financialDocList", financialDocService.findAll());
            req.getSession().setAttribute("financialTransaction", financialTransactionService.findAll());
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            financialDocService.removeById(id);

            log.info("FinancialDocServlet - FinancialDoc Removed");
            resp.sendRedirect("/financialDoc.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
