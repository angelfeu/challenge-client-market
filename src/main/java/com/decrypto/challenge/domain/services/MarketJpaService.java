package com.decrypto.challenge.domain.services;

import com.decrypto.challenge.domain.dao.ClientDao;
import com.decrypto.challenge.domain.dao.MarketDao;
import com.decrypto.challenge.domain.entities.ClientJpa;
import com.decrypto.challenge.domain.entities.MarketJpa;
import com.decrypto.challenge.domain.model.ClientDTO;
import com.decrypto.challenge.domain.model.MarketDTO;
import org.springframework.stereotype.Service;

@Service
public class MarketJpaService {

    private final MarketDao marketDao;

    public MarketJpaService(MarketDao marketDao) {
        this.marketDao = marketDao;
    }

    public MarketDTO save(MarketDTO market) {
        if (market == null) {
            throw new IllegalArgumentException("Market cannot be null");
        }

        return MarketDTO.from(marketDao.save(new MarketJpa(market)));
    }

    public MarketDTO getById(Long id) {
        MarketJpa market = marketDao.findById(id);
        return market != null ? MarketDTO.from(market) : null;
    }

    public void deleteById(Long id) {
        MarketJpa market = marketDao.findById(id);
        if (market != null) {
            market.setActive(false);
            marketDao.save(market);
        } else {
            throw new IllegalArgumentException("Market with id " + id + " does not exist");
        }
    }
}
