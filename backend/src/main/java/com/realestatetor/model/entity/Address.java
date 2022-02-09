package com.realestatetor.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.persistence.Column;

/**
 * The address resource.
 */
@Data
@MappedEntity(value = "address")
public class Address {

    /**
     * The unique identifier of the address.
     */
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    /**
     * The country.
     */
    private String country;

    /**
     * The city.
     */
    private String city;

    /**
     * The street.
     */
    private String street;

    /**
     * The zipcode of the country.
     */
    private String zipCode;

    /**
     * The house number.
     */
    private String number;
}
