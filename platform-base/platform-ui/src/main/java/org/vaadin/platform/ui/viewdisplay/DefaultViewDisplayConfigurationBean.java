package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.configuration.DefaultConfiguration;

import com.vaadin.navigator.ViewDisplay;

@DefaultConfiguration
public class DefaultViewDisplayConfigurationBean {
    public Class<? extends ViewDisplay> viewDisplay() {
        return DefaultViewDisplayBean.class;
    }

    public Class<? extends ViewArea> viewArea() {
        return PanelViewAreaBean.class;
    }
}
