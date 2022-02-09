package com.realestatetor.controller;

import com.realestatetor.mapper.PropertyMapper;
import com.realestatetor.model.dto.PropertyDto;
import com.realestatetor.service.PropertyService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Controller("/property")
public class PropertyController {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

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
    public Mono<MutableHttpResponse<List<PropertyDto>>> getAllProperties() {
        return service.getAllProperties()
                .collectList()
                .map(property -> {
                    LOGGER.info("Requesting all properties");
                    return HttpResponse.ok(mapper.toResourceObjectList(property));
                });
    }

    /**
     * Get a property based on its ID.
     */
    @Get("/{id}")
    public Mono<MutableHttpResponse<PropertyDto>> getProperty(final long id) {
        return service.getProperty(id)
                .map(property -> {
                    LOGGER.info("Requesting property with id: {}", id);
                    return HttpResponse.ok(mapper.toResourceObject(property));
                })
                .defaultIfEmpty(HttpResponse.notFound())
                .onErrorReturn(HttpResponse.serverError());
    }

    /**
     * Get all the properties within a specific price range.
     */
    @Get("/search")
    public Mono<MutableHttpResponse<?>> getPropertyInPriceRange(
            @QueryValue(defaultValue = DEFAULT_PRICE) Double min,
            @QueryValue(defaultValue = DEFAULT_PRICE) Double max) {
        return service.getPropertyInPriceRange(min, max)
                .collectList()
                .map(property -> {
                    if (max != 0.00 && min > max) {
                        return HttpResponse.badRequest("Did you mean to set the minimum value greater then the maximum value?");
                    }
                    LOGGER.info("Requesting property within price range of: [{}$ - {}$]", min, max);
                    return HttpResponse.ok(mapper.toResourceObjectList(property));
                })
                .defaultIfEmpty(HttpResponse.notFound())
                .onErrorReturn(HttpResponse.serverError());
    }

    /**
     * Create a new property.
     */
    @Post
    public Mono<MutableHttpResponse<PropertyDto>> CreatePropertyAdvertisement(@NonNull @Body final PropertyDto propertyDto) {
        return service.createAdvertisement(mapper.toDomainObject(propertyDto))
                .map(property -> {
                    LOGGER.info("Creating a new advertisement for property: {}", propertyDto);
                    return HttpResponse.created(mapper.toResourceObject(property));
                })
                .onErrorReturn(HttpResponse.serverError());
    }
}

