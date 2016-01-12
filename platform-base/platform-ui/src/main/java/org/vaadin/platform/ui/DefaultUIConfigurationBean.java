package org.vaadin.platform.ui;

import org.vaadin.platform.configuration.DefaultConfiguration;
import org.vaadin.platform.ui.navigation.PlatformViewProvider;
import org.vaadin.platform.ui.navigation.UriFragmentResolver;
import org.vaadin.platform.ui.navigation.UriFragmentResolverBean;
import org.vaadin.platform.ui.viewdisplay.DefaultViewDisplayBean;
import org.vaadin.platform.ui.viewdisplay.PanelViewAreaBean;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;
import org.vaadin.platform.ui.viewdisplay.ViewArea;

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

    public Class<? extends PlatformViewDisplay> viewDisplay() {
        return DefaultViewDisplayBean.class;
    }

    public Class<? extends ViewArea> viewArea() {
        return PanelViewAreaBean.class;
    }

    public Class<? extends ViewProvider> viewProvider() {
        return PlatformViewProvider.class;
    }

    public Class<? extends UriFragmentResolver> uriFragmentResolver() {
        return UriFragmentResolverBean.class;
    }
}
