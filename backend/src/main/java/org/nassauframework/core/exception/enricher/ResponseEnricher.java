package org.nassauframework.core.exception.enricher;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import jakarta.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.nassauframework.core.exception.ApiException;
import org.nassauframework.core.exception.handler.ResponseExceptionHandler;
import org.nassauframework.core.exception.resource.ApiErrorResponseResource;
import org.nassauframework.core.exception.resource.ErrorResponseResource;

/**
 * This class defines a way to enrich the error response with values applicable to the given situation.
 * <p>
 * The situation in this case consists of the exception, the original request and the http status.
 * <p>
 * A {@link ResponseExceptionHandler} has a list of {@code ResponseEnricher}, which will make sure that
 * all relevant information is captured in the error response.
 *
 * @author Jordi Jaspers
 */
@Singleton
public class ResponseEnricher {

    /**
     * Performs the enrichment of the error response resource.
     *
     * @param result    the error response resource
     * @param throwable the exception that was raised
     * @param request   the original web request
     * @param status    the http status that will be returned
     */
    public void enrich(final ErrorResponseResource result, final Throwable throwable, final HttpRequest<?> request,
            final HttpStatus status) {
        final String message = throwable.getMessage();
        if (!StringUtils.isEmpty(message)) {
            result.setErrorMessage(message);
        }

        if (request != null) {
            result.setUri(request.getUri().toString());
            result.setQuery(request.getUri().getQuery());
            result.setMethod(request.getMethod().toString());
            if (request.getContentType().isPresent()) {
                final MediaType contentType = request.getContentType().get();
                result.setContentType(contentType.getType());
            }
        }

        result.setStatusCode(status.getCode());
        result.setStatusMessage(status.getReason());

        if (throwable instanceof ApiException && result instanceof ApiErrorResponseResource) {
            ((ApiErrorResponseResource) result).setApiErrorCode(((ApiException) throwable).getErrorCode());
            ((ApiErrorResponseResource) result).setApiErrorReason(((ApiException) throwable).getReason());
        }
    }
}
