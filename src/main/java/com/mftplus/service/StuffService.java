package com.mftplus.service;

import com.mftplus.model.Stuff;

import java.util.List;
import java.util.Optional;

public interface StuffService {
    void save(Stuff stuff) throws Exception;

    void edit(Stuff stuff) throws Exception;

    void remove(Stuff stuff) throws Exception;

    void removeById(Long id) throws Exception;

    List<Stuff> findAll() throws Exception;

    Optional<Stuff> findById(Long id) throws Exception;
}
