package com.decrypto.challenge.controllers;

import com.decrypto.challenge.application.services.MarketService;
import com.decrypto.challenge.controllers.model.MarketRequest;
import com.decrypto.challenge.controllers.model.MarketResponse;
import com.decrypto.challenge.domain.model.MarketDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MarketControllerTest extends ControllerTest {

    @Mock
    MarketService marketService;

    @InjectMocks
    MarketController controller;

    @Override
    protected Object getTarget() {
        return controller;
    }

    @Test
    void testCreateMarket() throws Exception {
        final MarketRequest request = easyRandom.nextObject(MarketRequest.class);
        final MarketDTO marketDTO = MarketDTO.from(request);

        when(marketService.save(any())).thenReturn(marketDTO);

        MarketResponse response = perform(post("/api/markets"),
                request, MarketResponse.class, status().isOk());

        verify(this.marketService).save(any());
        assertEquals(marketDTO.getCode(), response.getCode());
    }

    @Test
    void testGetMarketById() throws Exception {
        final MarketDTO marketDTO = easyRandom.nextObject(MarketDTO.class);

        when(marketService.getById(anyLong())).thenReturn(marketDTO);

        final MarketResponse response = perform(get("/api/markets/1"),
                null, MarketResponse.class, status().isOk());

        verify(this.marketService).getById(anyLong());
        assertEquals(marketDTO.getCode(), response.getCode());
    }

    @Test
    void testUpdateMarket() throws Exception {
        final MarketRequest request = easyRandom.nextObject(MarketRequest.class);

        when(marketService.update(any())).thenReturn(MarketDTO.from(request));

        final MarketResponse result = perform(put("/api/markets/1"),
                request, MarketResponse.class, status().isOk());

        verify(this.marketService).update(any());
        assertEquals(request.getCode(), result.getCode());
    }

    @Test
    void testDeleteMarket() throws Exception {
        doNothing().when(marketService).deleteById(any());

        perform(delete("/api/markets/1"), null, status().isOk());

        verify(this.marketService).deleteById(anyLong());
    }

}
