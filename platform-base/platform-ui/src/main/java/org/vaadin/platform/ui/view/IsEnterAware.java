package org.vaadin.platform.ui.view;

/**
 * IsEnterAware is a role interface that @Presenter classes can implement in
 * order of their views notifying them once view is entered.
 * 
 * @author Peter / Vaadin
 */
public interface IsEnterAware {

    /**
     * Invoked by the view upon enter if view's @Presenter is of type
     * IsEnterAware.
     * 
     * @param parameters
     *            passed from URI fragment
     */
    void onEnter(String parameters);
}
