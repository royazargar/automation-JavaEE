package com.mftplus.service;

import com.mftplus.model.FinancialTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface  FinancialTransactionService {
    void save(FinancialTransaction financialTransaction) throws Exception;

    void edit(FinancialTransaction financialTransaction) throws Exception;

    void remove(FinancialTransaction financialTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByTrackingCode (int trackingCode) throws Exception;

    List<FinancialTransaction> findAll() throws Exception;

    Optional<FinancialTransaction> findById(Long id) throws Exception;

    Optional<FinancialTransaction> findByTrackingCode(int trackingCode) throws Exception;

    List<FinancialTransaction> findByDate(LocalDate date) throws Exception;

    List<FinancialTransaction> findByUser(String username) throws Exception;

    List<FinancialTransaction> findByDepartment(String title) throws Exception;

    Optional<FinancialTransaction> findByFinancialDoc(Long docNumber) throws Exception;
}
