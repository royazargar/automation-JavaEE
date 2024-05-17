package com.mftplus.service;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.model.Reference;
import com.mftplus.model.enums.ReferencePriority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReferenceService {
    void save(Reference reference) throws Exception;
    void edit(Reference reference) throws NoContentException;
    void remove(Reference reference) throws Exception;
    void removeById(Long id) throws Exception;

    Optional<Reference> findById(Long id) throws NoContentException;
    List<Reference> findAll() throws Exception;

    List<Reference> findByReferenceSenderId(String senderUsername) throws Exception;
    List<Reference> findByReferenceReceiverId(String receiverUsername) throws Exception;

    List<Reference> findByReferenceSenderIdAndDeletedFalse(String senderUsername) throws Exception;
    List<Reference> findByReferenceReceiverIdAndDeletedFalse(String receiverUsername) throws Exception;

    List<Reference> findByRefDate(LocalDateTime date) throws Exception;
    List<Reference> findByLetterId(Long letterId) throws Exception;
    List<Reference> findByValidate(Boolean validate) throws Exception;
    List<Reference> findByParaph(String paraph) throws Exception;
    List<Reference> findByPriority(ReferencePriority priority) throws Exception;
    List<Reference> findByStatus(Boolean status) throws Exception;


}
