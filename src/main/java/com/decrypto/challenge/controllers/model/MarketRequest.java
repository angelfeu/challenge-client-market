package com.decrypto.challenge.controllers.model;

import com.decrypto.challenge.domain.model.CountryType;
import com.decrypto.challenge.domain.model.MarketDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketRequest {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String description;

    @NotNull
    private CountryType country;

    public static MarketRequest from(MarketDTO dto) {
        return MarketRequest.builder()
                .id(dto.getId())
                .code(dto.getCode())
                .description(dto.getDescription())
                .country(dto.getCountry())
                .build();
    }

}
