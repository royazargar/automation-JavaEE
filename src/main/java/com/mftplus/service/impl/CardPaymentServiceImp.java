package com.mftplus.service.impl;

import com.mftplus.model.CardPayment;
import com.mftplus.service.CardPaymentService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@SessionScoped
public class CardPaymentServiceImp implements CardPaymentService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(CardPayment cardPayment) throws Exception {
        entityManager.persist(cardPayment);
    }

    @Transactional
    @Override
    public void edit(CardPayment cardPayment) throws Exception {
        entityManager.merge(cardPayment);
    }

    @Transactional
    @Override
    public void remove(CardPayment cardPayment) throws Exception {
        cardPayment.setDeleted(true);
        entityManager.merge(cardPayment);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        CardPayment cardPayment = entityManager.find(CardPayment.class, id);
        cardPayment.setDeleted(true);
        entityManager.merge(cardPayment);
    }

    @Transactional
    @Override
    public List<CardPayment> findAll() throws Exception {
        TypedQuery<CardPayment> query = entityManager.createQuery("SELECT oo FROM cardPaymentEntity oo WHERE oo.deleted=false ", CardPayment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<CardPayment> findByDepositCode(String depositCode) throws Exception {
        TypedQuery<CardPayment> query = entityManager.createQuery("SELECT oo FROM cardPaymentEntity oo WHERE oo.depositCode=:depositCode AND oo.deleted=false ", CardPayment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<CardPayment> findByBankInvolved(String accountNumber) throws Exception {
        TypedQuery<CardPayment> query = entityManager.createQuery("SELECT oo FROM cardPaymentEntity oo WHERE oo.bankInvolved.accountNumber=:accountNumber AND oo.deleted=false ", CardPayment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<CardPayment> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(CardPayment.class, id));
    }
}
