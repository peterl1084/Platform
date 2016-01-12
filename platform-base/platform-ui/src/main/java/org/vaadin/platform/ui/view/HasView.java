package org.vaadin.platform.ui.view;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.vaadin.platform.ui.BeanManagerAccessor;

public interface HasView<V extends HasPresenter> {

    default V getView() {
        Class<V> viewType = getViewType();
        if (viewType == null) {
            throw new RuntimeException("View type is not defined in " + getClass().getCanonicalName());
        }

        BeanManager beanManager = BeanManagerAccessor.getBeanManager();
        Set<Bean<?>> viewCandidates = beanManager.getBeans(viewType);
        if (viewCandidates.isEmpty()) {
            throw new RuntimeException("No candidate views available for type " + viewType.getCanonicalName()
                    + " check that the view type is annotated with @ViewComposition");
        }
        if (viewCandidates.size() > 1) {
            throw new RuntimeException(
                    "More than one view candidate available for type " + viewType.getCanonicalName());
        }

        Bean<?> viewBean = viewCandidates.iterator().next();
        CreationalContext<?> creationalContext = beanManager.createCreationalContext(viewBean);
        V view = (V) beanManager.getReference(viewBean, viewBean.getBeanClass(), creationalContext);

        return view;
    }

    Class<V> getViewType();
}
