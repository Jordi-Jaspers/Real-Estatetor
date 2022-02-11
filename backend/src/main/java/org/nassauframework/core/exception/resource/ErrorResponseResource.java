package org.nassauframework.core.exception.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 * This class represents the body of an error response.
 *
 * @author Jordi Jaspers
 */
public class ErrorResponseResource {

    /**
     * The used HTTP method.
     */
    private String method;

    /**
     * The Uri of the request.
     */
    private String uri;

    /**
     * The query string of the request.
     */
    private String query;

    /**
     * The content type of the request.
     */
    private String contentType;

    /**
     * The returned HTTP status code.
     */
    private int statusCode;

    /**
     * The status specific message.
     */
    private String statusMessage;

    /**
     * The error message.
     */
    private String errorMessage;

    /**
     * The throwable this resource was created for.
     */
    private final Throwable throwable;

    /**
     * Default constructor.
     */
    public ErrorResponseResource() {
        this(null);
    }

    /**
     * Construct an error resource with a throwable.
     *
     * @param throwable the throwable
     */
    public ErrorResponseResource(final Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * Returns the HTTP method.
     *
     * @return the HTTP method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the HTTP method.
     *
     * @param method the HTTP method
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Returns the request URI.
     *
     * @return the request URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the request URI.
     *
     * @param uri the request URI
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     * Returns the query string.
     *
     * @return the query string.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the query string.
     *
     * @param query the query string
     */
    public void setQuery(final String query) {
        this.query = query;
    }

    /**
     * Returns the request content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the request content type.
     *
     * @param contentType the content type.
     */
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    /**
     * Returns the response status code.
     *
     * @return the response status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the response status code.
     *
     * @param statusCode the response status code.
     */
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Returns the response status message.
     *
     * @return the status message.
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the response status message.
     *
     * @param statusMessage the response status message
     */
    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * Returns the error message ({@code Throwable#getMessage()}).
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the error message.
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Getter for throwable.
     *
     * @return the throwable
     */
    @JsonIgnore
    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }
}

