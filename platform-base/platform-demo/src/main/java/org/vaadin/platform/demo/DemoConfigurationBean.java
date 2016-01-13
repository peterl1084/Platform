package org.vaadin.platform.demo;

import org.vaadin.platform.configuration.SpecializedConfiguration;
import org.vaadin.platform.sidemenu.Menu;
import org.vaadin.platform.sidemenu.SingleLevelFlexMenu;

@SpecializedConfiguration
public class DemoConfigurationBean {

    public Class<? extends Menu> getSideMenu() {
        return SingleLevelFlexMenu.class;
    }

}
