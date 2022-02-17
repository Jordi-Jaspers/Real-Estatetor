package com.realestatetor.repository;

import com.realestatetor.model.entity.Property;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * The database connection with basic CRUD capabilities to the properties table.
 *
 * @author Jordi Jaspers
 */
@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface PropertyRepository extends ReactorCrudRepository<Property, Long> {

    // No need for extra methods here, Spring Data ReactiveMongoRepository will take care of it.
    // ReactiveMongoRepository also extends from CrudRepository, so we can use the standard CRUD methods or create custom ones.
    // example: Flux<Product> findByName(String name);
    //
    // Note:
    // Say you query for Product instances, what happens is that by default Micronaut Data MongoDB will only query for and fetch the
    // simple properties. In the case of single ended associations like the above Micronaut Data will only retrieve the ID and assign it
    // if is possible (In the case of entities that require constructor arguments this is not even possible).
    //
    // If you need to fetch the association too then you can use the @Join annotation on your repository interface to specify that
    // the aggregation should be executed to with a lookup of the associated Manufacturer.

    @NonNull
    @Join("address")
    @Override
    Flux<Property> findAll();

    @NonNull
    @Join("address")
    Mono<Property> findById(@NonNull @NotNull Long id);

    @NonNull
    @Join("address")
    Flux<Property> findByPriceAfterAndPriceBefore(@NonNull @NotNull Double lowerBound, @NonNull @NotNull Double upperBound);

    @NonNull
    @Join("address")
    Flux<Property> findByPriceAfter(@NonNull @NotNull Double lowerBound);

}


