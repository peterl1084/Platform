package org.vaadin.platform.ui;

import com.vaadin.ui.Component;

/**
 * IsCastableComponent is default interface for all types of interfaces that
 * don't want to expose Component interface methods but rather have a method for
 * casting to component. This interface should be implemented only by such a
 * hierarchy that can be cast to Vaadin Component.
 */
public interface IsCastableComponent {

    /**
     * @return this object as Component.
     */
    default Component asComponent() {
        return Component.class.cast(this);
    }
}
