package com.decrypto.challenge.controllers;

import com.decrypto.challenge.application.services.ClientService;
import com.decrypto.challenge.controllers.model.*;
import com.decrypto.challenge.domain.model.ClientDTO;
import com.decrypto.challenge.domain.model.MarketDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest extends ControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController controller;

    @Override
    protected Object getTarget() {
        return controller;
    }

    @Test
    void testCreateClient() throws Exception {
        final ClientRequest request = easyRandom.nextObject(ClientRequest.class);
        final ClientDTO clientDTO = ClientDTO.from(request);

        when(clientService.save(any())).thenReturn(clientDTO);

        ClientResponse response = perform(post("/api/clients"),
                request, ClientResponse.class, status().isOk());

        verify(this.clientService).save(any());
        assertEquals(clientDTO.getDescription(), response.getDescription());
    }

    @Test
    void testGetClientById() throws Exception {
        final ClientDTO clientDTO = easyRandom.nextObject(ClientDTO.class);

        when(clientService.getById(anyLong())).thenReturn(clientDTO);

        ClientResponse response = perform(get("/api/clients/1"),
                null, ClientResponse.class, status().isOk());

        verify(this.clientService).getById(anyLong());
        assertEquals(clientDTO.getDescription(), response.getDescription());
    }

    @Test
    void testUpdateClient() throws Exception {
        final ClientRequest request = easyRandom.nextObject(ClientRequest.class);

        when(clientService.update(any())).thenReturn(ClientDTO.from(request));

        ClientResponse result = perform(put("/api/clients/1"),
                request, ClientResponse.class, status().isOk());

        verify(this.clientService).update(any());
        assertEquals(request.getDescription(), result.getDescription());
    }

    @Test
    void testDeleteClient() throws Exception {
        doNothing().when(clientService).deleteById(any());

        perform(delete("/api/clients/1"), null, status().isOk());

        verify(this.clientService).deleteById(anyLong());
    }

    @Test
    void testGetMarkets() throws Exception {
        final MarketDTO marketDTO = easyRandom.nextObject(MarketDTO.class);

        when(clientService.getMarkets(anyLong())).thenReturn(List.of(marketDTO));

        ClientMarketResponse response = perform(get("/api/clients/1/markets"),
                null, ClientMarketResponse.class, status().isOk());

        verify(this.clientService).getMarkets(anyLong());
        assertEquals(1, response.getMarkets().size());
        assertEquals(marketDTO.getCode(), response.getMarkets().get(0).getCode());
    }

    @Test
    void testAddMarkets() throws Exception {
        MarketDTO marketDTO = easyRandom.nextObject(MarketDTO.class);
        ClientMarketRequest request = new ClientMarketRequest(List.of(1L));

        when(clientService.addMarkets(anyLong(),anyList())).thenReturn(List.of(marketDTO));

        ClientMarketResponse response = perform(post("/api/clients/1/markets/add"),
                request, ClientMarketResponse.class, status().isOk());

        verify(this.clientService).addMarkets(anyLong(),anyList());
        assertEquals(1, response.getMarkets().size());
        assertEquals(marketDTO.getCode(), response.getMarkets().get(0).getCode());
    }

    @Test
    void testRemoveMarkets() throws Exception {
        ClientMarketRequest request = new ClientMarketRequest(List.of(1L));

        doNothing().when(clientService).removeMarkets(anyLong(),anyList());

        perform(post("/api/clients/1/markets/remove"),
                request, status().isOk());

        verify(this.clientService).removeMarkets(anyLong(),anyList());
    }

}
