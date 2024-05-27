package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.Department;
import com.mftplus.model.FinancialTransaction;
import com.mftplus.model.User;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import com.mftplus.service.impl.DepartmentServiceImp;
import com.mftplus.service.impl.FinancialTransactionServiceImpl;
import com.mftplus.service.impl.UserServiceImpl;
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
@WebServlet(urlPatterns = "/financialTransactionEdit.do")
public class FinancialTransactionEditServlet extends HttpServlet {

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private DepartmentServiceImp departmentService;

    @Inject
    private FinancialTransaction financialTransaction;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("id") == null) {
                resp.sendRedirect("/financialTransaction.do");
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                Optional<FinancialTransaction> financialTransaction = financialTransactionService.findById(id);
                if (financialTransaction.isPresent()) {
                    req.getSession().setAttribute("financialTransactionEdit", financialTransaction);
                }
                req.getSession().setAttribute("paymentTypes", Arrays.asList(PaymentType.values()));
                req.getSession().setAttribute("transactionTypes", Arrays.asList(FinancialTransactionType.values()));
                req.getSession().setAttribute("userList", userService.findAll());
                req.getSession().setAttribute("departmentList", departmentService.findAll());
                req.getRequestDispatcher("/jsp/editFinancialTransaction.jsp").forward(req, resp);
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
            String username = req.getParameter("username");
            Optional<User> userOptional = userService.findByUsername(username);
            Long dId = Long.valueOf(req.getParameter("dId"));
            Optional<Department> departmentOptional = departmentService.findById(dId);

            if (userOptional.isPresent() && departmentOptional.isPresent()) {
                String faDate = req.getParameter("date").replace("/", "-");
                Long amount = Long.valueOf(req.getParameter("amount"));
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");

                financialTransaction = FinancialTransaction
                        .builder()
                        .id(id)
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

                //validate
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();

                if (validator.validate(financialTransaction) != null) {
                    resp.setStatus(500);
                    resp.getWriter().write(validator.validate(financialTransaction).toString());
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransactionEditServlet - FinancialTransaction Edited");
                resp.setStatus(200);
            } else {
                throw new NoContentException("The required financialTransaction and department does not exist !");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
