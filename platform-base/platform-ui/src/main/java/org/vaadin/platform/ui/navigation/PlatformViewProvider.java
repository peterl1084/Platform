package org.vaadin.platform.ui.navigation;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.literal.AnyLiteral;
import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.view.ViewComposition;
import org.vaadin.platform.ui.view.ViewCompositionLiteral;

import com.vaadin.cdi.internal.CDIUtil;
import com.vaadin.cdi.internal.VaadinViewChangeCleanupEvent;
import com.vaadin.cdi.internal.VaadinViewChangeEvent;
import com.vaadin.cdi.internal.VaadinViewCreationEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.UI;

/*
 * Copyright 2000-2013 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

public class PlatformViewProvider implements ViewProvider {
    private static final long serialVersionUID = -812939554352575060L;

    @Inject
    private BeanManager beanManager;

    private UriFragmentResolver fragmentResolver;

    @Inject
    private BeanProvider beanProvider;

    private static ThreadLocal<VaadinViewChangeCleanupEvent> cleanupEvent = new ThreadLocal<VaadinViewChangeCleanupEvent>();

    private transient CreationalContext<?> currentViewCreationalContext;

    private ViewChangeListener viewChangeListener;

    @Any
    @Inject
    private Instance<IsNavigable> viewInstantiator;

    private final class ViewChangeListenerBean implements ViewChangeListener {
        private static final long serialVersionUID = 7489568851685540893L;

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            // current session id
            long sessionId = CDIUtil.getSessionId();
            int uiId = event.getNavigator().getUI().getUIId();
            String viewName = event.getViewName();
            beanManager.fireEvent(new VaadinViewChangeEvent(sessionId, uiId, viewName));
        }
    }

    @PostConstruct
    private void postConstruct() {
        viewChangeListener = new ViewChangeListenerBean();
        fragmentResolver = beanProvider.getReference(UriFragmentResolver.class);
    }

    @Override
    public String getViewName(String viewAndParameters) {
        String viewName = fragmentResolver.resolveViewName(viewAndParameters);

        for (String name : getNavigableViews(beanManager)) {
            if (viewName.equals(name) || (viewName.startsWith(name + "/"))) {
                return name;
            }
        }

        return null;
    }

    @Override
    public IsNavigable getView(String viewName) {
        long sessionId = CDIUtil.getSessionId();
        UI currentUI = UI.getCurrent();

        Instance<IsNavigable> selectedViewInstantiator = viewInstantiator.select(new ViewCompositionLiteral(viewName));

        if (selectedViewInstantiator.isUnsatisfied()) {
            throw new RuntimeException("Could not find view with name " + viewName);
        } else if (selectedViewInstantiator.isAmbiguous()) {
            throw new RuntimeException(
                    "More than one view found with name " + viewName + " check your view definitions");
        }

        beanManager.fireEvent(new VaadinViewCreationEvent(sessionId, currentUI.getUIId(), viewName));
        cleanupEvent.set(new VaadinViewChangeCleanupEvent(sessionId, currentUI.getUIId()));

        return selectedViewInstantiator.get();
    }

    @PreDestroy
    protected void destroy() {
        if (currentViewCreationalContext != null) {
            getLogger().log(Level.FINE,
                    "CDIViewProvider is being destroyed, releasing creational context for current view");
            currentViewCreationalContext.release();
        }
    }

    private Set<String> getNavigableViews(BeanManager beanManager) {
        Set<Bean<?>> viewBeans = beanManager.getBeans(IsNavigable.class, new AnyLiteral());

        // Take only such beans which have ViewComposition annotation.
        Set<ViewComposition> viewDefinitions = viewBeans.stream()
                .filter(bean -> bean.getBeanClass().isAnnotationPresent(ViewComposition.class))
                .map(bean -> bean.getBeanClass().getAnnotation(ViewComposition.class)).collect(Collectors.toSet());

        // Remove all such which are not for this UI.

        // viewDefinitions.removeIf(definition ->
        // !Arrays.asList(definition.uis()).contains(UI.getCurrent().getClass()));

        // Return name set
        return viewDefinitions.stream().map(definition -> definition.name()).collect(Collectors.toSet());
    }

    public static VaadinViewChangeCleanupEvent getCleanupEvent() {
        return cleanupEvent.get();
    }

    public static void removeCleanupEvent() {
        cleanupEvent.remove();
    }

    private static Logger getLogger() {
        return Logger.getLogger(PlatformViewProvider.class.getCanonicalName());
    }
}
