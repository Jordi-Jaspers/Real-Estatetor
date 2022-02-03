package com.realestatetor.model.resource;

/**
 * Defines the type of property.
 */
public enum PropertyType {

    DETACHED("Detached"),
    SEMI_DETACHED("Semi-detached"),
    VILLA("Villa"),
    APARTMENT("Apartment"),
    ROOM("Room"),
    OTHER("Land");

    private final String value;

    PropertyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PropertyType fromValue(String value) {
        for (PropertyType type : PropertyType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
