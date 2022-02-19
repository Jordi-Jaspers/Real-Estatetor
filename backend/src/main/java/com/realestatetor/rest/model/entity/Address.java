package com.realestatetor.rest.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

/**
 * The address resource.
 */
@Data
@MappedEntity("address")
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
