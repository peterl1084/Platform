package org.vaadin.platform.ui.viewdisplay;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;

/**
 * PViewDisplay is the general top level interface for all view displays that
 * make up the base layouting of the Platform UI.
 */
public interface PlatformViewDisplay extends ViewDisplay {

    /**
     * @return ViewArea of ViewDisplay that hosts the actual changing view area.
     */
    ViewAreaComponent getViewArea();

    public interface ViewAreaComponent {
        void setView(View view);

        default Component asComponent() {
            return Component.class.cast(this);
        }
    }

    default Component asComponent() {
        return Component.class.cast(this);
    }
}
