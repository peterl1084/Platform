package org.vaadin.platform.ui.viewdisplay.split;

import org.vaadin.platform.ui.viewdisplay.AbstractViewDisplay;
import org.vaadin.platform.ui.viewdisplay.PanelViewAreaComponent;

import com.vaadin.cdi.UIScoped;

@UIScoped
public class VerticalSplitViewDisplay extends AbstractViewDisplay {
    private static final long serialVersionUID = 1959956371487702902L;

    public VerticalSplitViewDisplay() {
        super(new VerticalSplitRootComponent(), new PanelViewAreaComponent());
    }
}
