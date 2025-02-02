package com.mftplus.service;

import com.mftplus.model.CheckPayment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CheckPaymentService {
    void save(CheckPayment checkPayment) throws Exception;

    void edit(CheckPayment checkPayment) throws Exception;

    void remove(CheckPayment checkPayment) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByCheckNumber(String checkNumber) throws Exception;

    List<CheckPayment> findAll() throws Exception;

    List<CheckPayment> findByCheckDueDate(LocalDateTime checkDueDate)throws Exception;

    Optional<CheckPayment> findByCashDeskNumber(int cashDeskNumber) throws Exception;

    Optional<CheckPayment> findByCheckNumber(String checkNumber) throws Exception;

    Optional<CheckPayment> findById(Long id) throws Exception;
}
