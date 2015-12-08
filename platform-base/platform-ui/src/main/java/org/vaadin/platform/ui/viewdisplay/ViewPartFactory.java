package org.vaadin.platform.ui.viewdisplay;

import java.lang.annotation.Annotation;

import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewPart;

/**
 * ViewPartFactory is used to produce ViewParts of the UI
 */
public interface ViewPartFactory {

    /**
     * Produces a view part of given type. Instance selection can be controlled
     * with given qualifier annotations.
     * 
     * @param partType
     * @param qualifier
     * @return ViewPart of T selected from given possible selection of
     *         qualifiers
     */
    <T extends ViewPart> T produceViewPart(Class<T> partType, Annotation... qualifier);

}
