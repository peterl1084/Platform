package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewAreaComponent;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

public class PanelViewArea extends Panel implements ViewAreaComponent {
    private static final long serialVersionUID = 8793364361651660486L;

    public PanelViewArea() {
        setSizeFull();

        addStyleName(ValoTheme.PANEL_BORDERLESS);
    }

    @Override
    public void setView(View view) {
        setContent((Component) view);
    }
}
