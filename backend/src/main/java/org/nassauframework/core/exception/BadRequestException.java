package org.nassauframework.core.exception;

import java.io.Serial;

import static io.micronaut.http.HttpStatus.BAD_REQUEST;

/**
 * Exception thrown when the request is incorrect.
 *
 * @author Jordi Jaspers
 */
public class BadRequestException extends HttpException {

    /**
     * The serial UID.
     */
    @Serial
    private static final long serialVersionUID = -2446858161898479667L;

    /**
     * Constructs a new {@code BadRequestException}.
     */
    public BadRequestException() {
        super(BAD_REQUEST);
    }

    /**
     * Constructs a new {@code BadRequestException} with the supplied message.
     */
    public BadRequestException(final String message) {
        super(message, BAD_REQUEST);
    }

    /**
     * Constructs a new {@code BadRequestException} with the supplied message and {@link Throwable}.
     */
    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause, BAD_REQUEST);
    }

    /**
     * Constructs a new {@code BadRequestException} with the supplied {@link Throwable}.
     */
    public BadRequestException(final Throwable cause) {
        super(cause, BAD_REQUEST);
    }
}

