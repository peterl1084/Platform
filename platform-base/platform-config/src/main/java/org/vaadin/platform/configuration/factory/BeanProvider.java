package org.vaadin.platform.configuration.factory;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * BeanProvider allows to look up configured beans defined
 * in @DefaultConfiguration and @SpecializedConfiguration and provides a
 * contextual reference to them according to how scopes are defined in the
 * corresponding beans.
 */
public interface BeanProvider {

    /**
     * Looks up configured bean which is a subtype of given beanType. If such
     * bean is unambiguous and defined in any configuration bean it will be
     * returned. Qualifier annotations can be used to determine the type more
     * accurately if multiple options are available.
     * 
     * @param beanType
     * @param qualifiers
     * @return bean
     * @throws RuntimeException
     *             if no such bean is found or if discovered beans are ambiguous
     */
    <T> T getReference(Class<T> beanType, Annotation... qualifiers);

    /**
     * Looks up configured bean which is a subtype of given beanType. If such
     * bean is unambiguous and defined in any configuration bean it will be
     * returned. Qualifier annotations can be used to determine the type more
     * accurately if multiple options are available. If no such bean is found or
     * multiple ambiguous beans are found Empty optional is returned.
     * 
     * @param beanType
     * @param qualifiers
     * @return bean
     */
    <T> Optional<T> getOptionalReference(Class<T> beanType, Annotation... qualifiers);
}
