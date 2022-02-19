package org.nassauframework.core.security.model.entity;

/**
 * Defines the level of permission the user has.
 *
 * @author Jordi Jaspers
 */
public enum RoleLevel {

    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    PERMISSION_ADMIN("PERMISSION_ADMIN"),
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
    RoleLevel(final String value) {
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
    public static RoleLevel fromValue(final String value) {
        for (final RoleLevel type : RoleLevel.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
