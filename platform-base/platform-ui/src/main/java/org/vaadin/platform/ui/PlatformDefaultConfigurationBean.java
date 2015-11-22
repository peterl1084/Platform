package org.vaadin.platform.ui;

import javax.enterprise.context.ApplicationScoped;

import org.vaadin.platform.config.PlatformDefaultConfiguration;
import org.vaadin.platform.ui.viewdisplay.PViewDisplay;
import org.vaadin.platform.ui.viewdisplay.horizontal.HorizontalViewDisplay;

@PlatformDefaultConfiguration
@ApplicationScoped
public class PlatformDefaultConfigurationBean {

    public Class<? extends PViewDisplay> getViewDisplayType() {
        return HorizontalViewDisplay.class;
    }
}
