package org.vaadin.platform.ui.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.enterprise.util.Nonbinding;

import com.vaadin.cdi.ViewScoped;
import com.vaadin.ui.UI;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Stereotype
@ViewScoped
public @interface ViewComposition {

    @Nonbinding
    String name();

    @Nonbinding
    Class<? extends UI>[] uis() default { UI.class };
}
