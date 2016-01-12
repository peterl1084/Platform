package org.vaadin.platform.ui.view;

/**
 * IsEnterAware is a role interface that Presenter classes can implement in
 * order of their views notifying them upon view getting activated by entering.
 * 
 * @author Peter / Vaadin
 */
public interface IsEnterAware {

    /**
     * Invoked by the view upon enter if view HasPresenter of type IsEnterAware.
     * 
     * @param parameters
     */
    void onEnter(String parameters);
}
