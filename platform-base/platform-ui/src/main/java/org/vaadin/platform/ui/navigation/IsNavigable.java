package org.vaadin.platform.ui.navigation;

import org.vaadin.platform.ui.view.CompositionView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public interface IsNavigable extends CompositionView, View {

    @Override
    default void enter(ViewChangeEvent event) {
    }
}
