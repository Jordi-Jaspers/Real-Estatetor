package org.nassauframework.core.security.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * The permission entity that connects the role with its permissions.
 *
 * @author Jordi Jaspers
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
     * The name of the permission.
     */
    private String name;

    /**
     * The description of the permission.
     */
    private String description;
}

