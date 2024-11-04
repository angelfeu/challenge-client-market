package com.decrypto.challenge.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HealthControllerTest extends ControllerTest {

    @InjectMocks
    private HealthController controller;

    @Override
    protected Object getTarget() {
        return controller;
    }

    @Test
    void testHealth() throws Exception {
        perform(get("/health"), null, status().isOk());
    }
}
