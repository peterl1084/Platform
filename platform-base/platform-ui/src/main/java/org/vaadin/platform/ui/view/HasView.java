package org.vaadin.platform.ui.view;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.apache.deltaspike.core.api.literal.AnyLiteral;
import org.vaadin.platform.ui.BeanManagerAccessor;

/**
 * HasView is a role interface that presenters implement for associating view
 * object with presenter. Implementing this interface in Presenter
 * implementation is completely optional as not all presenters need to talk back
 * to the view in case they only work as forward data delegates. Implementing
 * this interface in presenter allows the presenter to talk back to its own view
 * instance that it governs.
 * 
 * @author Peter / Vaadin
 *
 * @param <V>
 *            type of the view
 */
public interface HasView<V extends HasPresenter> {

    /**
     * @return reference to view V that this presenter governs
     */
    default V getView() {
        Class<V> viewType = getViewType();
        if (viewType == null) {
            throw new RuntimeException("View type is not defined in " + getClass().getCanonicalName());
        }

        BeanManager beanManager = BeanManagerAccessor.getBeanManager();
        Set<Bean<?>> viewCandidates = beanManager.getBeans(viewType, new AnyLiteral());
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

    /**
     * @return type of the view
     */
    Class<V> getViewType();
}
