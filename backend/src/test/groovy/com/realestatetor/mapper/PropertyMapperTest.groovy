package com.realestatetor.mapper

import com.realestatetor.model.entity.Address
import com.realestatetor.model.entity.AdvertisementType
import com.realestatetor.model.entity.Property
import com.realestatetor.model.entity.PropertyType
import com.realestatetor.model.mapper.AddressMapper
import com.realestatetor.model.mapper.PropertyMapper
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class PropertyMapperTest extends Specification {

    private AddressMapper addressMapper = new AddressMapperImpl()

    private PropertyMapper propertyMapper = new PropertyMapperImpl(addressMapper)

    private Address address;

    private Property property;

    def setup() {
        // Setting the address.
        address = new Address()
        address.setId(1)
        address.setCity("Paris")
        address.setCountry("France")
        address.setZipCode("75000")
        address.setStreet("Rue de la République")
        address.setNumber("10")

        // Setting the property.
        property = new Property()
        property.setId(1)
        property.setAddress(address)
        property.setDescription("Description")
        property.setPrice(1000000)
        property.setAdvertisementType(AdvertisementType.SALE)
        property.setImage("image.jpg")
        property.setPropertyType(PropertyType.APARTMENT)
        property.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()))
        property.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()))
    }

    def "The domain object will be correctly mapped to a resource object"() {
        when: "The mapper is called"
        def resourceObject = propertyMapper.toResourceObject(property)

        then: "The resource object will be correctly mapped"
        verifyAll(resourceObject) {
            it.description == property.getDescription()
            it.price == property.getPrice()
            it.advertisementType == property.getAdvertisementType().toString()
            it.image == property.getImage()
            it.propertyType == property.getPropertyType().toString()
            verifyAll(it.address) {
                it.city == address.getCity()
                it.country == address.getCountry()
                it.zipCode == address.getZipCode()
                it.street == address.getStreet()
                it.number == address.getNumber()
            }
        }
    }
}
