package org.vaadin.platform.ui.viewdisplay;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.vaadin.platform.config.ConfigurationProvider;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewPart;

import com.vaadin.cdi.UIScoped;

@UIScoped
public class DefaultViewPartFactory implements ViewPartFactory {

    @Any
    @Inject
    private Instance<ViewPart> viewPartInstantiator;

    @Inject
    private ConfigurationProvider configuration;

    @Override
    public <T extends ViewPart> T produceViewPart(Class<T> partType, Annotation... qualifiers) {
        Optional<Class<? extends T>> configuredType = configuration.findConfiguredType(partType);
        if (!configuredType.isPresent()) {
            throw new RuntimeException("Could not find configuration for type " + partType.getCanonicalName());
        }

        Instance<? extends T> typeSelectedInstantiator = viewPartInstantiator.select(configuredType.get());
        if (typeSelectedInstantiator.isUnsatisfied()) {
            throw new RuntimeException("Could not find any managed bean for type " + partType.getCanonicalName());
        }

        if (typeSelectedInstantiator.isAmbiguous()) {
            String types = StreamSupport.stream(typeSelectedInstantiator.spliterator(), false)
                    .map(type -> type.getClass().getCanonicalName()).collect(Collectors.joining(","));
            throw new RuntimeException("Found more than one managed bean for type " + partType.getCanonicalName()
                    + ", the types are: " + types);
        }

        return typeSelectedInstantiator.get();
    }
}
