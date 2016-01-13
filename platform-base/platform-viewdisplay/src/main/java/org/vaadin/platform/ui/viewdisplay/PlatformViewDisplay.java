package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.IsCastable;

import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;

/**
 * PlatformViewDisplay is the general top level interface for all view displays
 * that make up the base layouting of the Platform UI.
 */
public interface PlatformViewDisplay extends ViewDisplay, IsCastable<Component> {

}
