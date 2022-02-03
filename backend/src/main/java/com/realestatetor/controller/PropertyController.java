package com.realestatetor.controller;

import com.realestatetor.model.resource.Property;
import com.realestatetor.service.PropertyService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/property")
public class PropertyController {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);

    /**
     * The property service.
     */
    private final PropertyService service;

    /**
     * The class constructor.
     *
     * @param service The property service.
     */
    @Inject
    public PropertyController(PropertyService service) {
        this.service = service;
    }

    /**
     * Get all the properties from the database.
     */
    @Get
    public String getAllProperties() {
        return "No properties found";
    }

    /**
     * Get a specific property from the database.
     */
    @Get("/{id}")
    public Property getProperty(Long id) {
        LOGGER.info("getProperty()");
        return service.getProperty(id);
    }

    /**
     * Create a new property to add to the database.
     */
    @Post
    public Property createProperty(@Body Property property) {
        LOGGER.info("createProperty()");
        return service.createProperty(property);
    }
}
