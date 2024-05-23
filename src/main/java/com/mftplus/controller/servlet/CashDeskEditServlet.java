package com.mftplus.controller.servlet;

import com.mftplus.model.CashDesk;
import com.mftplus.model.User;
import com.mftplus.service.impl.CashDeskServiceImp;
import com.mftplus.service.impl.UserServiceImpl;
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
@WebServlet(urlPatterns = "/cashDeskEdit.do")
public class CashDeskEditServlet extends HttpServlet {

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private CashDesk cashDesk;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("id") == null) {
                resp.sendRedirect("/cashDesk.do");
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                Optional<CashDesk> cashDesk = cashDeskService.findById(id);
                cashDesk.ifPresent(desk -> req.getSession().setAttribute("cashDeskEdit", desk));
                req.getSession().setAttribute("userList", userService.findAll());
                req.getRequestDispatcher("/jsp/editCashDesk.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            int cashDeskNumber = Integer.parseInt(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            Optional<User> user = userService.findByUsername(req.getParameter("username"));

            if (user.isPresent()) {
                cashDesk = CashDesk
                        .builder()
                        .id(id)
                        .name(name)
                        .cashDeskNumber(cashDeskNumber)
                        .cashBalance(cashBalance)
                        .cashier(user.get())
                        .deleted(false)
                        .build();
                cashDeskService.edit(cashDesk);
                resp.sendRedirect("/cashDesk.do");
            }else {
                log.info("Invalid Cashier");
                resp.sendRedirect("/cashDesk.do");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
