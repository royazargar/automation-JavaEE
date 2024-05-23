package com.mftplus.service;

import com.mftplus.model.FinancialDoc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FinancialDocService {
    void save(FinancialDoc financialDoc) throws Exception;

    void edit(FinancialDoc financialDoc) throws Exception;

    void remove(FinancialDoc financialDoc) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByDocNumber(Long docNumber) throws Exception;

    Optional<FinancialDoc> findById(Long id) throws Exception;

    Optional<FinancialDoc> findByDocNumber(Long docNumber) throws Exception;

    List<FinancialDoc> findByDescription(String description) throws Exception;

    List<FinancialDoc> findAll() throws Exception;

    List<FinancialDoc> findByDate(LocalDate date) throws Exception;
}
