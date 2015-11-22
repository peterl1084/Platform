package org.vaadin.platform.ui.viewdisplay;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.vaadin.platform.config.Configuration;
import org.vaadin.platform.ui.viewdisplay.horizontal.HorizontalViewDisplay;

@ApplicationScoped
public class ViewDisplayProvider {

    @Any
    @Inject
    private Instance<PViewDisplay> instantiator;

    @Inject
    private Configuration config;

    public PViewDisplay provideViewDisplay() {
        Class<? extends PViewDisplay> viewDisplayType = config.findConfiguredType(PViewDisplay.class)
                .orElse(HorizontalViewDisplay.class);

        Instance<? extends PViewDisplay> selector = instantiator.select(viewDisplayType);
        if (selector.isUnsatisfied()) {
            throw new RuntimeException("No view display beans  found with configured type " + viewDisplayType);
        }
        if (selector.isAmbiguous()) {
            String types = StreamSupport.stream(instantiator.spliterator(), false)
                    .map(instance -> instance.getClass().getSimpleName()).collect(Collectors.joining(","));
            throw new RuntimeException("Found multiple view display implementations with same configured type "
                    + viewDisplayType + ": " + types);
        }

        return instantiator.get();
    }
}
