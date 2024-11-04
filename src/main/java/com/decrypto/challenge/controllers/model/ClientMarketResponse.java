package com.decrypto.challenge.controllers.model;

import com.decrypto.challenge.domain.model.MarketDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientMarketResponse {

    private List<MarketResponse> markets;

    public static ClientMarketResponse from(List<MarketDTO> dto) {
        return new ClientMarketResponse(dto.stream().map(MarketResponse::from).toList());
    }

}
