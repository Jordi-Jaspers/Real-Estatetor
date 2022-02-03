package com.realestatetor.model.resource;

/**
 * Determines if the advertisement is for sale or for rent.
 */
public enum AdvertisementType {

    SALE("SALE"),
    RENT("RENT");

    private String value;

    AdvertisementType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AdvertisementType fromValue(String value) {
        for (AdvertisementType type : AdvertisementType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
