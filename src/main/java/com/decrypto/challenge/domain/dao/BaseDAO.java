package com.decrypto.challenge.domain.dao;

import com.decrypto.challenge.domain.entities.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import static java.util.Objects.isNull;

@Repository
public abstract class BaseDAO<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public T save(final T entity) {
        if (isNull(entity.getId())) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }

        return entity;
    }

    public void remove(final T entity) {
        em.remove(entity);
    }

}
