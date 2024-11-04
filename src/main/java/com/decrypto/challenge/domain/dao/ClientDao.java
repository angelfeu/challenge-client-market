package com.decrypto.challenge.domain.dao;

import com.decrypto.challenge.domain.entities.ClientJpa;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao extends BaseDAO<ClientJpa> {

    public ClientJpa findById(Long id) {
        String query = """
            FROM    ClientJpa c
            WHERE   c.id = :id
            AND     c.active = true
            """;

        try {
            return em.createQuery(query, ClientJpa.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
