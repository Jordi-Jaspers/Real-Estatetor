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
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A filter that will lgo all the requests and responses to the logger.
 * Also, will add Tx id to the request header in which we can trace the request with its response.
 *
 * @author Jordi Jaspers
 */
@Filter("/**")
public class RequestResponseLoggerFilter implements HttpServerFilter {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggerFilter.class);

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {

        // If Micronaut Security rejected the request simpy do nothing
        //if (request.getAttribute(SecurityFilter.REJECTION).isPresent()) {
        //    LOGGER.info("Request was rejected by Micronaut Security");
        //    return chain.proceed(request);
        //}

        return Flux.from(chain.proceed(request)).switchMap(serverResponse -> {
            // Declaring all the variables needed to log the request and response.
            final Optional<?> optionalBody = serverResponse.getBody();
            final HttpStatus status = serverResponse.getStatus();
            final Optional<String> requestBody = request.getBody(String.class);
            final Iterator<Map.Entry<String, List<String>>> headers = request.getHeaders().iterator();
            final String path = request.getPath();
            final HttpMethod httpMethod = request.getMethod();

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
                    requestString.append(entry.getKey()).append(" = ").append(entry.getValue());
                }
                requestString.append("\n\n");
            }

            while (headers.hasNext()) {
                Map.Entry<String, List<String>> header = headers.next();
                requestString
                        .append(header.getKey()).append(": ")
                        .append(header.getValue()).append("\n");
            }
            requestString.append("\nBody: ");
            requestBody.ifPresentOrElse(requestString::append, () -> requestString.append("{}"));
            requestBody.ifPresent(requestString::append);
            LOGGER.info(requestString.toString());

            // Logging the response.
            final StringBuilder responseString = new StringBuilder();
            responseString
                    .append("Response is - ")
                    .append(status.getCode())
                    .append(" ")
                    .append(status)
                    .append("\n\n");

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

            return Flux.just(serverResponse);
        });
    }
}
