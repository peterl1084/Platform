package org.vaadin.platform.ui;

import org.vaadin.platform.configuration.DefaultConfiguration;
import org.vaadin.platform.ui.viewdisplay.PanelViewArea;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewAreaComponent;
import org.vaadin.platform.ui.viewdisplay.UserViewDisplay;

@DefaultConfiguration
public class DefaultUIConfigurationBean {

    public Class<UserViewDisplay> getViewDisplayType() {
        return UserViewDisplay.class;
    }

    public Class<? extends ViewAreaComponent> getViewArea() {
        return PanelViewArea.class;
    }
}
