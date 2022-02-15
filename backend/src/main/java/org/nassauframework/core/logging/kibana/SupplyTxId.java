package org.nassauframework.core.logging.kibana;

import io.micronaut.aop.Around;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A custom class that will provide the transaction ID on each method call.
 * The reason for this is that the transaction ID is not available in the on repository calls.
 * Everytime a repository is called, Java reactive will start a separate worker thread.
 * which means the Tx id stored on the local thread bean is empty and will not be displayed in logs.
 *
 * @author Jordi Jaspers
 */
@Retention(RUNTIME)
@Target(METHOD)
@Around
public @interface SupplyTxId {
    // The logic is implemented in the interceptor.
}
