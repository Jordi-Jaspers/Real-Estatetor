package com.realestatetor.model.entity;

/**
 * Defines the level of permission the user has.
 */
public enum PermissionLevel {

    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    USER("USER");

    private final String value;

    PermissionLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PermissionLevel fromValue(String value) {
        for (PermissionLevel type : PermissionLevel.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
