package com.decrypto.challenge.application.services;

import com.decrypto.challenge.controllers.model.ClientRequest;
import com.decrypto.challenge.domain.model.ClientDTO;
import com.decrypto.challenge.domain.model.MarketDTO;
import com.decrypto.challenge.domain.services.ClientJpaService;
import com.decrypto.challenge.domain.services.ClientMarketJpaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientJpaService clientJpaService;
    private final ClientMarketJpaService clientMarketJpaService;

    public ClientService(ClientJpaService clientJpaService,
                         ClientMarketJpaService clientMarketJpaService) {
        this.clientJpaService = clientJpaService;
        this.clientMarketJpaService = clientMarketJpaService;
    }

    public ClientDTO save(ClientRequest client) {
        return clientJpaService.save(ClientDTO.from(client));
    }

    public ClientDTO getById(Long id) {
        return clientJpaService.getById(id);
    }

    public ClientDTO update(ClientRequest request) {
        ClientDTO client = clientJpaService.getById(request.getId());
        if (client != null) {
            client.setDescription(request.getDescription());
            return clientJpaService.save(client);
        }
        return null;
    }

    public void deleteById(Long id) {
        clientJpaService.deleteById(id);
    }

    public List<MarketDTO> getMarkets(Long id) {
        return clientMarketJpaService.getMarkets(id);
    }

    public List<MarketDTO> addMarkets(Long id,
                                      List<Long> markets) {
        return clientMarketJpaService.addMarkets(id, markets);
    }

    public void removeMarkets(Long id,
                              List<Long> markets) {
        clientMarketJpaService.removeMarkets(id, markets);
    }
}
