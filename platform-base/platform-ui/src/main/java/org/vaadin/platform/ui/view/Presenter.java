package org.vaadin.platform.ui.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.inject.Qualifier;

import com.vaadin.cdi.ViewScoped;

/**
 * @Presenter is combination scope definition and qualifier. It associates
 *            annotated presenter class with its view's scope.
 * 
 * @author Peter / Vaadin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Stereotype
@ViewScoped
@Qualifier
public @interface Presenter {

}
