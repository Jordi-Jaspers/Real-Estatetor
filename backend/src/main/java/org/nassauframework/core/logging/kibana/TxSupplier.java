/*
 * Copyright (C) ilionx Group BV 2021
 */

package org.nassauframework.core.logging.kibana;

import lombok.SneakyThrows;
import org.nassauframework.core.logging.model.TransactionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Callable;

import static org.nassauframework.core.logging.kibana.KibanaLogFieldNames.TX_ID;

/**
 * Utility to wrap a Method with the appropriate transaction id values for logging.
 *
 * @author Jordi Jaspers
 */
public final class TxSupplier {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TxSupplier.class);

    /**
     * Pass the transaction id to the given callable.
     */
    private static final String TRANSACTION_ID_LOGGING = "Wrapping method with transaction id: {}";

    /**
     * The default constructor.
     */
    private TxSupplier() {
        // Prevent instantiation.
    }

    /**
     * Wrap the method, add logging with a random transaction id.
     *
     * @param object The method to invoke.
     */
    public static <T> T withTxIdDo(final T object) {
        return withTxIdDo(object, UUID.randomUUID());
    }

    /**
     * Wrap the method, add logging with a random transaction id.
     *
     * @param method The method to invoke.
     */
    @SneakyThrows
    public static <T> T withTxIdDo(final Callable<T> method) {
        return withTxIdDo(method, UUID.randomUUID());
    }

    /**
     * Wrap the method, add logging with the given transaction id.
     *
     * @param object The method to invoke.
     * @param uuid   The transaction id.
     * @param <T>    The type of the method.
     * @return The result of the method invocation.
     */
    public static <T> T withTxIdDo(final T object, final Object uuid) {
        try {
            LOGGER.debug(TRANSACTION_ID_LOGGING, uuid);
            if (uuid instanceof UUID) {
                TransactionId.set((UUID) uuid);
            } else {
                TransactionId.set(UUID.fromString((String) uuid));
            }
            KibanaLogFields.tag(TX_ID, TransactionId.get());
        } finally {
            TransactionId.remove();
            KibanaLogFields.clear();
        }
        return object;
    }

    /**
     * Wrap the method, add logging.
     *
     * @param method The method to invoke.
     */
    @SneakyThrows
    public static <T> T withTxIdDo(final Callable<T> method, final Object uuid) {
        try {
            LOGGER.debug(TRANSACTION_ID_LOGGING, uuid);
            if (uuid instanceof UUID) {
                TransactionId.set((UUID) uuid);
            } else {
                TransactionId.set(UUID.fromString((String) uuid));
            }
            KibanaLogFields.tag(TX_ID, TransactionId.get());
            return method.call();
        } finally {
            TransactionId.remove();
            KibanaLogFields.clear();
        }
    }
}
