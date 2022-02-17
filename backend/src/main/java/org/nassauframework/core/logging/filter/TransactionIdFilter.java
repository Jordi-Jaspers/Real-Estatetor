package org.nassauframework.core.logging.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import org.apache.commons.lang3.StringUtils;
import org.nassauframework.core.logging.kibana.KibanaLogFieldNames;
import org.nassauframework.core.logging.kibana.KibanaLogFields;
import org.nassauframework.core.logging.model.TransactionId;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.nassauframework.core.logging.model.LoggingAttributes.TX_ID_HEADER;

/**
 * A utility filter that will log all the requests and responses to the logger in a readable fashion.
 * This filter makes debugging purposes easier.
 * <p>
 * Also, will add Tx id to the request header in which we can trace the request with its response.
 *
 * @author Jordi Jaspers
 */
@Filter(Filter.MATCH_ALL_PATTERN)
public class TransactionIdFilter implements HttpServerFilter {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionIdFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(final HttpRequest<?> request, final ServerFilterChain chain) {
        final UUID uuid = resolveHeader(request);
        final Publisher<MutableHttpResponse<?>> result;

        // If Micronaut Security rejected the request simpy do nothing
        //if (request.getAttribute(SecurityFilter.REJECTION).isPresent()) {
        //    LOGGER.info("Request was rejected by Micronaut Security");
        //    return chain.proceed(request);
        //}

        try {
            result = Flux.from(chain.proceed(request))
                    .doOnRequest(r -> {
                        TransactionId.set(uuid);
                        KibanaLogFields.tag(KibanaLogFieldNames.TX_ID, TransactionId.get());

                        request.mutate().header(TX_ID_HEADER, TransactionId.get());
                        LOGGER.info("Transaction id set on the request to '{}'.", TransactionId.get());

                        logRequest(request);
                    }).switchMap(serverResponse -> {
                        TransactionId.set(uuid);
                        KibanaLogFields.tag(KibanaLogFieldNames.TX_ID, TransactionId.get());

                        if (!serverResponse.getHeaders().contains(TX_ID_HEADER) || StringUtils.isBlank(
                                serverResponse.getHeaders().get(TX_ID_HEADER))) {
                            LOGGER.info("Set '{}' with value '{}'", TX_ID_HEADER, TransactionId.get());
                            serverResponse.header(TX_ID_HEADER, TransactionId.get());
                        }

                        logResponse(serverResponse);

                        return Flux.just(serverResponse);
                    });
        } finally {
            TransactionId.remove();
            KibanaLogFields.clear();
        }
        return result;
    }


    /**
     * Resolve the UUID from the header with name {@code headerName}.
     * <p>
     * If the value is not set in the {@code request} then a new UUID will be generated.
     */
    public UUID resolveHeader(final HttpRequest<?> request) {
        UUID txId = null;
        final String txHeaderValue = request.getHeaders().get(TX_ID_HEADER);

        if (StringUtils.isNotBlank(txHeaderValue)) {
            try {
                LOGGER.info("Found header '{}' with value '{}' in request.", TX_ID_HEADER, txHeaderValue);
                txId = UUID.fromString(txHeaderValue);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Could not create UUID from header.", e);
            }
        } else {
            txId = UUID.randomUUID();
            LOGGER.debug("Generated new UUID '{}'.", txId);
        }
        return txId;
    }

    /**
     * Logs the request.
     *
     * @param request The request to log.
     */
    private void logRequest(HttpRequest<?> request) {
        // Declaring all the variables needed to log the request and response.
        final String path = request.getPath();
        final HttpMethod httpMethod = request.getMethod();
        final Optional<String> requestBody = request.getBody(String.class);
        final Iterator<Map.Entry<String, List<String>>> requestHeaders = request.getHeaders().iterator();

        // Logging the request
        final StringBuilder requestString = new StringBuilder();
        requestString
                .append("Request is: \n\n")
                .append(httpMethod).append(" ")
                .append(path).append("\n\n");

        if (!request.getParameters().isEmpty()) {
            final HttpParameters parameters = request.getParameters();
            requestString.append("Request params: \n");
            for (Map.Entry<String, List<String>> entry : parameters) {
                requestString.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
            requestString.append("\n\n");
        }

        while (requestHeaders.hasNext()) {
            Map.Entry<String, List<String>> header = requestHeaders.next();
            requestString
                    .append(header.getKey()).append(": ")
                    .append(header.getValue()).append("\n");
        }
        requestString.append("\nBody: ");
        requestBody.ifPresentOrElse(requestString::append, () -> requestString.append("{}"));
        requestBody.ifPresent(requestString::append);
        LOGGER.info(requestString.toString());
    }

    /**
     * Logs the response.
     *
     * @param serverResponse The response to log.
     */
    private void logResponse(MutableHttpResponse<?> serverResponse) {
        final Optional<?> optionalBody = serverResponse.getBody();
        final HttpStatus status = serverResponse.getStatus();
        final Iterator<Map.Entry<String, List<String>>> responseHeaders = serverResponse.getHeaders().iterator();

        // Logging the response.
        final StringBuilder responseString = new StringBuilder();
        responseString
                .append("Response is - ")
                .append(status.getCode())
                .append(" ")
                .append(status)
                .append("\n\n");

        responseString.append("Headers: \n");
        while (responseHeaders.hasNext()) {
            Map.Entry<String, List<String>> header = responseHeaders.next();
            responseString
                    .append(header.getKey()).append(": ")
                    .append(header.getValue()).append("\n");
        }
        responseString.append("\n");

        if (optionalBody.isPresent()) {
            final Object responseBody = optionalBody.get();
            final ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try {
                responseString.append("Body: ").append(objectWriter.writeValueAsString(responseBody));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            responseString.append("Body: {}");
        }
        LOGGER.info(responseString.toString());
    }
}
