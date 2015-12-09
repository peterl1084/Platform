package org.vaadin.platform.ui.navigation;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.navigation.NavigationEvent.EventType;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;

import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.UriFragmentManager;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.ui.UI;

@NormalUIScoped
class NavigationManagerBean implements NavigationManager {
    private Navigator navigator;
    private ViewProvider viewProvider;

    @Inject
    private Event<NavigationEvent> navigationEventSource;

    @Inject
    public NavigationManagerBean(BeanProvider beanProvider) {
        PlatformViewDisplay viewDisplay = beanProvider.getReference(PlatformViewDisplay.class);
        UriFragmentResolver fragmentResolver = beanProvider.getReference(UriFragmentResolver.class);
        navigator = new Navigator(UI.getCurrent(), new UriFragmentHandler(Page.getCurrent(), fragmentResolver),
                viewDisplay);

        viewProvider = beanProvider.getReference(ViewProvider.class);
        navigator.addProvider(viewProvider);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void navigateTo(String view, String parameters) {
        navigationEventSource.fire(new NavigationEvent(view, parameters, EventType.PROGRAMMATIC));
    }

    private class UriFragmentHandler extends UriFragmentManager {
        private UriFragmentResolver fragmentResolver;

        public UriFragmentHandler(Page page, UriFragmentResolver fragmentResolver) {
            super(page);
            this.fragmentResolver = fragmentResolver;
        }

        private static final long serialVersionUID = -2241509222046244059L;

        @Override
        public void uriFragmentChanged(UriFragmentChangedEvent event) {
            if (fragmentResolver.isFragmentValid(event.getUriFragment())) {
                String view = fragmentResolver.resolveView(event.getUriFragment());
                String parameters = fragmentResolver.resolveParameters(event.getUriFragment());

                navigationEventSource.fire(new NavigationEvent(view, parameters, EventType.FRAGMENT));
            }
        }
    }
}
