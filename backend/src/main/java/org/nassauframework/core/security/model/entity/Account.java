package org.nassauframework.core.security.model.entity;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Data;

import javax.validation.constraints.Email;
import java.sql.Timestamp;

/**
 * The account resource with specified user details.
 *
 * @author Jordi Jaspers
 */
@Data
@MappedEntity("account")
public class Account {

    /**
     * The unique identifier of the address.
     */
    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * the last name of the user.
     */
    private String lastName;

    /**
     * The email of the user.
     */
    @Email
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The salt added to the password.
     */
    private String salt;

    /**
     * The timestamp of the creation of the property listing.
     */
    @DateCreated
    private Timestamp createdAt;
}
