package org.nassauframework.core.exception;


import java.io.Serial;

import static io.micronaut.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exception thrown when the server enters an illegal state.
 *
 * @author Jordi Jaspers
 */
public class IllegalStateException extends HttpException {

    /**
     * The serial UUID.
     */
    @Serial
    private static final long serialVersionUID = -6898595036061772302L;

    /**
     * Constructs a new {@code InternalServerErrorException}.
     */
    public IllegalStateException() {
        super(INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied message.
     */
    public IllegalStateException(final String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied message and {@link Throwable}.
     */
    public IllegalStateException(final String message, final Throwable cause) {
        super(message, cause, INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied {@link Throwable}.
     */
    public IllegalStateException(final Throwable cause) {
        super(cause, INTERNAL_SERVER_ERROR);
    }
}
