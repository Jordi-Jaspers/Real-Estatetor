package com.realestatetor.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Shared MapStruct configuration.
 * <p>
 * NOTE: In Micronaut, it used JSR330(aka @Inject) specification to annotate the injectable beans.
 *
 * @author Jordi Jaspers
 */
@MapperConfig(
        componentModel = "jsr330",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SharedMapperConfig {
    // Just an empty interface for the mapper configuration.
}
