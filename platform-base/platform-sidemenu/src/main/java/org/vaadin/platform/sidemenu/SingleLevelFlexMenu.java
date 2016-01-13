package org.vaadin.platform.sidemenu;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.themes.ValoTheme;

@UIScoped
public class SingleLevelFlexMenu extends MenuBase implements Menu {
    private static final long serialVersionUID = 1701032494718298908L;

    public SingleLevelFlexMenu() {
        addStyleName(ValoTheme.MENU_ROOT);
        addStyleName("platform-sidemenu");
        setHeight(100, Unit.PERCENTAGE);
    }
}
