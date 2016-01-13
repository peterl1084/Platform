package org.vaadin.platform.demo;

import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.PlatformUI;
import org.vaadin.platform.ui.navigation.NavigationManager;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;

@CDIUI("")
@Theme("platform")
public class PlatformDemoUI extends PlatformUI {
    private static final long serialVersionUID = -4248136473744994248L;

    @Inject
    private NavigationManager navigationManager;

    @Inject
    private BeanProvider beanProvider;

    @Override
    protected void init(VaadinRequest request) {
        PlatformViewDisplay viewDisplay = beanProvider.getReference(PlatformViewDisplay.class);
        setContent(viewDisplay.asCasted());

        navigationManager.navigateTo(Views.CUSTOMER_VIEW);
    }
}
