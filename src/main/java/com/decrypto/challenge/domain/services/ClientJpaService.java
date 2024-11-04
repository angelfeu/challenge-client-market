package com.decrypto.challenge.domain.services;

import com.decrypto.challenge.domain.entities.ClientJpa;
import com.decrypto.challenge.domain.dao.ClientDao;
import com.decrypto.challenge.domain.model.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientJpaService {

    private final ClientDao clientDao;

    public ClientJpaService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDTO save(ClientDTO client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        return ClientDTO.from(clientDao.save(new ClientJpa(client)));
    }

    public ClientDTO getById(Long id) {
        ClientJpa client = clientDao.findById(id);
        return client != null ? ClientDTO.from(client) : null;
    }

    public void deleteById(Long id) {
        ClientJpa client = clientDao.findById(id);
        if (client != null) {
            client.setActive(false);
            clientDao.save(client);
        }
    }

}
