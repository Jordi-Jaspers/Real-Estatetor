package org.nassauframework.core.exception;

/**
 * Interface that defines an API error of the application.
 * An API error is a well-defined error situation with a unique error code.
 *
 * @author Jordi Jaspers
 */
public interface ApiError {

    /**
     * Getter for the error code.
     *
     * @return the error code
     */
    String getErrorCode();

    /**
     * Get the error reason.
     *
     * @return the reason
     */
    String getReason();

}

