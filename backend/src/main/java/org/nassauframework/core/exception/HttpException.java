package org.nassauframework.core.exception;

import io.micronaut.http.HttpStatus;

import java.io.Serial;

import static java.util.Objects.requireNonNull;

/**
 * An exception class that contains HTTP status code and message.
 * This class is used to wrap with an HTTP status code so, that we can enrich the response body with convenient information about the error.
 *
 * @author Jordi Jaspers
 */
public class HttpException extends NassauException {

    /**
     * The serial version UID.
     */
    @Serial
    private static final long serialVersionUID = -3990631201408723548L;

    /**
     * The HTTP status code.
     */
    private final HttpStatus httpStatus;

    /**
     * Constructs a new {@code HttpException} with the supplied {@link HttpStatus}.
     */
    public HttpException(final HttpStatus httpStatus) {
        this.httpStatus = requireNonNull(httpStatus);
    }

    /**
     * Constructs a new {@code HttpException} with the supplied message and {@link HttpStatus}.
     */
    public HttpException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = requireNonNull(httpStatus);
    }

    /**
     * Constructs a new {@code HttpException} with the supplied message, {@link Throwable} and {@link HttpStatus}.
     */
    public HttpException(final String message, final Throwable cause, final HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = requireNonNull(httpStatus);
    }

    /**
     * Constructs a new {@code HttpException} with the supplied {@link Throwable} and {@link HttpStatus}.
     */
    public HttpException(final Throwable cause, final HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = requireNonNull(httpStatus);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

