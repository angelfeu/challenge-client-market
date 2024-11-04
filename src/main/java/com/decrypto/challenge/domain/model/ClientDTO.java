package com.decrypto.challenge.domain.model;

import com.decrypto.challenge.controllers.model.ClientRequest;
import com.decrypto.challenge.controllers.model.ClientResponse;
import com.decrypto.challenge.domain.entities.ClientJpa;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String description;

    public static ClientDTO from(ClientJpa jpa) {
        return ClientDTO.builder()
                .id(jpa.getId())
                .description(jpa.getDescription())
                .build();
    }

    public static ClientDTO from(ClientRequest request) {
        return ClientDTO.builder()
                .id(request.getId())
                .description(request.getDescription())
                .build();
    }

    public static ClientDTO from(ClientResponse response) {
        return ClientDTO.builder()
                .id(response.getId())
                .description(response.getDescription())
                .build();
    }
}
