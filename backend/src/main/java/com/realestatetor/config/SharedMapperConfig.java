package com.realestatetor.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Shared MapStruct configuration.
 */
@MapperConfig(
        componentModel = "jsr330",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SharedMapperConfig {
    // Just an empty interface for the mapper configuration.
}
