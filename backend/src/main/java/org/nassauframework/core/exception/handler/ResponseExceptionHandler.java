package org.nassauframework.core.exception.handler;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import jakarta.inject.Inject;
import org.nassauframework.core.exception.ApiException;
import org.nassauframework.core.exception.HttpException;
import org.nassauframework.core.exception.enricher.ResponseEnricher;
import org.nassauframework.core.exception.resource.ErrorResponseResource;

/**
 * This class creates proper HTTP response bodies for exceptions.
 * <p>
 * NOTE: Micronaut does not have a "ControllerAdvice" Bean.
 *
 * @author Jordi Jaspers
 */
@Controller
public class ResponseExceptionHandler {

    /**
     * The enricher to fill the error responses with a decent body.
     */
    private final ResponseEnricher enricher;

    /**
     * The default constructor.
     *
     * @param enricher the enricher.
     */
    @Inject
    public ResponseExceptionHandler(final ResponseEnricher enricher) {
        this.enricher = enricher;
    }

    /**
     * Handles {@code HttpException} instances.
     * <p>
     * Each {@code HttpException} has an associated {@code HttpStatus} that is used as the response status.
     *
     * @param exception the exception
     * @param request   the current request
     * @return a response entity reflecting the current exception
     */
    @Error(exception = HttpException.class, global = true)
    public MutableHttpResponse<Object> handleHttpException(final HttpRequest<?> request, final HttpException exception) {
        final HttpStatus status = exception.getHttpStatus();
        final Object body = buildErrorResponseBody(exception, status, request);
        return HttpResponse.status(status).body(body);
    }

    /**
     * Handles {@code ApiException} instances.
     * <p>
     * Each {@code ApiException} has an associated {@code HttpStatus} that is used as the response status.
     *
     * @param exception the exception
     * @param request   the current request
     * @return a response entity reflecting the current exception
     */
    @Error(exception = ApiException.class, global = true)
    public MutableHttpResponse<Object> handleApiException(final HttpRequest<?> request, final ApiException exception) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final Object body = buildErrorResponseBody(exception, status, request);
        return HttpResponse.status(status).body(body);
    }

    /**
     * Builds a meaningful response body for the given throwable, HTTP status and request.
     * <p>
     * This method constructs an {@link ErrorResponseResource} and then applies
     * the error response enricher to complete the response.
     *
     * @param throwable the exception
     * @param status    the HTTP status
     * @param request   the current request
     * @return an error response
     */
    private ErrorResponseResource buildErrorResponseBody(
            final Throwable throwable,
            final HttpStatus status,
            final HttpRequest<?> request) {
        final ErrorResponseResource result = new ErrorResponseResource(throwable);
        enricher.enrich(result, throwable, request, status);
        return result;
    }
}

