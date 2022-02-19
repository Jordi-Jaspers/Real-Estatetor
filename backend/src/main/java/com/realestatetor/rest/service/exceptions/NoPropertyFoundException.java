package com.realestatetor.rest.service.exceptions;

import org.nassauframework.core.exception.ApiException;

import java.io.Serial;

import static com.realestatetor.rest.service.exceptions.ErrorCode.NO_PROPERTY_FOUND;

/**
 * A specific exception for the case when no property is found.
 *
 * @author Jordi Jasper
 */
public class NoPropertyFoundException extends ApiException {

    /**
     * Generated UUID.
     */
    @Serial
    private static final long serialVersionUID = 7098354070944259924L;

    /**
     * The constructor.
     */
    public NoPropertyFoundException() {
        super(NO_PROPERTY_FOUND);
    }

    /**
     * Construct an API error with a custom message.
     *
     * @param message the error message.
     */
    public NoPropertyFoundException(final String message) {
        super(NO_PROPERTY_FOUND, message);
    }
}
