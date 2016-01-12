package org.vaadin.platform.ui.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.enterprise.util.Nonbinding;

import com.vaadin.cdi.ViewScoped;
import com.vaadin.ui.UI;

/**
 * @ViewComposition is the annotation that each view composition needs to
 *                  define. @ViewComposition can consist of various UI elements
 *                  that together work as higher abstraction level composition,
 *                  such as 'editor' or 'form'. If @ViewComposition is used with
 *                  view that is also implementing IsNavigable interface then
 *                  the view is considered as navigable application view
 *                  composition root that can be activated through
 *                  NavigationManager by navigateTo(name)
 * @author Peter / Vaadin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Stereotype
@ViewScoped
public @interface ViewComposition {

    /**
     * @return name of the view, in navigable views this is the URI fragment in
     *         the browser
     */
    @Nonbinding
    String name();

    /**
     * @return UIs in which this view is allowed to appear.
     */
    @Nonbinding
    Class<? extends UI>[] uis() default { UI.class };
}
