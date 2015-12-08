package org.vaadin.platform.ui.viewdisplay;

import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.SingleComponentContainer;

/**
 * PViewDisplay is the general top level interface for all view displays that
 * make up the base layouting of the Platform UI.
 */
public interface PlatformViewDisplay extends ViewDisplay {

    /**
     * @return ViewRoot of ViewDisplay that is the actual root component of
     *         ViewDisplay structure.
     */
    ViewRootComponent getRoot();

    /**
     * @return ViewArea of ViewDisplay that hosts the actual changing view area.
     */
    ViewAreaComponent getViewArea();

    public interface ViewRootComponent extends ViewPart, HasComponents {

    }

    public interface ViewAreaComponent extends ViewPart, SingleComponentContainer {

    }

    public interface ViewPart extends Component {

    }
}
