package com.decrypto.challenge.domain.services;

import com.decrypto.challenge.domain.dao.ClientDao;
import com.decrypto.challenge.domain.dao.MarketDao;
import com.decrypto.challenge.domain.entities.ClientJpa;
import com.decrypto.challenge.domain.entities.MarketJpa;
import com.decrypto.challenge.domain.model.ClientDTO;
import com.decrypto.challenge.domain.model.MarketDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientMarketJpaService {

    private final ClientDao clientDao;
    private final MarketDao marketDao;

    public ClientMarketJpaService(ClientDao clientDao,
                                  MarketDao marketDao) {
        this.clientDao = clientDao;
        this.marketDao = marketDao;
    }

    public List<MarketDTO> getMarkets(final Long clientId) {
        ClientJpa client = clientDao.findById(clientId);
        return client.getMarkets().stream()
                .map(MarketDTO::from)
                .toList();
    }

    public List<MarketDTO> addMarkets(final Long clientId,
                                      final List<Long> marketList) {
        ClientJpa client = clientDao.findById(clientId);
        List<MarketJpa> markets = marketDao.findByIds(marketList);

        if (marketList.size() != markets.size()) {
            throw new IllegalArgumentException("Invalid market IDs");
        }

        markets.forEach(market -> {
            if (!client.getMarkets().contains(market)) {
                client.getMarkets().add(market);
            }
        });

        ClientJpa clientNew = clientDao.save(client);

        return clientNew.getMarkets().stream().map(MarketDTO::from).toList();
    }

    public void removeMarkets(final Long clientId,
                              final List<Long> marketList) {
        ClientJpa client = clientDao.findById(clientId);
        List<MarketJpa> markets = marketDao.findByIds(marketList);

        if (marketList.size() != markets.size()) {
            throw new IllegalArgumentException("Invalid market IDs");
        }

        markets.forEach(market -> client.getMarkets().remove(market));

        clientDao.save(client);
    }

}
