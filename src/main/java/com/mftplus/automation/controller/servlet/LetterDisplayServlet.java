package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.controller.exception.LetterIdIsRequiredException;
import com.mftplus.automation.model.Letter;
import com.mftplus.automation.model.enums.LetterAccessLevel;
import com.mftplus.automation.model.enums.LetterType;
import com.mftplus.automation.model.enums.TransferMethod;
import com.mftplus.automation.service.impl.LetterServiceImpl;
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
@WebServlet(urlPatterns = "/letterDisplay.do")
public class LetterDisplayServlet extends HttpServlet {
    @Inject
    private LetterServiceImpl letterService;

    //todo : a better way instead of 500 error page
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LetterDisplayServlet - Get");
        try {
            if (req.getParameter("id") == null) {
                throw new LetterIdIsRequiredException("Please set letter id !");
            } else {
                long id = Integer.parseInt(req.getParameter("id"));
                Optional<Letter> letter = letterService.findById(id);
                letter.ifPresent(value -> req.getSession().setAttribute("letter", value));

                req.getSession().setAttribute("accessLevels", Arrays.asList(LetterAccessLevel.values()));
                req.getSession().setAttribute("transferMethods", Arrays.asList(TransferMethod.values()));
                req.getSession().setAttribute("letterTypes", Arrays.asList(LetterType.values()));
                req.getRequestDispatcher("/jsp/form/display/letter.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
