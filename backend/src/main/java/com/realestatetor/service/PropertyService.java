package com.realestatetor.service;

import com.realestatetor.model.resource.Property;
import com.realestatetor.repository.PropertyRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PropertyService {

    private PropertyRepository repository;

    @Inject
    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    private Property getProperty(long id) {
        return repository.findById(id);
    }

    private List<Property> getAllProperties() {
        return repository.findAll();
    }

    private void createProperty(Property property) {
        repository.create(property);
    }
}
