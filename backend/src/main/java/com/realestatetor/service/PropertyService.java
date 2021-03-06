package com.realestatetor.service;

import com.realestatetor.model.entity.Property;
import com.realestatetor.repository.PropertyRepository;
import com.realestatetor.service.exceptions.NoPropertyFoundException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.nassauframework.core.exception.BadRequestException;
import org.nassauframework.core.logging.model.TransactionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static org.nassauframework.core.logging.kibana.TxSupplier.withTxIdDo;

/**
 * A service for the property endpoint.
 *
 * @author Jordi Jaspers
 */
@Singleton
public class PropertyService {

    /**
     * Default price value.
     */
    public static final String DEFAULT_PRICE = "0.00";

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyService.class);

    /**
     * The connection to the property table.
     */
    private final PropertyRepository repository;

    @Inject
    public PropertyService(final PropertyRepository repository) {
        this.repository = repository;
    }

    /**
     * Get all Property entries from the database.
     *
     * @return all the properties.
     */
    public Mono<List<Property>> getAllProperties() {
        return repository.findAll().collectList()
                .map(properties -> withTxIdDo(() -> {
                    if (properties.isEmpty()) {
                        throw new NoPropertyFoundException("There are no property entries in the repository.");
                    }
                    LOGGER.info("Requesting all properties");
                    return properties;
                }, TransactionId.get()));
    }

    /**
     * Find a property by its id.
     *
     * @param id the id of the property
     * @return the property
     */
    public Mono<Property> getProperty(final long id) {
        return repository.findById(id).map(property -> withTxIdDo(() -> {
            if (property == null) {
                throw new NoPropertyFoundException("There is no property with this ID.");
            }

            LOGGER.info("Requesting property with id: {}", id);
            return property;
        }, TransactionId.get()));
    }

    /**
     * Find a property within a specific price range.
     *
     * @param lowerBound the minimum price.
     * @param upperBound the maximum price.
     * @return the list of properties within the price range.
     */
    public Mono<List<Property>> getPropertyInPriceRange(final Double lowerBound, final Double upperBound) {
        final Flux<Property> result;

        if (!Objects.equals(upperBound, Double.parseDouble(DEFAULT_PRICE)) && lowerBound > upperBound) {
            throw new BadRequestException("Did you mean to set the minimum value greater then the maximum value?");
        } else if (upperBound.equals(Double.parseDouble(DEFAULT_PRICE))) {
            LOGGER.info("No maximum price limit set.");
            result = repository.findByPriceAfter(lowerBound);
        } else {
            result = repository.findByPriceAfterAndPriceBefore(lowerBound, upperBound);
        }

        return result.collectList()
                .map(properties -> withTxIdDo(() -> {
                    if (properties.isEmpty()) {
                        throw new NoPropertyFoundException("No property found in price range");
                    }
                    LOGGER.info("Requesting property within price range of: [{}$ - {}$]", lowerBound, upperBound);
                    return properties;
                }, TransactionId.get()));
    }

    /**
     * Create a new advertisement for the customer's property.
     *
     * @param property the property in question.
     * @return The created property.
     */
    public Mono<Property> createAdvertisement(final Property property) {
        LOGGER.info("Creating a new advertisement for property: {}", property.toString());
        return repository.save(property);
    }
}
