package com.realestatetor.rest.model.entity;

/**
 * Defines the type of property.
 */
public enum PropertyType {

    DETACHED("Detached"),
    SEMI_DETACHED("Semi-detached"),
    VILLA("Villa"),
    APARTMENT("Apartment"),
    ROOM("Room"),
    LAND("Land");

    /**
     * The value of the property type.
     */
    private final String value;

    /**
     * The default constructor.
     *
     * @param value the value of the property type.
     */
    PropertyType(final String value) {
        this.value = value;
    }

    /**
     * Gets the value of the property type.
     *
     * @return the value of the property type.
     */
    public String getValue() {
        return value;
    }

    /**
     * Gets the property type from its value.
     *
     * @param value the value of the property type.
     * @return the property type.
     */
    public static PropertyType fromValue(final String value) {
        for (final PropertyType type : PropertyType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
