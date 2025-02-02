package com.mftplus.controller.api;

import com.mftplus.model.CardPayment;
import com.mftplus.service.impl.CardPaymentServiceImp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/cardPayment")
public class CardPaymentApi {

    @Inject
    private CardPaymentServiceImp cardPaymentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All Card Payment");
            return Response
                    .ok()
                    .entity(cardPaymentService.findAll())
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
            log.info("Find By Id Card Payment");
            return Response
                    .ok()
                    .entity(cardPaymentService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{depositCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDepositCode(@PathParam("depositCode") String depositCode) {
        try {
            log.info("Find By Id Card Payment Deposit Code");
            return Response
                    .ok()
                    .entity(cardPaymentService.findByDepositCode(depositCode))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByBankInvolved(@PathParam("accountNumber") String accountNumber) {
        try {
            log.info("Find By Id Card Payment Bank Involved");
            return Response
                    .ok()
                    .entity(cardPaymentService.findByBankInvolved(accountNumber))
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
    public Response save(CardPayment cardPayment) {
        try {
            log.info("Save Card Payment");
            cardPaymentService.save(cardPayment);
            return Response
                    .ok()
                    .entity(cardPayment)
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
    public Response edit(CardPayment cardPayment) {
        try {
            log.info("Edit Card Payment");
            cardPaymentService.edit(cardPayment);
            return Response
                    .ok()
                    .entity(cardPayment)
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
    public Response removeById(@PathParam("id") Long id) {
        try {
            log.info("Remove By Id Card Payment");
            cardPaymentService.removeById(id);
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
}
