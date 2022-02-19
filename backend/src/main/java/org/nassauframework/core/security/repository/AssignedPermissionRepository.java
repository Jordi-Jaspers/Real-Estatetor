package org.nassauframework.core.security.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import org.nassauframework.core.security.model.entity.AssignedPermission;
import org.nassauframework.core.security.model.entity.Role;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The database connection with basic CRUD capabilities to the AssignedPermission table.
 *
 * @author Jordi Jaspers
 */
@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface AssignedPermissionRepository extends ReactorCrudRepository<AssignedPermission, Long> {

    @NonNull
    @Join("role")
    @Join("permission")
    Flux<AssignedPermission> findByRoleInList(@NonNull @NotNull List<Role> role);
}


