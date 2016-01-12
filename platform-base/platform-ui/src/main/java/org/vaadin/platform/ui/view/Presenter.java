package org.vaadin.platform.ui.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.inject.Qualifier;

import com.vaadin.cdi.ViewScoped;

@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@ViewScoped
@Qualifier
public @interface Presenter {

}
