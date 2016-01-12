package org.vaadin.platform.ui.navigation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.literal.AnyLiteral;
import org.vaadin.platform.ui.view.ViewComposition;

import com.vaadin.cdi.access.AccessControl;
import com.vaadin.cdi.internal.CDIUtil;
import com.vaadin.cdi.internal.VaadinViewChangeCleanupEvent;
import com.vaadin.cdi.internal.VaadinViewChangeEvent;
import com.vaadin.cdi.internal.VaadinViewCreationEvent;
import com.vaadin.cdi.internal.ViewBean;
import com.vaadin.navigator.Navigator;
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

    private static ThreadLocal<VaadinViewChangeCleanupEvent> cleanupEvent = new ThreadLocal<VaadinViewChangeCleanupEvent>();

    @Inject
    private AccessControl accessControl;
    private transient CreationalContext<?> currentViewCreationalContext;

    public final static class ViewChangeListenerImpl implements ViewChangeListener {

        private BeanManager beanManager;

        public ViewChangeListenerImpl(BeanManager beanManager) {
            this.beanManager = beanManager;
        }

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            getLogger().fine("Changing view from " + event.getOldView() + " to " + event.getNewView());
            // current session id
            long sessionId = CDIUtil.getSessionId();
            int uiId = event.getNavigator().getUI().getUIId();
            String viewName = event.getViewName();
            beanManager.fireEvent(new VaadinViewChangeEvent(sessionId, uiId, viewName));
        }
    }

    private ViewChangeListener viewChangeListener;

    @PostConstruct
    private void postConstruct() {
        viewChangeListener = new ViewChangeListenerImpl(beanManager);
    }

    @Override
    public String getViewName(String viewAndParameters) {
        getLogger().log(Level.FINE, "Attempting to retrieve view name from string \"{0}\"", viewAndParameters);

        String name = parseViewName(viewAndParameters);
        ViewBean viewBean = getViewBean(name);

        if (viewBean == null) {
            return null;
        }

        if (isUserHavingAccessToView(viewBean)) {
            if (viewBean.getBeanClass().isAnnotationPresent(ViewComposition.class)) {
                String specifiedViewName = deriveMappingForView(viewBean.getBeanClass());
                if (!specifiedViewName.isEmpty()) {
                    return specifiedViewName;
                }
            }
            return name;
        } else {
            getLogger().log(Level.INFO, "User {0} did not have access to view \"{1}\"",
                    new Object[] { accessControl.getPrincipalName(), viewBean });
        }

        return null;
    }

    protected boolean isUserHavingAccessToView(Bean<?> viewBean) {
        return true;
    }

    private ViewBean getViewBean(String viewName) {
        getLogger().log(Level.FINE, "Looking for view with name \"{0}\"", viewName);

        if (viewName == null) {
            return null;
        }

        Set<Bean<?>> matching = new HashSet<Bean<?>>();
        Set<Bean<?>> all = beanManager.getBeans(IsNavigable.class, new AnyLiteral());
        if (all.isEmpty()) {
            getLogger().severe(
                    "No navigable views found! Please add at least one class implementing the IsNavigable interface.");
            return null;
        }
        for (Bean<?> bean : all) {
            Class<?> beanClass = bean.getBeanClass();
            ViewComposition viewAnnotation = beanClass.getAnnotation(ViewComposition.class);
            if (viewAnnotation == null) {
                continue;
            }

            String mapping = deriveMappingForView(beanClass);
            getLogger().log(Level.FINER, "{0} is annotated, the viewName is \"{1}\"",
                    new Object[] { beanClass.getName(), mapping });

            // In the case of an empty fragment, use the root view.

            if (viewName.equals(mapping)) {
                matching.add(bean);
                getLogger().log(Level.FINER, "Bean {0} with viewName \"{1}\" is one alternative",
                        new Object[] { bean, mapping });
            }
        }

        Set<Bean<?>> viewBeansForThisProvider = getViewBeansForCurrentUI(matching);
        if (viewBeansForThisProvider.isEmpty()) {
            getLogger().log(Level.WARNING, "No view beans found for current UI");
            return null;
        }

        if (viewBeansForThisProvider.size() > 1) {
            throw new RuntimeException("Multiple views mapped with same name for same UI");
        }

        return new ViewBean(viewBeansForThisProvider.iterator().next(), viewName);
    }

    private Set<Bean<?>> getViewBeansForCurrentUI(Set<Bean<?>> beans) {
        Set<Bean<?>> viewBeans = new HashSet<Bean<?>>();

        for (Bean<?> bean : beans) {
            ViewComposition viewAnnotation = bean.getBeanClass().getAnnotation(ViewComposition.class);

            if (viewAnnotation == null) {
                continue;
            }

            List<Class<? extends UI>> uiClasses = Arrays.asList(viewAnnotation.uis());

            if (uiClasses.contains(UI.class)) {
                viewBeans.add(bean);
            } else {
                Class<? extends UI> currentUI = UI.getCurrent().getClass();
                for (Class<? extends UI> uiClass : uiClasses) {
                    if (uiClass.isAssignableFrom(currentUI)) {
                        viewBeans.add(bean);
                        break;
                    }
                }
            }
        }

        return viewBeans;
    }

    @Override
    public IsNavigable getView(String viewName) {
        getLogger().log(Level.FINE, "Attempting to retrieve view with name \"{0}\"", viewName);

        // current session and UI
        long sessionId = CDIUtil.getSessionId();
        UI currentUI = UI.getCurrent();

        if (currentUI == null) {
            getLogger().log(Level.WARNING, "No current UI - cannot create view {0}", viewName);
            throw new IllegalStateException("Cannot create View " + viewName + " - current UI is not set");
        }

        ViewBean viewBean = getViewBean(viewName);
        if (viewBean != null) {
            if (!isUserHavingAccessToView(viewBean)) {
                getLogger().log(Level.INFO, "User {0} did not have access to view {1}",
                        new Object[] { accessControl.getPrincipalName(), viewBean });
                return null;
            }

            if (currentViewCreationalContext != null) {
                getLogger().log(Level.FINER, "Releasing creational context for current view {0}",
                        currentViewCreationalContext);
                currentViewCreationalContext.release();
            }

            currentViewCreationalContext = beanManager.createCreationalContext(viewBean);
            getLogger().log(Level.FINER, "Created new creational context for current view {0}",
                    currentViewCreationalContext);

            beanManager.fireEvent(new VaadinViewCreationEvent(sessionId, currentUI.getUIId(), viewName));
            cleanupEvent.set(new VaadinViewChangeCleanupEvent(sessionId, currentUI.getUIId()));

            IsNavigable view = (IsNavigable) beanManager.getReference(viewBean, viewBean.getBeanClass(),
                    currentViewCreationalContext);
            getLogger().log(Level.FINE, "Returning view instance {0}", view.toString());

            Navigator navigator = currentUI.getNavigator();
            if (navigator != null) {
                // This is a fairly dumb way of making sure that there is
                // one and only one CDI viewChangeListener for this
                // Navigator.
                navigator.removeViewChangeListener(viewChangeListener);
                navigator.addViewChangeListener(viewChangeListener);
            }
            return view;
        }

        throw new RuntimeException("Unable to instantiate view");
    }

    @PreDestroy
    protected void destroy() {
        if (currentViewCreationalContext != null) {
            getLogger().log(Level.FINE,
                    "CDIViewProvider is being destroyed, releasing creational context for current view");
            currentViewCreationalContext.release();
        }
    }

    private String parseViewName(String viewAndParameters) {

        String viewName = viewAndParameters;
        if (viewName.startsWith("!")) {
            viewName = viewName.substring(1);
        }

        for (String name : getCDIViewMappings(beanManager)) {
            if (viewName.equals(name) || (viewName.startsWith(name + "/"))) {
                return name;
            }
        }

        return null;
    }

    public static List<String> getCDIViewMappings(BeanManager beanManager) {
        Set<Bean<?>> viewBeans = beanManager.getBeans(IsNavigable.class, new AnyLiteral());

        List<String> mappingList = new LinkedList<String>();
        for (Bean<?> viewBean : viewBeans) {
            Class<?> beanClass = viewBean.getBeanClass();
            if (beanClass.getAnnotation(ViewComposition.class) == null) {
                continue;
            }
            String mapping = deriveMappingForView(viewBean.getBeanClass());
            if (mapping != null) {
                mappingList.add(mapping);
            }
        }
        Collections.sort(mappingList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // descending string length order to get the longest view
                // mappings first
                return o2.length() - o1.length();
            }
        });

        return mappingList;
    }

    public static String deriveMappingForView(Class<?> beanClass) {
        if (beanClass.isAnnotationPresent(ViewComposition.class)) {
            return beanClass.getAnnotation(ViewComposition.class).name();
        }

        return null;
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
