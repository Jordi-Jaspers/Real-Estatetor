package com.realestatetor.rest.model.mapper;

import com.realestatetor.config.SharedMapperConfig;
import com.realestatetor.rest.model.dto.PropertyDto;
import com.realestatetor.rest.model.entity.Property;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * A Bi-directional mapper for the Property.
 *
 * @author Jordi Jaspers
 */
@Mapper(config = SharedMapperConfig.class, uses = AddressMapper.class)
public interface PropertyMapper {

    /**
     * Convert a domain object to a resource object.
     */
    PropertyDto toResourceObject(Property input);

    /**
     * Convert a list domain objects to a list of resource objects.
     */
    List<PropertyDto> toResourceObjectList(List<Property> input);

    /**
     * Convert a resource object to a domain object.
     */
    Property toDomainObject(PropertyDto input);

    /**
     * Convert a list resource objects to a list of domain objects.
     */
    List<Property> toDomainObjectList(List<PropertyDto> input);
}
