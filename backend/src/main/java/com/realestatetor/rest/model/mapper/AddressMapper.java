package com.realestatetor.rest.model.mapper;

import com.realestatetor.config.SharedMapperConfig;
import com.realestatetor.rest.model.dto.AddressDto;
import com.realestatetor.rest.model.entity.Address;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * A Bi-directional mapper for the Address.
 *
 * @author Jordi Jaspers
 */
@Mapper(config = SharedMapperConfig.class)
public interface AddressMapper {

    /**
     * Convert a domain object to a resource object.
     */
    AddressDto toResourceObject(Address input);

    /**
     * Convert a list domain objects to a list of resource objects.
     */
    List<AddressDto> toResourceObjectList(List<Address> input);

    /**
     * Convert a resource object to a domain object.
     */
    Address toDomainObject(AddressDto input);

    /**
     * Convert a list resource objects to a list of domain objects.
     */
    List<Address> toDomainObjectList(List<AddressDto> input);
}
