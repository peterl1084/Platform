package org.vaadin.platform.ui.navigation;

import org.vaadin.platform.ui.view.HasPresenter;
import org.vaadin.platform.ui.view.IsEnterAware;
import org.vaadin.platform.ui.view.ViewComposition;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

/**
 * IsNavigable is a role interface that adds navigation support for views built
 * with @ViewComposition. In other terms it enables using component compositions
 * as root points of view structure. In order to be able to 'navigate' into the
 * view the @ViewComposition needs to implement this interface and in addition
 * use @ViewComposition annotation where the name of the view is defined as the
 * navigation URI fragment.
 * 
 * @author Peter / Vaadin
 */
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
        if (this instanceof IsEnterAware) {
            IsEnterAware thisObject = (IsEnterAware) this;
            thisObject.onEnter(event.getParameters());
        }
    }

    /**
     * @return name of this view as defined in @ViewComposition annotation.
     */
    default String getViewName() {
        if (getClass().isAnnotationPresent(ViewComposition.class)) {
            return getClass().getAnnotation(ViewComposition.class).name();
        }

        return null;
    }
}
