package org.nassauframework.core.security.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import org.nassauframework.core.security.model.entity.Account;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * The database connection with basic CRUD capabilities to the account table.
 *
 * @author Jordi Jaspers
 */
@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface AccountRepository extends ReactorCrudRepository<Account, Long> {

    @NonNull
    Mono<Boolean> existsByEmailAndPassword(@NonNull @NotNull String email, @NonNull @NotNull String password);

    @NonNull
    Mono<Account> findByEmailAndPassword(@NonNull @NotNull String email, @NonNull @NotNull String password);

    @NonNull
    Mono<Account> findByEmail(@NonNull @NotNull String email);
}


