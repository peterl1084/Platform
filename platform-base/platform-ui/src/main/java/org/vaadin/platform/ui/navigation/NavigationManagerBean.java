package org.vaadin.platform.ui.navigation;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.navigation.NavigationEvent.EventType;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;

import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.ui.UI;

@NormalUIScoped
class NavigationManagerBean extends Navigator implements NavigationManager {
    private static final long serialVersionUID = 1546108773276819826L;

    private ViewProvider viewProvider;

    @Inject
    private Event<NavigationEvent> navigationEventSource;

    @Inject
    private BeanProvider beanProvider;

    @PostConstruct
    protected void initialize() {
        PlatformViewDisplay viewDisplay = beanProvider.getReference(PlatformViewDisplay.class);
        UriFragmentResolver fragmentResolver = beanProvider.getReference(UriFragmentResolver.class);

        init(UI.getCurrent(), new UriFragmentHandler(Page.getCurrent(), fragmentResolver), viewDisplay);

        viewProvider = beanProvider.getReference(ViewProvider.class);
        addProvider(viewProvider);
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
