package org.vaadin.platform.ui.view;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;

import org.vaadin.platform.ui.BeanManagerAccessor;

/**
 * HasPresenter is a role interface that associates presenter object with its
 * implementor. This interface provides access to presenter instance that can be
 * owner specific according to its scoping rules.
 * 
 * @author Peter / Vaadin
 *
 * @param
 *            <P>
 *            type of the presenter
 */
public interface HasPresenter<P> {

    /**
     * @return reference to or new instance of presenter P according to
     *         presenter's scoping rules.
     */
    default P getPresenter() {
        Class<P> presenterType = getPresenterType();
        if (presenterType == null) {
            throw new RuntimeException("Presenter type is not defined in " + getClass().getCanonicalName());
        }

        BeanManager beanManager = BeanManagerAccessor.getBeanManager();
        Set<Bean<?>> presenterCandidates = beanManager.getBeans(presenterType, new PresenterLiteral());
        if (presenterCandidates.isEmpty()) {
            throw new RuntimeException("No candidate presenters available for type " + presenterType.getCanonicalName()
                    + " check that the presenter type is annotated with @Presenter");
        }
        if (presenterCandidates.size() > 1) {
            throw new RuntimeException(
                    "More than one presenter candidate available for type " + presenterType.getCanonicalName());
        }

        Bean<?> presenterBean = presenterCandidates.iterator().next();
        CreationalContext<?> creationalContext = beanManager.createCreationalContext(presenterBean);
        P presenter = (P) beanManager.getReference(presenterBean, presenterBean.getBeanClass(), creationalContext);

        return presenter;
    }

    Class<P> getPresenterType();
}
