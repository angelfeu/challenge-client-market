package com.decrypto.challenge.domain.entities;

import com.decrypto.challenge.domain.model.CountryType;
import com.decrypto.challenge.domain.model.MarketDTO;
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
@Table(name = "market")
public class MarketJpa extends BaseEntity {

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "country", nullable = false)
    private CountryType country;

    @ManyToMany(mappedBy = "markets")
    private List<ClientJpa> clients = new ArrayList<>();

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    public MarketJpa(MarketDTO dto) {
        this.id = dto.getId();
        this.code = dto.getCode();
        this.description = dto.getDescription();
        this.country = dto.getCountry();
    }
}
