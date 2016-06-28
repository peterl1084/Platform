package org.vaadin.platform.configuration.bean;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.vaadin.platform.configuration.ConfigurationProvider;

@ApplicationScoped
class BeanProviderBean implements BeanProvider {

    @Inject
    private Instance<Object> instantiator;

    @Inject
    private ConfigurationProvider configuration;

    @Override
    public <T> T getReference(Class<T> beanType, Annotation... qualifiers) {
        Optional<Class<? extends T>> configured = configuration.findConfiguredType(beanType);
        if (!configured.isPresent()) {
            throw new RuntimeException("No configured type available " + beanType.getCanonicalName());
        }

        Class<? extends T> type = configured.get();
        Instance<? extends T> selectedInstantiator = qualifiers == null ? instantiator.select(type)
                : instantiator.select(type, qualifiers);

        String usedAnnotations = Arrays.asList(qualifiers).stream()
                .map(annotation -> annotation.annotationType().getCanonicalName()).collect(Collectors.joining(","));

        if (selectedInstantiator.isUnsatisfied()) {
            throw new RuntimeException("Could not find bean reference for: " + beanType.getCanonicalName()
                    + " with annotations: " + usedAnnotations);
        }

        if (selectedInstantiator.isAmbiguous()) {
            throw new RuntimeException("Ambiguous bean references found for: " + beanType.getCanonicalName()
                    + " with annotations: " + usedAnnotations);
        }

        return selectedInstantiator.get();
    }

    @Override
    public <T> Optional<T> getOptionalReference(Class<T> beanType, Annotation... qualifiers) {
        Optional<Class<? extends T>> configured = configuration.findConfiguredType(beanType, qualifiers);
        if (!configured.isPresent()) {
            return Optional.empty();
        }

        Class<? extends T> type = configured.get();
        Instance<? extends T> selectedInstantiator = qualifiers == null ? instantiator.select(type)
                : instantiator.select(type, qualifiers);

        if (selectedInstantiator.isUnsatisfied()) {
            return Optional.empty();
        }
        if (selectedInstantiator.isAmbiguous()) {
            return Optional.empty();
        }

        return Optional.of(selectedInstantiator.get());
    }
}
