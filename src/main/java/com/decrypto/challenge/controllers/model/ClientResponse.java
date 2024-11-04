package com.decrypto.challenge.controllers.model;

import com.decrypto.challenge.domain.model.ClientDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Long id;
    private String description;

    public static ClientResponse from(ClientDTO dto) {
        return ClientResponse.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .build();
    }

}
