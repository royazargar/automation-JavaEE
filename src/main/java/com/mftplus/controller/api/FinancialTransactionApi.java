package com.mftplus.controller.api;

import com.mftplus.model.FinancialTransaction;
import com.mftplus.service.impl.FinancialTransactionServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Path("/financialTransaction")
public class FinancialTransactionApi {

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("FindAllFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findAll())
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            log.info("FindByIdFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{trackingCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByTrackingCode(@PathParam("trackingCode") int trackingCode) {
        try {
            log.info("FindByTrackingCodeFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findByTrackingCode(trackingCode))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDate(@PathParam("date") String date) {
        try {
            LocalDateTime datePars = LocalDateTime.parse(date);
            log.info("FindByDateFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findByDate(LocalDate.from(datePars)))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUser(@PathParam("username") String username) {
        try {
            log.info("FindByPayerFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findByUser(username))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDepartment(@PathParam("title") String title) {
        try {
            log.info("FindByDepartmentFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findByDepartment(title))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{docNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByFinancialDoc(@PathParam("docNumber") Long docNumber) {
        try {
            log.info("FindByFinancialDocFinancialTransaction");
            return Response
                    .ok()
                    .entity(financialTransactionService.findByFinancialDoc(docNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(FinancialTransaction financialTransaction) {
        try {
            log.info("Save FinancialTransaction");
            financialTransactionService.save(financialTransaction);
            return Response
                    .ok()
                    .entity(financialTransaction)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(FinancialTransaction financialTransaction) {
        try {
            log.info("Edit FinancialTransaction");
            financialTransactionService.edit(financialTransaction);
            return Response
                    .ok()
                    .entity(financialTransaction)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long id) {
        try {
            log.info("Remove FinancialTransaction");
            financialTransactionService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{trackingCode}")
    public Response removeByTrackingCode(@PathParam("trackingCode") int trackingCode) {
        try {
            log.info("Remove FinancialTransaction Tracking Code");
            financialTransactionService.removeByTrackingCode(trackingCode);
            return Response
                    .ok()
                    .entity(trackingCode)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}