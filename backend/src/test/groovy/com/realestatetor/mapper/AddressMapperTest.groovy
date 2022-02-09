package com.realestatetor.mapper

import com.realestatetor.model.entity.Address
import com.realestatetor.model.entity.AdvertisementType
import com.realestatetor.model.entity.Property
import com.realestatetor.model.entity.PropertyType
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class AddressMapperTest extends Specification {

    private AddressMapper addressMapper = new AddressMapperImpl()

    private Address address;

    def setup() {
        // Setting the address.
        address = new Address()
        address.setId(1)
        address.setCity("Paris")
        address.setCountry("France")
        address.setZipCode("75000")
        address.setStreet("Rue de la République")
        address.setNumber("10")
    }

    def "The domain object will be correctly mapped to a resource object"() {
        when: "The mapper is called"
        def resourceObject = addressMapper.toResourceObject(address)

        then: "The resource object will be correctly mapped"
        verifyAll(resourceObject) {
            it.city == address.getCity()
            it.country == address.getCountry()
            it.zipCode == address.getZipCode()
            it.street == address.getStreet()
            it.number == address.getNumber()
        }
    }
}
