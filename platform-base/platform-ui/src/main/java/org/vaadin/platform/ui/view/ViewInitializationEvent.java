package org.vaadin.platform.ui.view;

import org.vaadin.platform.ui.UIEvent;

public class ViewInitializationEvent implements UIEvent {

    private final String viewName;

    public ViewInitializationEvent(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
