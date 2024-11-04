package com.decrypto.challenge.domain.entities;

import com.decrypto.challenge.domain.model.ClientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientJpa extends BaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToMany
    @JoinTable(
            name = "client_market",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "market_id")
    )
    private List<MarketJpa> markets = new ArrayList<>();

    public ClientJpa(ClientDTO dto) {
        this.id = dto.getId();
        this.description = dto.getDescription();
    }
}
