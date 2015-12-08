package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewRootComponent;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class DefaultViewRoot extends CustomComponent implements ViewRootComponent {
    private static final long serialVersionUID = -6828242758207566634L;

    public DefaultViewRoot() {
        setCompositionRoot(new Label("Hello!"));
    }

}
