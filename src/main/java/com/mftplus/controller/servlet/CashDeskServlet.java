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
@WebServlet(urlPatterns = "/cashDesk.do")
public class CashDeskServlet extends HttpServlet {
    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private CashDesk cashDesk;

    // TODO: 5/9/2024 null exception
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            Integer cashDeskNumber = Integer.valueOf(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            String username = req.getParameter("username");
            System.out.println(username);
            log.info(username);
            Optional<User> userOptional=userService.findByUsername(username);
            System.out.println(userOptional);
            log.info(String.valueOf(userOptional));

                if (userOptional.isPresent()) {
                    cashDesk = CashDesk
                            .builder()
                            .name(name)
                            .cashDeskNumber(cashDeskNumber)
                            .cashBalance(cashBalance)
                            .cashier(userOptional.get())
                            .deleted(false)
                            .build();

                    cashDeskService.save(cashDesk);
                    log.info("CashDeskServlet - CashDesk Saved");
                    resp.sendRedirect("/cashDesk.do");
                    req.getSession().setAttribute("CashDeskId",cashDesk.getId());
                }
            else {
                log.info("Invalid Cashier");
                resp.sendRedirect("/cashDesk.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            int cashDeskNumber = Integer.parseInt(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            Optional<User> user = userService.findByUsername(req.getParameter("username"));

            if (user.isPresent()) {
                cashDesk = CashDesk
                        .builder()
                        .name(name)
                        .cashDeskNumber(cashDeskNumber)
                        .cashBalance(cashBalance)
                        .cashier(user.get())
                        .deleted(false)
                        .build();

                cashDeskService.edit(cashDesk);
                log.info("CashDeskServlet - CashDesk Edited");
                resp.sendRedirect("/cashDesk.do");
            } else {
                log.info("Invalid Cashier");
                resp.sendRedirect("/cashDesk.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
            req.getSession().setAttribute("userList",userService.findAll());
            req.getRequestDispatcher("/jsp/cashDesk.jsp").forward(req, resp);

            System.out.println(userService.findAll());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            cashDeskService.removeById(id);

            log.info("CashDeskServlet - CashDesk Removed");
            resp.sendRedirect("/cashDesk.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
