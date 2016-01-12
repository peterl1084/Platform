package org.vaadin.platform.ui.view;

public interface CompositionView {

    default String getViewName() {
        if (getClass().isAnnotationPresent(ViewComposition.class)) {
            return getClass().getAnnotation(ViewComposition.class).name();
        }

        return null;
    }
}
