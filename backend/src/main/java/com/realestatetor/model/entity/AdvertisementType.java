package com.realestatetor.model.entity;

/**
 * Determines if the advertisement is for sale or for rent.
 */
public enum AdvertisementType {

    SALE("SALE"),
    RENT("RENT");

    /**
     * The value of the enumeration.
     */
    private final String value;

    AdvertisementType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Returns the enum value from the given string.
     *
     * @param value the string value.
     * @return the enum value.
     */
    public static AdvertisementType fromValue(final String value) {
        for (final AdvertisementType type : AdvertisementType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
