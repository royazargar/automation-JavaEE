package com.mftplus.controller.servlet;

import com.google.gson.Gson;
import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.model.Bank;
import com.mftplus.model.Letter;
import com.mftplus.model.enums.LetterAccessLevel;
import com.mftplus.model.enums.LetterType;
import com.mftplus.model.enums.TransferMethod;
import com.mftplus.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(urlPatterns = "/bank.do")
@Slf4j
public class BankServlet extends HttpServlet {
    @Inject
    private BankServiceImpl bankService;

    @Inject
    private Bank bank;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String name=req.getParameter("name");
            String accountNumber=req.getParameter("accountNumber");
            int branchCode=Integer.parseInt(req.getParameter("branchCode"));
            String branchName=req.getParameter("branchName");
            String accountType=req.getParameter("accountType");
            long accountBalance= Long.parseLong(req.getParameter("accountBalance"));

            bank = Bank
                    .builder()
                    .name(name)
                    .accountNumber(accountNumber)
                    .branchCode(branchCode)
                    .branchName(branchName)
                    .accountType(accountType)
                    .accountBalance(accountBalance)
                    .deleted(false)
                    .build();

            bankService.save(bank);

            log.info("BankServlet - Bank Saved");
            resp.sendRedirect("/bank.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//
//            String name=req.getParameter("edit-name");
//            String accountNumber=req.getParameter("edit-accountNumber");
//            int branchCode=Integer.parseInt(req.getParameter("edit-branchCode"));
//            String branchName=req.getParameter("edit-branchName");
//            String accountType=req.getParameter("edit-accountType");
//            long accountBalance= Long.parseLong(req.getParameter("edit- accountBalance"));
//
//            bank = Bank
//                    .builder()
//                    .name(name)
//                    .accountNumber(accountNumber)
//                    .branchCode(branchCode)
//                    .branchName(branchName)
//                    .accountType(accountType)
//                    .accountBalance(accountBalance)
//                    .deleted(false)
//                    .build();
//
//            bankService.edit(bank);
//
//            log.info("BankServlet - Bank Edited");
//            resp.sendRedirect("/bank.do");
//        } catch (Exception e) {
//            log.info(e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String name=req.getParameter("edit-name");
            String accountNumber=req.getParameter("edit-accountNumber");
            int branchCode=Integer.parseInt(req.getParameter("edit-branchCode"));
            String branchName=req.getParameter("edit-branchName");
            String accountType=req.getParameter("edit-accountType");
            long accountBalance= Long.parseLong(req.getParameter("edit-accountBalance"));

            bank = Bank
                    .builder()
                    .name(name)
                    .accountNumber(accountNumber)
                    .branchCode(branchCode)
                    .branchName(branchName)
                    .accountType(accountType)
                    .accountBalance(accountBalance)
                    .deleted(false)
                    .build();

            bankService.edit(bank);

            log.info("BankServlet - Bank Edited");
            resp.sendRedirect("/bank.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("bankList", bankService.findAll());
            req.getRequestDispatcher("/jsp/bank.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            bankService.removeById(id);

            log.info("BankServlet - Bank Removed");
            resp.sendRedirect("/bank.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
