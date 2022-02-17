package org.nassauframework.core.exception;

import java.io.Serial;

/**
 * Base exception class for all Nassau exceptions.
 *
 * @author Jordi Jaspers
 */
public class NassauException extends RuntimeException {

    /**
     * Serial version UID.
     */
    @Serial
    private static final long serialVersionUID = 6595973568098304165L;

    /**
     * Constructs a new {@code NassauException}.
     */
    public NassauException() {
        super();
    }

    /**
     * Constructs a new {@code NassauException} with the supplied message.
     */
    public NassauException(final String message) {
        super(message);
    }

    /**
     * Constructs a new {@code NassauException} with the supplied message and {@link Throwable}.
     */
    public NassauException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code NassauException} with the supplied {@link Throwable}.
     */
    public NassauException(final Throwable cause) {
        super(cause);
    }
}

