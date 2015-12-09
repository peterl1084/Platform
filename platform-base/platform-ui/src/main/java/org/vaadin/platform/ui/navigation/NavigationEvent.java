package org.vaadin.platform.ui.navigation;

public class NavigationEvent {
    public enum EventType {
        PROGRAMMATIC,
        FRAGMENT;
    }

    private final String view;
    private final String parameters;
    private final EventType type;

    public NavigationEvent(String view, String parameters, EventType type) {
        this.view = view;
        this.parameters = parameters;
        this.type = type;
    }

    public String getView() {
        return view;
    }

    public String getParameters() {
        return parameters;
    }

    public EventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + ", type: " + type + ", view: " + view + ", params: " + parameters;
    }
}
