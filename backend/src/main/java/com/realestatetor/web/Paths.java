package com.realestatetor.web;

/**
 * Class that holds the paths for the controllers.
 *
 * @author Jordi Jaspers
 */
public final class Paths {

    /**
     * The base part of all rest API paths.
     */
    public static final String BASE_PATH = "/rest";

    /**
     * The path for property.
     */
    public static final String PROPERTY = BASE_PATH + "/property";

    /**
     * The path for a property by id.
     */
    public static final String PARAM_ID = "/{id}";

    /**
     * The search path for property.
     */
    public static final String PARAM_SEARCH = "/search";

    /**
     * Private constructor to prevent instantiation.
     */
    private Paths() {
        // no-op
    }

}
