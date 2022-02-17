package org.nassauframework.core.logging.kibana;

/**
 * Interface that allows client projects to use their own log fields.
 *
 * @author Jordi Jaspers
 */
public interface KibanaLogField {

    /**
     * Get the name with which this field will appear in the log.
     *
     * @return the log name of the field
     */
    String getLogName();

    /**
     * Check if a key matches.
     *
     * @param key the key to match
     * @return true if not null and if the supplied key equals this key.
     */
    default boolean matches(final String key) {
        return key != null && key.equalsIgnoreCase(getLogName());
    }

}
