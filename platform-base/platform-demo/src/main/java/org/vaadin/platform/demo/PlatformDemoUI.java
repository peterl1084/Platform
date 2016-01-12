package org.vaadin.platform.demo;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import org.vaadin.platform.ui.PlatformUI;
import org.vaadin.platform.ui.navigation.NavigationEvent;
import org.vaadin.platform.ui.navigation.NavigationManager;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;

@CDIUI("")
@Theme("platform")
public class PlatformDemoUI extends PlatformUI {
    private static final long serialVersionUID = -4248136473744994248L;

    @Inject
    private NavigationManager navigationManager;

    protected void onNavigationEvent(@Observes(notifyObserver = Reception.IF_EXISTS) NavigationEvent e) {
        System.out.println(e);
    }

    @Override
    protected void init(VaadinRequest request) {
        super.init(request);

        navigationManager.navigateTo(Views.CUSTOMER_VIEW);
    }
}
