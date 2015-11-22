package org.vaadin.platform.ui.navigation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.platform.ui.viewdisplay.ViewDisplayProvider;

import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;

@NormalUIScoped
public class PlatformNavigationManagerBean implements PNavigationManager {

    private Navigator navigator;

    @Inject
    private CDIViewProvider viewProvider;

    @Inject
    private ViewDisplayProvider viewDisplayProvider;

    @PostConstruct
    protected void initialize() {
        navigator = new Navigator(UI.getCurrent(), viewDisplayProvider.provideViewDisplay());
        navigator.addProvider(viewProvider);
    }

}
