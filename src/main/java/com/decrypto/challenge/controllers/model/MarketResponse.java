package com.decrypto.challenge.controllers.model;

import com.decrypto.challenge.domain.model.CountryType;
import com.decrypto.challenge.domain.model.MarketDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketResponse {

    private Long id;
    private String code;
    private String description;
    private CountryType country;

    public static MarketResponse from(MarketDTO dto) {
        return MarketResponse.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .description(dto.getDescription())
                .country(dto.getCountry())
                .build();
    }

}
