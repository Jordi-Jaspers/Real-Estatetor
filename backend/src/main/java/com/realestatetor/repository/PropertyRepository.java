package com.realestatetor.repository;

import com.realestatetor.model.resource.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}

