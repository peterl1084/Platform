package org.vaadin.platform.ui.viewdisplay.horizontal;

import org.vaadin.platform.ui.viewdisplay.PViewDisplay.RootComponent;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.HorizontalLayout;

@UIScoped
@HorizontalRoot
public class HorizontalRootComponent extends HorizontalLayout implements RootComponent {
    private static final long serialVersionUID = 4205962548722605887L;

    public HorizontalRootComponent() {
        super();
    }
}
