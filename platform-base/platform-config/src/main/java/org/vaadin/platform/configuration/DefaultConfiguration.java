package org.vaadin.platform.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * DefaultConfiguration is qualifier annotation that can be used to mark
 * framework level beans that provide default values for various type
 * configurations.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface DefaultConfiguration {

}
