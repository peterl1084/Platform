package org.vaadin.platform.sidemenu;

import org.vaadin.platform.ui.menu.SideMenu;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScoped
public class PlatformSideMenuBean extends CssLayout implements SideMenu {
    private static final long serialVersionUID = 1701032494718298908L;

    public PlatformSideMenuBean() {
        addStyleName(ValoTheme.MENU_ROOT);
        addStyleName("platform-sidemenu");
        setHeight(100, Unit.PERCENTAGE);
    }
}
