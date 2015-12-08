package org.vaadin.platform.ui.viewdisplay;

import java.util.Optional;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.vaadin.platform.config.ConfigurationBean;

import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;

@UIScoped
public class DefaultViewDisplay implements PlatformViewDisplay {
    private static final long serialVersionUID = 6108890114881391778L;

    private ViewPartFactory partFactory;

    @Inject
    public DefaultViewDisplay(Instance<ViewPartFactory> factoryInitializer, ConfigurationBean configuration) {
        partFactory = buildPartFactory(factoryInitializer, configuration);
    }

    private ViewPartFactory buildPartFactory(Instance<ViewPartFactory> factoryInitializer,
            ConfigurationBean configuration) {
        Optional<Class<? extends ViewPartFactory>> viewPartFactoryOptional = configuration
                .findConfiguredType(ViewPartFactory.class);
        if (!viewPartFactoryOptional.isPresent()) {
            throw new RuntimeException("Failed to find configuration for ViewPartFactory bean");
        }

        Instance<? extends ViewPartFactory> selectedViewPartFactory = factoryInitializer
                .select(viewPartFactoryOptional.get());
        if (selectedViewPartFactory.isUnsatisfied()) {
            throw new RuntimeException("Could not find ViewPartFactory implementation "
                    + viewPartFactoryOptional.get().getCanonicalName());
        } else if (selectedViewPartFactory.isAmbiguous()) {
            throw new RuntimeException("Defined ViewPartFactory implementation "
                    + viewPartFactoryOptional.get().getCanonicalName() + " is ambiguous");
        }

        return factoryInitializer.get();
    }

    @Override
    public void showView(View view) {
        // TODO Auto-generated method stub

    }

    @Override
    public ViewRootComponent getRoot() {
        return partFactory.produceViewPart(ViewRootComponent.class);
    }

    @Override
    public ViewAreaComponent getViewArea() {
        // TODO Auto-generated method stub
        return null;
    }

}
