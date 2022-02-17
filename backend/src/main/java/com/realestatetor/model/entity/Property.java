package com.realestatetor.model.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

import java.sql.Timestamp;

/**
 * The resource object that resembles a property with its attributes.
 * <p>
 * '@Data' is a convenient shortcut annotation that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together.
 * In other words, @Data generates all the boilerplate that is normally associated with simple POJOs (Plain Old Java Objects) and beans.
 * getters for all fields, setters for all non-final fields, and appropriate toString, equals and hashCode implementations that involve the fields of the class,
 * and a constructor that initializes all final fields, as well as all non-final fields with no initializer that have been marked with @NonNull, in order to ensure the field is never null.
 *
 * @see <a href="https://projectlombok.org/features/Data"> Lombok Data </a>
 */
@Data
@MappedEntity(value = "property")
public class Property {

    /**
     * The unique identifier.
     */
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    /**
     * The property type.
     */
    private PropertyType propertyType;

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
     * <p>
     * NOTE: JoinColumn is used to specify the name of the column in the table that is the foreign key for the relationship.
     */
    @Relation(value = Relation.Kind.ONE_TO_ONE, cascade = Relation.Cascade.ALL)
    private Address address;

    /**
     * The number of rooms.
     */
    private AdvertisementType advertisementType;

    /**
     * The number of rooms.
     */
    private String image;

    /**
     * The timestamp of the creation of the property listing.
     */
    @DateCreated
    private Timestamp createdAt;

    /**
     * The timestamp of the creation of the property listing.
     */
    @DateUpdated
    private Timestamp updatedAt;
}
