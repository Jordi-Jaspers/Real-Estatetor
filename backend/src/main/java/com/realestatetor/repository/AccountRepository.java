package com.realestatetor.repository;

import com.realestatetor.model.entity.Account;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
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
    @Join("permission")
    Mono<Account> findByEmailAndPassword(@NonNull @NotNull String email, @NonNull @NotNull String password);

}


