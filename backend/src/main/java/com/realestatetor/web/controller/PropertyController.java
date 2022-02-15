package com.realestatetor.web.controller;

import com.realestatetor.model.dto.PropertyDto;
import com.realestatetor.model.mapper.PropertyMapper;
import com.realestatetor.service.PropertyService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

import static com.realestatetor.web.Paths.PARAM_ID;
import static com.realestatetor.web.Paths.PARAM_SEARCH;
import static com.realestatetor.web.Paths.PROPERTY;

@Controller(PROPERTY)
public class PropertyController {

    /**
     * Default price value.
     */
    private static final String DEFAULT_PRICE = "0.00";

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
    public PropertyController(PropertyService service, final PropertyMapper mapper) {
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
        return service.getProperty(id).
                map(property -> HttpResponse.ok(mapper.toResourceObject(property)));
    }

    /**
     * Get all the properties within a specific price range.
     */
    @Get(PARAM_SEARCH)
    public Mono<MutableHttpResponse<?>> getPropertyInPriceRange(
            @QueryValue(defaultValue = DEFAULT_PRICE) Double min,
            @QueryValue(defaultValue = DEFAULT_PRICE) Double max) {
        return service.getPropertyInPriceRange(min, max)
                .map(properties -> HttpResponse.ok(mapper.toResourceObjectList(properties)));
    }

    /**
     * Create a new property.
     */
    @Post
    public Mono<MutableHttpResponse<PropertyDto>> CreatePropertyAdvertisement(@NonNull @Body final PropertyDto propertyDto) {
        return service.createAdvertisement(mapper.toDomainObject(propertyDto))
                .map(property -> HttpResponse.created(mapper.toResourceObject(property)));
    }
}

