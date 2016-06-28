package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.IsCastable;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

/**
 * ViewArea defines the component container that holds the changing view
 * content.
 */
public interface ViewArea extends IsCastable<Component> {

    /**
     * Sets the given Vaadin view as content of this ViewArea
     * 
     * @param view
     */
    void showView(View view);
}
