package com.mftplus.controller.servlet;

import com.mftplus.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/bankTable.do")
public class BankTable extends HttpServlet {
    @Inject
    private BankServiceImpl bankService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("BankTableServlet - Get");
        try {
            req.getSession().setAttribute("bankList", bankService.findAll());
            req.getRequestDispatcher("/jsp/table/bank.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}