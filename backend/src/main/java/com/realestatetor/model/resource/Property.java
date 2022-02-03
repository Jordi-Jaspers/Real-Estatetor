package com.realestatetor.model.resource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The resource object that resembles a property with its attributes.
 */
@Entity
@Table(name = "property")
public class Property {
    /**
     * The unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The property type.
     */
    @NotNull
    private PropertyType propertyType;

    /**
     * The description of the property.
     */
    @NotNull
    private String description;

    /**
     * The price of the property.
     */
    @NotNull
    private double price;

    /**
     * The address of the property.
     */
    @NotNull
    @OneToOne
    private Address address;

    /**
     * The number of rooms.
     */
    @NotNull
    private AdvertisementType advertisementType;

    /**
     * The number of rooms.
     */
    @NotNull
    private String image;
}
