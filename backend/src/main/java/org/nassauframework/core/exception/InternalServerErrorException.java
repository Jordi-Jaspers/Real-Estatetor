package org.nassauframework.core.exception;


import java.io.Serial;

import static io.micronaut.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exception thrown when the requested server is not available.
 *
 * @author Jordi Jaspers
 */
public class InternalServerErrorException extends HttpException {

    /**
     * The serial UID.
     */
    @Serial
    private static final long serialVersionUID = -3252101813888466865L;

    /**
     * Constructs a new {@code InternalServerErrorException}.
     */
    public InternalServerErrorException() {
        super(INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied message.
     */
    public InternalServerErrorException(final String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied message and {@link Throwable}.
     */
    public InternalServerErrorException(final String message, final Throwable cause) {
        super(message, cause, INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied {@link Throwable}.
     */
    public InternalServerErrorException(final Throwable cause) {
        super(cause, INTERNAL_SERVER_ERROR);
    }
}
