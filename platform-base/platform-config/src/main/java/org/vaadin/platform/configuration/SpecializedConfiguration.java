package org.vaadin.platform.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * SpecializedConfiguration is a qualifier annotation that can be applied on
 * beans providing specializing (and overwriting) type configuration.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface SpecializedConfiguration {

}
