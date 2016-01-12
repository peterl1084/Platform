package org.vaadin.platform.ui.navigation;

/**
 * NavigationManager can be used for programmatically navigating to specific
 * view with specific parameters.
 */
public interface NavigationManager {

    void navigateTo(String view);

    /**
     * Causes navigation to given view with given parameters.
     * 
     * @param view
     * @param parameters
     */
    void navigateTo(String view, String parameters);

}
