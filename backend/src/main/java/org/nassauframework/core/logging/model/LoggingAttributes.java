package org.nassauframework.core.logging.model;

/**
 * A class that holds saved logging attributes.
 *
 * @author Jordi Jaspers
 */
public final class LoggingAttributes {

    /**
     * The transaction id header.
     */
    public static final String TX_ID_HEADER = "Nassau-Tx-Id";

    /**
     * The default constructor.
     */
    private LoggingAttributes() {
        // Prevent instantiation.
    }
}
