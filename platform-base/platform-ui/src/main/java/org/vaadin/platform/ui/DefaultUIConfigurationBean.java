package org.vaadin.platform.ui;

import org.vaadin.platform.configuration.DefaultConfiguration;
import org.vaadin.platform.ui.navigation.DefaultFragmentResolverBean;
import org.vaadin.platform.ui.navigation.UriFragmentResolver;
import org.vaadin.platform.ui.viewdisplay.PanelViewArea;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewAreaComponent;
import org.vaadin.platform.ui.viewdisplay.UserViewDisplay;

import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.ViewProvider;

@DefaultConfiguration
public class DefaultUIConfigurationBean {

    public Class<UserViewDisplay> getViewDisplayType() {
        return UserViewDisplay.class;
    }

    public Class<? extends ViewAreaComponent> getViewArea() {
        return PanelViewArea.class;
    }

    public Class<? extends ViewProvider> getViewProvider() {
        return CDIViewProvider.class;
    }

    public Class<? extends UriFragmentResolver> getFragmentResolver() {
        return DefaultFragmentResolverBean.class;
    }
}
