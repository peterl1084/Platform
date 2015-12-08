package org.vaadin.platform.ui.viewdisplay;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.vaadin.platform.config.ConfigurationBean;

@ApplicationScoped
public class ViewDisplayProvider {

    @Any
    @Inject
    private Instance<PlatformViewDisplay> instantiator;

    @Inject
    private ConfigurationBean config;

    public PlatformViewDisplay provideViewDisplay() {
        Optional<Class<? extends PlatformViewDisplay>> configuredViewDisplayType = config
                .findConfiguredType(PlatformViewDisplay.class);
        if (!configuredViewDisplayType.isPresent()) {
            throw new RuntimeException(
                    "Could not find configuration for view display, please provide a method to configuration bean with return type of Class<? extends PViewDisplay>");
        }

        Instance<? extends PlatformViewDisplay> selector = instantiator.select(configuredViewDisplayType.get());
        if (selector.isUnsatisfied()) {
            throw new RuntimeException(
                    "No view display beans  found with configured type " + configuredViewDisplayType.get());
        }
        if (selector.isAmbiguous()) {
            String types = StreamSupport.stream(instantiator.spliterator(), false)
                    .map(instance -> instance.getClass().getSimpleName()).collect(Collectors.joining(","));
            throw new RuntimeException("Found multiple view display implementations with same configured type "
                    + configuredViewDisplayType.get() + ": " + types);
        }

        return selector.get();
    }
}
