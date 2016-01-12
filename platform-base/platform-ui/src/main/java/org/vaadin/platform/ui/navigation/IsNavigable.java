package org.vaadin.platform.ui.navigation;

import org.vaadin.platform.ui.view.HasPresenter;
import org.vaadin.platform.ui.view.IsEnterAware;
import org.vaadin.platform.ui.view.ViewComposition;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

public interface IsNavigable extends View {

    @Override
    default void enter(ViewChangeEvent event) {
        if (this instanceof HasPresenter) {
            HasPresenter presenterHolder = (HasPresenter) this;
            Object presenter = presenterHolder.getPresenter();
            if (presenter instanceof IsEnterAware) {
                IsEnterAware enterAwarePresenter = (IsEnterAware) presenter;
                enterAwarePresenter.onEnter(event.getParameters());
            }
        }
    }

    default String getViewName() {
        if (getClass().isAnnotationPresent(ViewComposition.class)) {
            return getClass().getAnnotation(ViewComposition.class).name();
        }

        return null;
    }
}
