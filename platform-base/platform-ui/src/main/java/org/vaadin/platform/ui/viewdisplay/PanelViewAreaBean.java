package org.vaadin.platform.ui.viewdisplay;

import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

@UIScoped
public class PanelViewAreaBean extends Panel implements ViewArea {
    private static final long serialVersionUID = 8793364361651660486L;

    public PanelViewAreaBean() {
        setSizeFull();

        addStyleName(ValoTheme.PANEL_BORDERLESS);
    }

    @Override
    public void showView(View view) {
        setContent((Component) view);
    }
}
