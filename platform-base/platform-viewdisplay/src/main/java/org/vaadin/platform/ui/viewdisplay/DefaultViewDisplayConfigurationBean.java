package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.configuration.DefaultConfiguration;

@DefaultConfiguration
public class DefaultViewDisplayConfigurationBean {
    public Class<? extends PlatformViewDisplay> viewDisplay() {
        return DefaultViewDisplayBean.class;
    }

    public Class<? extends ViewArea> viewArea() {
        return PanelViewAreaBean.class;
    }
}
