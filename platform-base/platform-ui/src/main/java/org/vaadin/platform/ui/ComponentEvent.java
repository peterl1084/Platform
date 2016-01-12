package org.vaadin.platform.ui;

import com.vaadin.ui.Component.Event;

public class ComponentEvent implements UIEvent {

    private Event event;

    public ComponentEvent(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
