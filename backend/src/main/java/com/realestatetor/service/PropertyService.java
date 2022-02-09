package com.realestatetor.service;

import com.realestatetor.model.entity.Property;
import com.realestatetor.repository.PropertyRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.commons.lang3.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Singleton
public class PropertyService {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

    /**
     * The connection to the property table.
     */
    private final PropertyRepository repository;

    @Inject
    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public Flux<Property> getAllProperties() {
        return repository.findAll();
    }

    public Mono<Property> getProperty(final long id) {
        return repository.findById(id);
    }

    public Flux<Property> getPropertyInPriceRange(final Double lowerBound, final Double upperBound) {
        if (upperBound == 0.00) {
            LOGGER.debug("No maximum price limit found.");
            return repository.findByPriceAfter(lowerBound);
        }
        return repository.findByPriceAfterAndPriceBefore(lowerBound, upperBound);
    }

    public Mono<Property> createAdvertisement(Property property) {
        LOGGER.info("Mapper property to {}", property.toString());
        return repository.save(property);
    }
}
