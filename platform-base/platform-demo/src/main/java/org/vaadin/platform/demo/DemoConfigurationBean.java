package org.vaadin.platform.demo;

import org.vaadin.platform.config.SpecializedConfiguration;
import org.vaadin.platform.sidemenu.PlatformSideMenuBean;
import org.vaadin.platform.ui.viewdisplay.SideMenu;

@SpecializedConfiguration
public class DemoConfigurationBean {

    public Class<? extends SideMenu> getSideMenu() {
        return PlatformSideMenuBean.class;
    }
}
