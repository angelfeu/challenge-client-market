package com.decrypto.challenge.controllers.model;

import com.decrypto.challenge.domain.model.ClientDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientMarketRequest {

    @NotNull
    private List<Long> markets;

}
