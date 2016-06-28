package org.vaadin.platform.sidemenu;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.literal.AnyLiteral;
import org.vaadin.platform.ui.UIInitializedEvent;
import org.vaadin.platform.ui.view.ViewComposition;

import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.navigator.View;

@NormalUIScoped
public class MenuItemProviderBean {

    @Inject
    private BeanManager beanManager;

    @Inject
    private Instance<MainMenu> mainMenuLookup;

    protected void doMenuItemLookup(@Observes UIInitializedEvent event) {
        if (mainMenuLookup.isAmbiguous()) {
            return;
        }

        if (mainMenuLookup.isUnsatisfied()) {
            return;
        }

        MainMenu mainMenu = mainMenuLookup.get();
        Set<Bean<?>> beans = beanManager.getBeans(View.class, new AnyLiteral());

        List<Bean<?>> menuItemBeans = beans.stream()
                .filter(bean -> bean.getBeanClass().isAnnotationPresent(ViewComposition.class)
                        && bean.getBeanClass().getAnnotation(ViewComposition.class).showInMenu())
                .sorted(new BeanNameComparer()).collect(Collectors.toList());

        menuItemBeans.forEach(menuItemBean -> {
            ViewComposition viewAnnotation = menuItemBean.getBeanClass().getAnnotation(ViewComposition.class);

            mainMenu.addMenuItem(viewAnnotation.caption(), viewAnnotation.icon(), viewAnnotation.name());
        });
    }

    private static class BeanNameComparer implements Comparator<Bean<?>> {

        @Override
        public int compare(Bean<?> a, Bean<?> b) {
            ViewComposition aAnnotation = a.getBeanClass().getAnnotation(ViewComposition.class);
            ViewComposition bAnnotation = b.getBeanClass().getAnnotation(ViewComposition.class);

            return aAnnotation.order() - bAnnotation.order();
        }
    }
}
