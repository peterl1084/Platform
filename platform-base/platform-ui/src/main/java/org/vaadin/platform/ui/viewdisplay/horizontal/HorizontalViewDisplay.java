package org.vaadin.platform.ui.viewdisplay.horizontal;

import javax.inject.Inject;

import org.vaadin.platform.ui.viewdisplay.AbstractViewDisplay;
import org.vaadin.platform.ui.viewdisplay.PanelViewArea;

import com.vaadin.cdi.UIScoped;

@UIScoped
public class HorizontalViewDisplay extends AbstractViewDisplay {
    private static final long serialVersionUID = -7179525164576536559L;

    @Inject
    public HorizontalViewDisplay(@HorizontalRoot RootComponent root, @PanelViewArea ViewArea viewArea) {
        super(root, viewArea);

        root.addComponent(viewArea);
    }
}
