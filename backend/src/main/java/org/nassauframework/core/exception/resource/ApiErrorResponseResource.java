package org.nassauframework.core.exception.resource;


import org.nassauframework.core.exception.ApiException;

/**
 * This class represents the body of an API error response.
 *
 * @author Jordi Jaspers
 */
public class ApiErrorResponseResource extends ErrorResponseResource {

    /**
     * The error code.
     */
    private String apiErrorCode;

    /**
     * The error reason.
     */
    private String apiErrorReason;

    public ApiErrorResponseResource() {
        this(null);
    }

    public ApiErrorResponseResource(final ApiException apiException) {
        super(apiException);
    }

    /**
     * Getter for error code.
     *
     * @return the error code
     */
    public String getApiErrorCode() {
        return apiErrorCode;
    }

    /**
     * Setter for error code.
     *
     * @param apiErrorCode the error code
     */
    public void setApiErrorCode(final String apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }

    /**
     * Getter for reason.
     *
     * @return the reason
     */
    public String getApiErrorReason() {
        return apiErrorReason;
    }

    /**
     * Setter for reason.
     *
     * @param apiErrorReason the reason
     */
    public void setApiErrorReason(final String apiErrorReason) {
        this.apiErrorReason = apiErrorReason;
    }
}

