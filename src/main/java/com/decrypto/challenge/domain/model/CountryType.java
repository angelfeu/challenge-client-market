package com.decrypto.challenge.domain.model;

import lombok.Getter;

@Getter
public enum CountryType {

    AR("ARGENTINA"),
    UY("URUGUAY");

    private final String countryName;

    CountryType(String countryName) {
        this.countryName = countryName;
    }

}
