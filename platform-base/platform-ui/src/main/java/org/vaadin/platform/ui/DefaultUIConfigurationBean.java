package org.vaadin.platform.ui;

import org.vaadin.platform.configuration.DefaultConfiguration;
import org.vaadin.platform.ui.navigation.PlatformViewProvider;
import org.vaadin.platform.ui.navigation.UriFragmentResolver;
import org.vaadin.platform.ui.navigation.UriFragmentResolverBean;

import com.vaadin.navigator.ViewProvider;

/**
 * DefaultUIConfigurationBean provides default bean implementations for
 * interface typed injection points.
 */
@DefaultConfiguration
class DefaultUIConfigurationBean {

    // no external instantiation
    private DefaultUIConfigurationBean() {
    }

    public Class<? extends ViewProvider> viewProvider() {
        return PlatformViewProvider.class;
    }

    public Class<? extends UriFragmentResolver> uriFragmentResolver() {
        return UriFragmentResolverBean.class;
    }
}
