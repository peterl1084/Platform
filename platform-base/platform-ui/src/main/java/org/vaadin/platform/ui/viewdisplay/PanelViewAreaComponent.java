package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.ui.viewdisplay.PViewDisplay.ViewArea;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.Panel;

@UIScoped
@PanelViewArea
public class PanelViewAreaComponent extends Panel implements ViewArea {
    private static final long serialVersionUID = -500334809101333419L;

    public PanelViewAreaComponent() {
        super();
    }
}
