package org.vaadin.platform.ui;

import javax.enterprise.context.ApplicationScoped;

import org.vaadin.platform.config.PlatformDefaultConfiguration;
import org.vaadin.platform.ui.viewdisplay.horizontal.HorizontalViewDisplay;

@PlatformDefaultConfiguration
@ApplicationScoped
public class PlatformDefaultConfigurationBean {

    public Class<HorizontalViewDisplay> getViewDisplayType() {
        return HorizontalViewDisplay.class;
    }
}
