package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.controller.validation.BeanValidator;

import com.mftplus.model.Reference;
import com.mftplus.model.enums.ReferencePriority;
import com.mftplus.model.enums.ReferenceType;
import com.mftplus.service.impl.ReferenceServiceImpl;
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
@WebServlet(urlPatterns = "/referenceEdit.do")
public class ReferenceEditServlet extends HttpServlet {
    @Inject
    private ReferenceServiceImpl referenceService;

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
                req.getRequestDispatcher("/jsp/form/edit/reference-edit.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReferenceEditServlet - Put");

        //todo : name is null error, does not work

        try {
            long id = Integer.parseInt(req.getParameter("id"));
            String refType = req.getParameter("refType");
            String priority = req.getParameter("priority");
            String faExpiration = req.getParameter("r_expiration");
            String paraph = req.getParameter("paraph");
            String explanation = req.getParameter("explanation");
            String status = req.getParameter("status");
            boolean validate = req.getParameter("validate") != null && req.getParameter("validate").equals("on");


//            if (username != null) {

//                if (user.isPresent() && referenceReceiverId.isPresent()) {
                    Reference reference =
                            Reference
                                    .builder()
                                    .id(id)
                                    .paraph(paraph)
                                    .explanation(explanation)
                                    .status(Boolean.parseBoolean(status))
                                    .validate(validate)
                                    .priority(ReferencePriority.valueOf(priority))
                                    .refType(ReferenceType.valueOf(refType))
//                                    .faExpiration(faExpiration)
                                    .deleted(false)
                                    .build();
//                    reference.setFaExpiration(faExpiration);

            //validate
            BeanValidator<Reference> validator = new BeanValidator<>();

            if (validator.validate(reference) != null){
                resp.setStatus(500);
                resp.getWriter().write(validator.validate(reference).toString());
            }

                    referenceService.edit(reference);
                    log.info("ReferenceEditServlet - Reference Edited");
                    resp.setStatus(200);
//                }
//            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
//todo date format bug
