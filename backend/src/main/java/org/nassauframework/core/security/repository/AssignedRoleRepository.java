package org.nassauframework.core.security.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import org.nassauframework.core.security.model.entity.Account;
import org.nassauframework.core.security.model.entity.AssignedRole;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;

/**
 * The database connection with basic CRUD capabilities to the AssignedRole table.
 *
 * @author Jordi Jaspers
 */
@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface AssignedRoleRepository extends ReactorCrudRepository<AssignedRole, Long> {

    @NonNull
    @Join("account")
    @Join("role")
    Flux<AssignedRole> findByAccount(@NonNull @NotNull Account account);

}


