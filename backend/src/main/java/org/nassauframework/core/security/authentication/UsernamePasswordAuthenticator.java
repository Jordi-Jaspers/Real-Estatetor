package org.nassauframework.core.security.authentication;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.apache.commons.codec.digest.DigestUtils;
import org.nassauframework.core.exception.IllegalStateException;
import org.nassauframework.core.security.model.entity.Account;
import org.nassauframework.core.security.model.entity.AssignedRole;
import org.nassauframework.core.security.model.entity.Role;
import org.nassauframework.core.security.repository.AccountRepository;
import org.nassauframework.core.security.repository.AssignedPermissionRepository;
import org.nassauframework.core.security.repository.AssignedRoleRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * A Basic username/password authentication provider, that uses the {@link AccountRepository} to authenticate the user that tries to login.
 * Afterwards, the {@link AssignedRoleRepository} is used to retrieve the assigned roles for the user, and it retrieves the
 * assigned permissions for the roles of that {@link Account}. The returned Bearer token will determine what the user is allowed to do.
 *
 * @author Jordi Jaspers
 */
@Singleton
public class UsernamePasswordAuthenticator implements AuthenticationProvider {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordAuthenticator.class);

    /**
     * The account repository.
     */
    @Inject
    private AccountRepository accountRepository;

    /**
     * The permission lookup repository.
     */
    @Inject
    private AssignedPermissionRepository assignedPermissionRepository;

    /**
     * The role lookup repository.
     */
    @Inject
    private AssignedRoleRepository assignedRoleRepository;


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

            final Account account = accountRepository.findByEmail(email).block();
            if (nonNull(account) && isValidPassword(account, password)) {
                emitter.next(AuthenticationResponse.success(account.getEmail(), getPermissions(account)));
                emitter.complete();
            } else {
                LOGGER.info("Check if you correctly filled in the username or password.", AuthenticationResponse.exception());
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }

    /**
     * Checks if the given password is valid for the given account.
     *
     * @param account  The account
     * @param password The password
     * @return True if the password is valid
     */
    private Boolean isValidPassword(final Account account, final String password) {
        return account.getPassword().equals(DigestUtils.sha256Hex(password + account.getSalt()));
    }

    /**
     * Gets the permissions for the given role.
     *
     * @param account the account
     * @return The permissions
     */
    private List<String> getPermissions(final Account account) {
        final List<Role> roles = assignedRoleRepository.findByAccount(account)
                .map(AssignedRole::getRole)
                .collectList().block();
        if (nonNull(roles)) {
            return assignedPermissionRepository.findByRoleInList(roles)
                    .map(assignedPermission -> assignedPermission.getPermission().getName())
                    .collectList().block();
        }

        throw new IllegalStateException("No roles found for account " + account.getEmail());
    }
}
