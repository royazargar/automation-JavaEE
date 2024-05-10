package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.controller.exception.IdIsRequiredException;
import com.mftplus.automation.model.Letter;
import com.mftplus.automation.model.Reference;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.model.enums.ReferenceType;
import com.mftplus.automation.service.impl.LetterServiceImpl;
import com.mftplus.automation.service.impl.ReferenceServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/referenceEdit.do")
public class ReferenceEditServlet extends HttpServlet {
    @Inject
    private ReferenceServiceImpl referenceService;
    @Inject
    private UserServiceImpl userService;
    @Inject
    private LetterServiceImpl letterService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReferenceEditServlet - Get");
        try {
            if (req.getParameter("id") == null) {
                throw new IdIsRequiredException("Please set reference id !");
            } else {
                long id = Integer.parseInt(req.getParameter("id"));
                Optional<Reference> reference = referenceService.findById(id);
                reference.ifPresent(value -> req.getSession().setAttribute("reference", value));

                req.getSession().setAttribute("refTypes", Arrays.asList(ReferenceType.values()));
                req.getSession().setAttribute("priorities", Arrays.asList(ReferencePriority.values()));
                req.getSession().setAttribute("referenceList", referenceService.findAll());
                req.getRequestDispatcher("/jsp/edit-reference.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReferenceEditServlet - Put");

        try {
            String letterId = req.getParameter("letterIdRef");
            String refType = req.getParameter("r_refType");
            String priority = req.getParameter("priority");
            String faExpiration = req.getParameter("r_expiration");
            String paraph = req.getParameter("paraph");
            String explanation = req.getParameter("explanation");
            String status = req.getParameter("status");
            String referenceReceiver = req.getParameter("referenceReceiver");
            boolean validate = req.getParameter("validate") != null && req.getParameter("validate").equals("on");

            String username = req.getUserPrincipal().getName();


            if (letterId != null && username != null) {

                Optional<Letter> letter = letterService.findById(Long.valueOf(letterId));
                Optional<User> user = userService.findByUsername(username);
                Optional<User> referenceReceiverId = userService.findByUsername(referenceReceiver);

                if (letter.isPresent() && user.isPresent() && referenceReceiverId.isPresent()) {
                    Reference reference =
                            Reference
                                    .builder()
                                    .letterId(letter.get())
                                    .referenceSenderId(user.get())
                                    .refDateAndTime(LocalDateTime.now())
                                    .paraph(paraph)
                                    .explanation(explanation)
                                    .status(Boolean.parseBoolean(status))
                                    .validate(validate)
                                    .priority(ReferencePriority.valueOf(priority))
                                    .refType(ReferenceType.valueOf(refType))
                                    .faExpiration(faExpiration)
                                    .deleted(false)
                                    .referenceReceiverId(referenceReceiverId.get())
                                    .build();
                    reference.setFaExpiration(faExpiration);
                    referenceService.edit(reference);
                    log.info("ReferenceEditServlet - Reference Edited");
                    resp.sendRedirect("/reference.do");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
//todo date format bug
