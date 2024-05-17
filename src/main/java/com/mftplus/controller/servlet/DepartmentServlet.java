package com.mftplus.controller.servlet;

import com.mftplus.model.Department;
import com.mftplus.model.Organisation;
import com.mftplus.model.User;
import com.mftplus.service.impl.DepartmentServiceImp;
import com.mftplus.service.impl.OrganisationServiceImpl;
import com.mftplus.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/department.do")
public class DepartmentServlet extends HttpServlet {

    @Inject
    private DepartmentServiceImp service;

    @Inject
    private OrganisationServiceImpl organisationService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private Department department;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String duty = req.getParameter("duty");
            String phoneNumber = req.getParameter("phoneNumber");
            String organisationName = req.getParameter("organisationName");
            Optional<Organisation> organisation = organisationService.findByName(organisationName);
//            String username = req.getParameter("username");
//            Optional<User> userOptional = userService.findByUsername(username);
//            List<User> userList=new ArrayList<>();

//            String department1=req.getParameter("title");
//            Optional<Department> departmentOptional=service.findByTitle("title");

            if (organisation.isPresent()) {
                department = Department
                        .builder()
                        .title(title)
                        .duty(duty)
                        .phoneNumber(phoneNumber)
                        .organisation(organisation.get())
                        .build();
                service.save(department);
                log.info("DepartmentServlet - Department Saved");
                resp.sendRedirect("/department.do");
                req.getSession().setAttribute("DepartmentId", department.getId());
            } else {
                log.info("Invalid Department");
                resp.sendRedirect("/department.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("departmentList", service.findAll());
            req.getRequestDispatcher("/jsp/department.jsp").forward(req, resp);
            req.getSession().setAttribute("organisationList", organisationService.findAll());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
