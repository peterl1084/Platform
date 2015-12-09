package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.ui.IsCastableComponent;

import com.vaadin.navigator.View;

/**
 * ViewArea defines the component container that holds the changing view
 * content.
 */
public interface ViewArea extends IsCastableComponent {

    /**
     * Sets the given Vaadin view as content of this ViewArea
     * 
     * @param view
     */
    void showView(View view);
}
