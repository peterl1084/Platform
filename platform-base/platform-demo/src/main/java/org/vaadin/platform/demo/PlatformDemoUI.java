package org.vaadin.platform.demo;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import org.vaadin.platform.ui.PlatformUI;
import org.vaadin.platform.ui.navigation.NavigationEvent;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;

@CDIUI("")
@Theme("platform")
public class PlatformDemoUI extends PlatformUI {
    private static final long serialVersionUID = -4248136473744994248L;

    protected void onNavigationEvent(@Observes(notifyObserver = Reception.IF_EXISTS) NavigationEvent e) {
        System.out.println(e);
    }
}
