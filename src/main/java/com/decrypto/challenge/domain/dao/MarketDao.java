package com.decrypto.challenge.domain.dao;

import com.decrypto.challenge.domain.entities.MarketJpa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarketDao extends BaseDAO<MarketJpa> {

    public MarketJpa findById(Long id) {
        String query = """
            FROM    MarketJpa m
            WHERE   m.id = :id
            AND     m.active = true
            """;

        try {
            return em.createQuery(query, MarketJpa.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<MarketJpa> findByIds(List<Long> ids) {
        String query = """
            FROM    MarketJpa m
            WHERE   m.id in :ids
            AND     m.active = true
            """;

        return em.createQuery(query, MarketJpa.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
