package com.realestatetor.model.entity;

/**
 * Defines the level of permission the user has.
 */
public enum PermissionLevel {

    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    USER("USER");

    /**
     * The permission level value.
     */
    private final String value;

    /**
     * The default constructor.
     *
     * @param value the permission level value.
     */
    PermissionLevel(final String value) {
        this.value = value;
    }

    /**
     * Returns the permission level value.
     *
     * @return the permission level value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the permission level corresponding to the given value.
     *
     * @param value the value of the permission level.
     * @return the permission level corresponding to the given value.
     */
    public static PermissionLevel fromValue(final String value) {
        for (final PermissionLevel type : PermissionLevel.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
