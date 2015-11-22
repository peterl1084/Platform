package org.vaadin.platform.ui.viewdisplay;

import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.SingleComponentContainer;

/**
 * PViewDisplay is the general top level interface for all view displays that
 * make up the base layouting of the Platform UI.
 */
public interface PViewDisplay extends ViewDisplay {

    /**
     * @return ComponentContainer that is the root component of this view
     *         display.
     */
    RootComponent getRootComponent();

    /**
     * @return Component containing the actual view that is currently visible.
     */
    ViewArea getViewArea();

    public interface RootComponent extends ComponentContainer {

    }

    public interface ViewArea extends SingleComponentContainer {

    }
}
