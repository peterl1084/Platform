package org.vaadin.platform.ui;

import org.vaadin.platform.config.DefaultConfiguration;
import org.vaadin.platform.ui.viewdisplay.DefaultViewDisplay;
import org.vaadin.platform.ui.viewdisplay.DefaultViewPartFactory;
import org.vaadin.platform.ui.viewdisplay.DefaultViewRoot;

@DefaultConfiguration
public class PlatformUIDefaultConfigurationBean {

    public Class<DefaultViewDisplay> getViewDisplayType() {
        return DefaultViewDisplay.class;
    }

    public Class<DefaultViewPartFactory> getViewPartFactory() {
        return DefaultViewPartFactory.class;
    }

    public Class<DefaultViewRoot> getViewRoot() {
        return DefaultViewRoot.class;
    }
}
