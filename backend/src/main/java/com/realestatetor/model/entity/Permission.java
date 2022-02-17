package com.realestatetor.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

/**
 * The account resource with specified user details.
 */
@Data
@MappedEntity("permission")
public class Permission {

    /**
     * The unique identifier.
     */
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    /**
     * The permission level of the user.
     */
    private PermissionLevel level;
}
