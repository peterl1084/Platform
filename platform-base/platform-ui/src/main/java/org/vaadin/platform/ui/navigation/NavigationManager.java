package org.vaadin.platform.ui.navigation;

public interface NavigationManager {

    void initialize();

    void navigateTo(String view, String parameters);

}
