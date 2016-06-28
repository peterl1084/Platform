package org.vaadin.platform.demo;

import org.vaadin.platform.configuration.InitialView;
import org.vaadin.platform.configuration.SpecializedConfiguration;
import org.vaadin.platform.demo.view.customer.CustomerView;
import org.vaadin.platform.sidemenu.MainMenu;
import org.vaadin.platform.sidemenu.ValoMainMenuBean;
import org.vaadin.platform.ui.navigation.IsNavigable;

@SpecializedConfiguration
public class DemoConfigurationBean {

    public Class<? extends MainMenu> getSideMenu() {
        return ValoMainMenuBean.class;
    }

    @InitialView
    public Class<? extends IsNavigable> getInitialView() {
        return CustomerView.class;
    }

}
