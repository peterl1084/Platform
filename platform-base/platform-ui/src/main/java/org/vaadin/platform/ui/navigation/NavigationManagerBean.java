package org.vaadin.platform.ui.navigation;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.navigation.NavigationEvent.EventType;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;

import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.ui.UI;

@NormalUIScoped
class NavigationManagerBean extends Navigator implements NavigationManager {
    private static final long serialVersionUID = 1546108773276819826L;

    @Inject
    private Event<NavigationEvent> navigationEventSource;

    @Inject
    private BeanProvider beanProvider;

    private UriFragmentResolver fragmentResolver;

    private ViewProvider viewProvider;

    private String navigationState;

    @PostConstruct
    protected void initialize() {
        PlatformViewDisplay viewDisplay = beanProvider.getReference(PlatformViewDisplay.class);
        fragmentResolver = beanProvider.getReference(UriFragmentResolver.class);
        init(UI.getCurrent(), new UriFragmentHandler(Page.getCurrent()), viewDisplay);
        viewProvider = beanProvider.getReference(ViewProvider.class);
    }

    @Override
    public String getState() {
        return navigationState;
    }

    @Override
    public void navigateTo(String state) {
        String viewName = viewProvider.getViewName(state);
        String parameters = fragmentResolver.resolveParameters(state);
        if (viewName == null) {
            revertNavigation();
            return;
        }

        View targetView = viewProvider.getView(viewName);

        ViewChangeEvent event = new ViewChangeEvent(this, getCurrentView(), targetView, viewName, parameters);
        boolean navigationAllowed = beforeViewChange(event);

        if (!navigationAllowed) {
            revertNavigation();
            return;
        }

        updateNavigationState(event);

        getDisplay().showView(targetView);

        switchView(event);

        targetView.enter(event);
        navigationEventSource.fire(new NavigationEvent(viewName, parameters, EventType.PROGRAMMATIC));

        fireAfterViewChange(event);
    }

    private class UriFragmentHandler extends UriFragmentManager {
        private static final long serialVersionUID = -2241509222046244059L;

        public UriFragmentHandler(Page page) {
            super(page);
        }

        @Override
        public void uriFragmentChanged(UriFragmentChangedEvent event) {
            navigateTo(event.getUriFragment());
        }
    }
}
