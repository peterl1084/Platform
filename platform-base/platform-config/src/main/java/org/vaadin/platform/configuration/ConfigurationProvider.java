package org.vaadin.platform.configuration;

import java.util.Optional;

/**
 * ConfigurationProvider allows accessing type information from all default- and
 * specializing configuration beans annotated with @DefaultConfiguration
 * and @SpecializingConfiguration
 */
public interface ConfigurationProvider {

    /**
     * Finds configured bean types expected to be sub types of given baseType
     * from all beans annotated with @DefaultConfiguration
     * and @SpecializingConfiguration qualifiers. If @SpecializingConfiguration
     * defines for same super type different sub type than
     * what @DefaultConfiguration did, the specialized option will be used. If
     * no configuration providing a subtype for given baseType is found empty
     * Optional is returned.
     * 
     * @param baseType
     * @return Optional of configured subType of given baseType or empty
     *         Optional if no such configuration exists.
     */
    <T> Optional<Class<? extends T>> findConfiguredType(Class<T> baseType);
}
