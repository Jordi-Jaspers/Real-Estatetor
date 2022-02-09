package com.realestatetor.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * The address resource.
 */
@Data
@ToString
public class AddressDto {

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
