package com.decrypto.challenge.config.interceptor.xclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class XClientService {

    @Value("${default.public.api.key}")
    private String defaultPublicApiKey;

    public boolean validateXClient(final String xClientToken) {
        return !defaultPublicApiKey.equals(xClientToken);
    }

}
