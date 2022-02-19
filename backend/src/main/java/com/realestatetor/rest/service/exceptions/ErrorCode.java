package com.realestatetor.rest.service.exceptions;


import org.nassauframework.core.exception.ApiError;

import java.util.Objects;

/**
 * Default error codes for the "REAL-ESTATOR" project.
 *
 * @author Jordi Jaspers
 */
public enum ErrorCode implements ApiError {
    NO_PROPERTY_FOUND(1000, "No property found with the given parameters.");

    /**
     * The error code.
     */
    private final Integer errorCode;

    /**
     * The reason.
     */
    private final String reason;

    /**
     * Construct an instance with error code and reason.
     *
     * @param errorCode the error code
     * @param reason    the reason
     */
    ErrorCode(final Integer errorCode, final String reason) {
        this.errorCode = Objects.requireNonNull(errorCode);
        this.reason = reason;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getErrorCode() {
        return errorCode.toString();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getReason() {
        return reason;
    }

}
