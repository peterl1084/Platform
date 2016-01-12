package org.vaadin.platform.ui.view;

import org.vaadin.platform.ui.UIEvent;

public class ViewEnterEvent implements UIEvent {

    private final String viewName;
    private final String parameters;

    public ViewEnterEvent(String viewName, String parameters) {
        this.viewName = viewName;
        this.parameters = parameters;
    }

    public String getViewName() {
        return viewName;
    }

    public String getParameters() {
        return parameters;
    }
}
