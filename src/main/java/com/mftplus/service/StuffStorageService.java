package com.mftplus.service;

import com.mftplus.model.StuffStorage;

import java.util.List;
import java.util.Optional;

public interface StuffStorageService {
    void save(StuffStorage StuffStorage) throws Exception;

    void edit(StuffStorage StuffStorage) throws Exception;

    void remove(StuffStorage StuffStorage) throws Exception;

    void removeById(Long id) throws Exception;

    List<StuffStorage> findAll() throws Exception;

    Optional<StuffStorage> findById(Long id) throws Exception;
}
