package org.nassauframework.core.exception;


import java.io.Serial;

import static io.micronaut.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exception thrown when there is a problem with the database.
 *
 * @author Jordi Jaspers
 */
public class SQLException extends HttpException {

    /**
     * The serial UUID.
     */
    @Serial
    private static final long serialVersionUID = -3146695242426369020L;

    /**
     * Constructs a new {@code InternalServerErrorException}.
     */
    public SQLException() {
        super(INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied message.
     */
    public SQLException(final String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied message and {@link Throwable}.
     */
    public SQLException(final String message, final Throwable cause) {
        super(message, cause, INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs a new {@code InternalServerErrorException} with the supplied {@link Throwable}.
     */
    public SQLException(final Throwable cause) {
        super(cause, INTERNAL_SERVER_ERROR);
    }
}
