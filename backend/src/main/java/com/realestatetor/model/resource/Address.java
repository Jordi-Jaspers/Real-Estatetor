package com.realestatetor.model.resource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The address resource.
 */
@Entity
@Table(name = "address")
public class Address {
    /**
     * The unique identifier of the address.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The country.
     */
    @NotNull
    private String country;

    /**
     * The city.
     */
    @NotNull
    private String city;

    /**
     * The street.
     */
    @NotNull
    private String street;

    /**
     * The zipcode of the country.
     */
    @NotNull
    private String zipCode;

    /**
     * The house number.
     */
    @NotNull
    private String number;

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
