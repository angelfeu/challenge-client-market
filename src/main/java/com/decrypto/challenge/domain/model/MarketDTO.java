package com.decrypto.challenge.domain.model;

import com.decrypto.challenge.controllers.model.MarketRequest;
import com.decrypto.challenge.controllers.model.MarketResponse;
import com.decrypto.challenge.domain.entities.MarketJpa;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketDTO {

    private Long id;
    private String code;
    private String description;
    private CountryType country;

    public static MarketDTO from(MarketJpa jpa) {
        return MarketDTO.builder()
                .id(jpa.getId())
                .code(jpa.getCode())
                .description(jpa.getDescription())
                .country(jpa.getCountry())
                .build();
    }

    public static MarketDTO from(MarketRequest request) {
        return MarketDTO.builder()
                .id(request.getId())
                .code(request.getCode())
                .description(request.getDescription())
                .country(request.getCountry())
                .build();
    }

    public static MarketDTO from(MarketResponse response) {
        return MarketDTO.builder()
                .id(response.getId())
                .code(response.getCode())
                .description(response.getDescription())
                .country(response.getCountry())
                .build();
    }
}
