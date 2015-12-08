package org.vaadin.platform.ui.navigation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.platform.configuration.factory.BeanProvider;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;

import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

@NormalUIScoped
public class NavigationManagerBean implements NavigationManager {

    private Navigator navigator;

    @Inject
    private CDIViewProvider viewProvider;

    @Inject
    private BeanProvider beanProvider;

    @PostConstruct
    protected void initialize() {
        PlatformViewDisplay viewDisplay = beanProvider.getReference(PlatformViewDisplay.class);
        navigator = new Navigator(UI.getCurrent(), viewDisplay);
        navigator.addProvider(viewProvider);
    }
}
