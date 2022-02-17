package com.realestatetor;

import io.micronaut.runtime.Micronaut;

/**
 * The main Application class.
 */
public final class Application {

    private Application() {
        // Empty constructor
    }

    /**
     * The main runnable method.
     *
     * @param args The arguments
     */
    public static void main(final String[] args) {
        Micronaut.run(Application.class, args);
    }
}
