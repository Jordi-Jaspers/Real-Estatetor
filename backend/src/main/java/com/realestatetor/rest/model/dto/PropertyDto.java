package com.realestatetor.rest.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * The resource representing a property.
 */
@Data
@ToString
public class PropertyDto {

    /**
     * The property type.
     */
    private String propertyType;

    /**
     * The description of the property.
     */
    private String description;

    /**
     * The price of the property.
     */
    private double price;

    /**
     * The address of the property.
     */
    private AddressDto address;

    /**
     * The number of rooms.
     */
    private String advertisementType;

    /**
     * The number of rooms.
     */
    private String image;
}
