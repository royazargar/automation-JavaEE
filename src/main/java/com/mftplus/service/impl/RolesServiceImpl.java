package com.mftplus.service.impl;

import com.mftplus.model.Roles;
import com.mftplus.service.RolesService;
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
public class RolesServiceImpl implements RolesService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Roles role) throws Exception {
        entityManager.persist(role);
    }

    @Transactional
    @Override
    public void edit(Roles role) throws Exception {
        entityManager.merge(role);
    }

    @Transactional
    @Override
    public void remove(Roles role) throws Exception {
        role = entityManager.find(Roles.class, role.getId());
        role.setDeleted(true);
        entityManager.merge(role);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Roles role = entityManager.find(Roles.class, id);
        role.setDeleted(true);
        entityManager.merge(role);
    }

    @Transactional
    @Override
    public Optional<Roles> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Roles.class, id));
    }

    @Transactional
    @Override
    public List<Roles> findAll() throws Exception {
        TypedQuery<Roles> query = entityManager.createQuery("select oo from rolesEntity oo where oo.deleted=false", Roles.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Roles> findByRoleName(String roleName) throws Exception {
        TypedQuery<Roles> query = entityManager.createQuery("select oo from rolesEntity oo where oo.role=:roleName", Roles.class);
        query.setParameter("roleName",roleName);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Roles> findByUser(String username) throws Exception {
        TypedQuery<Roles> query = entityManager.createQuery("select oo from rolesEntity oo where oo.user=:username", Roles.class);
        query.setParameter("username",username);
        return query.getResultList();
    }

    //todo : is this supposed to be optional? did not work
    @Transactional
    @Override
    public List<Roles> findByUsernameAndRoleName(String username, String roleName) throws Exception {
        TypedQuery<Roles> query = entityManager.
                createQuery
                        ("select oo from rolesEntity oo where oo.user.username=:username and oo.role=:roleName and deleted=false", Roles.class);
        query.setParameter("username",username);
        query.setParameter("roleName",roleName);
        return query.getResultList();
    }
}
