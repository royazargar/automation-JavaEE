package com.mftplus.service;

import com.mftplus.model.CardPayment;

import java.util.List;
import java.util.Optional;

public interface CardPaymentService {
    void save(CardPayment cardPayment) throws Exception;

    void edit(CardPayment cardPayment) throws Exception;

    void remove(CardPayment cardPayment) throws Exception;

    void removeById(Long id) throws Exception;

    List<CardPayment> findAll() throws Exception;

    List<CardPayment> findByDepositCode(String depositCode) throws Exception;

    List<CardPayment> findByBankInvolved(String accountNumber) throws Exception;

    Optional<CardPayment> findById(Long id) throws Exception;
}
