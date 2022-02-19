package com.realestatetor.rest.controller;

import com.realestatetor.rest.model.dto.PropertyDto;
import com.realestatetor.rest.model.mapper.PropertyMapper;
import com.realestatetor.rest.service.PropertyService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import static com.realestatetor.rest.Paths.PARAM_ID;
import static com.realestatetor.rest.Paths.PARAM_SEARCH;
import static com.realestatetor.rest.Paths.PROPERTY;
import static com.realestatetor.rest.service.PropertyService.DEFAULT_PRICE;
import static org.nassauframework.core.security.model.entity.PermissionLevel.READ_PROPERTY;
import static org.nassauframework.core.security.model.entity.PermissionLevel.WRITE_PROPERTY;

@Secured(READ_PROPERTY)
@Controller(PROPERTY)
public class PropertyController {

    /**
     * The property service.
     */
    private final PropertyService service;

    /**
     * The Bi-directional mapper for the Property entity.
     */
    private final PropertyMapper mapper;

    /**
     * The class constructor.
     *
     * @param service The property service.
     * @param mapper  The Bi-directional mapper for the Property entity.
     */
    @Inject
    public PropertyController(final PropertyService service, final PropertyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Get all the properties from the database.
     */
    @Get
    public Mono<MutableHttpResponse<?>> getAllProperties() {
        return service.getAllProperties()
                .map(property -> HttpResponse.ok(mapper.toResourceObjectList(property)));
    }

    /**
     * Get a property based on its ID.
     */
    @Get(PARAM_ID)
    public Mono<MutableHttpResponse<?>> getProperty(final long id) {
        return service.getProperty(id)
                .map(property -> HttpResponse.ok(mapper.toResourceObject(property)));
    }

    /**
     * Get all the properties within a specific price range.
     */
    @Get(PARAM_SEARCH)
    public Mono<MutableHttpResponse<?>> getPropertyInPriceRange(
            @QueryValue(defaultValue = DEFAULT_PRICE) final Double min,
            @QueryValue(defaultValue = DEFAULT_PRICE) final Double max) {
        return service.getPropertyInPriceRange(min, max)
                .map(properties -> HttpResponse.ok(mapper.toResourceObjectList(properties)));
    }

    /**
     * Create a new property.
     */
    @Secured(WRITE_PROPERTY)
    @Post
    public Mono<MutableHttpResponse<?>> createPropertyAdvertisement(@NonNull @Body final PropertyDto propertyDto) {
        return service.createAdvertisement(mapper.toDomainObject(propertyDto))
                .map(property -> HttpResponse.created(mapper.toResourceObject(property)));
    }
}

