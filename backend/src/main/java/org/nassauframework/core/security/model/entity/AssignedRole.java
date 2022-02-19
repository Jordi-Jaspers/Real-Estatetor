package org.nassauframework.core.security.model.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Data;

/**
 * The permission entity that connects the role with its permissions.
 *
 * @author Jordi Jaspers
 */
@Data
@MappedEntity("assigned_role")
public class AssignedRole {

    /**
     * The unique identifier.
     */
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    /**
     * The role.
     */
    @Relation(Relation.Kind.MANY_TO_ONE)
    private Account account;

    /**
     * The permission.
     */
    @Relation(Relation.Kind.MANY_TO_ONE)
    private Role role;
}

