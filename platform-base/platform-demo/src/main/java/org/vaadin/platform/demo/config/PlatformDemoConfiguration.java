package org.vaadin.platform.demo.config;

import org.vaadin.platform.config.PlatformUserConfiguration;
import org.vaadin.platform.ui.viewdisplay.split.HorizontalSplitViewDisplay;

@PlatformUserConfiguration
public class PlatformDemoConfiguration {

    public Class<HorizontalSplitViewDisplay> getViewDisplayType() {
        return HorizontalSplitViewDisplay.class;
    }
}
