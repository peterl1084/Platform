package org.vaadin.platform.ui.viewdisplay;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;

/**
 * PViewDisplay is the general top level interface for all view displays that
 * make up the base layouting of the Platform UI.
 */
public interface PlatformViewDisplay extends ViewDisplay, Component {

    /**
     * @return ViewArea of ViewDisplay that hosts the actual changing view area.
     */
    ViewAreaComponent getViewArea();

    public interface ViewAreaComponent extends ViewPart {
        void setView(View view);
    }

    public interface ViewPart extends Component {

    }
}
