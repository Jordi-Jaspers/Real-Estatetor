package org.nassauframework.core.exception;

import java.io.Serial;

import static io.micronaut.http.HttpStatus.NOT_FOUND;

/**
 * Exception thrown when the endpoint does not exist.
 *
 * @author Jordi Jaspers
 */
public class NotFoundException extends HttpException {

    /**
     * The serial UID.
     */
    @Serial
    private static final long serialVersionUID = -2446858161898479667L;

    /**
     * Constructs a new {@code NotFoundException}.
     */
    public NotFoundException() {
        super(NOT_FOUND);
    }

    /**
     * Constructs a new {@code NotFoundException} with the supplied message.
     */
    public NotFoundException(final String message) {
        super(message, NOT_FOUND);
    }

    /**
     * Constructs a new {@code NotFoundException} with the supplied message and {@link Throwable}.
     */
    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause, NOT_FOUND);
    }

    /**
     * Constructs a new {@code NotFoundException} with the supplied {@link Throwable}.
     */
    public NotFoundException(final Throwable cause) {
        super(cause, NOT_FOUND);
    }
}

