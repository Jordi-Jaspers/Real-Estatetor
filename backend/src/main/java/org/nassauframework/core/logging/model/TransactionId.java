package org.nassauframework.core.logging.model;

import io.micronaut.runtime.context.scope.ThreadLocal;

import java.util.UUID;

/**
 * Saving the transaction id in a ThreadLocal in order to recall it whenever needed.
 * <p>
 * Note: Suppressing the warning because an AOP or advice classes may not be final classes.
 *
 * @author Jordi Jaspers
 */
@ThreadLocal
public abstract class TransactionId {

    /**
     * The thread local id.
     */
    private static UUID id;

    /**
     * Return the id as string.
     */
    public static String get() {
        return id.toString();
    }

    /**
     * Set the transaction id.
     */
    public static void set(final UUID value) {
        id = value;
    }

    /**
     * Clear the thread local.
     */
    public static void remove() {
        id = null;
    }
}
