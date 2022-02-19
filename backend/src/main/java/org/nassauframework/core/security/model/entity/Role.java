package org.nassauframework.core.security.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

import java.util.Set;

/**
 * The role entity of the user.
 *
 * @author Jordi Jaspers
 */
@Data
@MappedEntity("role")
public class Role {

    /**
     * The unique identifier.
     */
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    /**
     * The role description of the user.
     */
    private RoleLevel level;
}

