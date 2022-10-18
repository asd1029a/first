package com.hp.first.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {

    private String zipCode;
    private String street;
    private String city;

    public Address(String zipCode, String street, String city) {
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
    }
}
