package com.decrypto.challenge.controllers.model;

import com.decrypto.challenge.domain.model.ClientDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    private Long id;

    @NotNull
    private String description;

    public static ClientRequest from(ClientDTO dto) {
        return ClientRequest.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .build();
    }

}
