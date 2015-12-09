package org.vaadin.platform.demo;

import org.vaadin.platform.configuration.SpecializedConfiguration;
import org.vaadin.platform.sidemenu.PlatformSideMenuBean;
import org.vaadin.platform.ui.menu.SideMenu;

@SpecializedConfiguration
public class DemoConfigurationBean {

    public Class<? extends SideMenu> getSideMenu() {
        return PlatformSideMenuBean.class;
    }
}
