package com.realestatetor.security;

import com.realestatetor.model.entity.Account;
import com.realestatetor.repository.AccountRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.commons.codec.digest.DigestUtils;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * A Basic username/password authentication provider.
 */
@Singleton
public class UsernamePasswordAuthenticator implements AuthenticationProvider {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordAuthenticator.class);

    @Inject
    private AccountRepository repository;

    /**
     * Authenticates a user with the given request. If a successful authentication is
     * returned, the object must be an instance of {@link Authentication}.
     * <p>
     * Publishers <b>MUST emit cold observables</b>! This method will be called for
     * all authenticators for each authentication request, and it is assumed no work
     * will be done until the publisher is subscribed to.
     *
     * @param httpRequest           The http request
     * @param authenticationRequest The credentials to authenticate
     * @return A publisher that emits 0 or 1 responses
     */
    @Override
    public Publisher<AuthenticationResponse> authenticate(final HttpRequest<?> httpRequest,
            final AuthenticationRequest<?, ?> authenticationRequest) {
        return Flux.create(emitter -> {
            final String email = (String) authenticationRequest.getIdentity();
            final String password = (String) authenticationRequest.getSecret();
            final Account user = repository.findByEmailAndPassword(email, DigestUtils.sha256Hex(password)).block();

            if (nonNull(user)) {
                emitter.next(AuthenticationResponse.success(user.getEmail(), List.of(user.getPermission().getLevel().getValue())));
                emitter.complete();
            } else {
                LOGGER.info("Check if you correctly filled in the username or password.", AuthenticationResponse.exception());
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }
}
