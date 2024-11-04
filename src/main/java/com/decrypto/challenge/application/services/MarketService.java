package com.decrypto.challenge.application.services;

import com.decrypto.challenge.controllers.model.MarketRequest;
import com.decrypto.challenge.domain.model.MarketDTO;
import com.decrypto.challenge.domain.services.MarketJpaService;
import org.springframework.stereotype.Service;

@Service
public class MarketService {

    private final MarketJpaService marketJpaService;

    public MarketService(MarketJpaService marketJpaService) {
        this.marketJpaService = marketJpaService;
    }

    public MarketDTO save(MarketRequest market) {
        return marketJpaService.save(MarketDTO.from(market));
    }

    public MarketDTO getById(Long id) {
        return marketJpaService.getById(id);
    }

    public MarketDTO update(MarketRequest request) {
        MarketDTO market = marketJpaService.getById(request.getId());
        if (market != null) {
            market.setCode(request.getCode());
            market.setDescription(request.getDescription());
            market.setCountry(request.getCountry());
            return marketJpaService.save(market);
        }
        return null;
    }

    public void deleteById(Long id) {
        marketJpaService.deleteById(id);
    }
}
