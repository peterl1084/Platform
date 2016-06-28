package org.vaadin.platform.ui;

import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 * PlatformUI is base class for all UI's that wish to utilize Platform for
 * Vaadin functionality.
 */
@CDIUI("")
@Theme("platform")
public class PlatformUI extends UI {
    private static final long serialVersionUID = -4457107924174041117L;
    private int uiId = -1;
    private String embedId;

    @Inject
    private javax.enterprise.event.Event<UIInitializedEvent> uiInitEventSource;

    @Inject
    private BeanProvider beanProvider;

    @Override
    protected void init(VaadinRequest request) {
        ViewDisplay viewDisplay = beanProvider.getReference(ViewDisplay.class);
        setContent((Component) viewDisplay);

        uiInitEventSource.fire(new UIInitializedEvent());
    }

    @Override
    public void doInit(VaadinRequest request, int uiId, String embedId) {
        if (this.getUIId() != -1) {
            String message = "This UI instance is already initialized (as UI id " + this.getUIId()
                    + ") and can therefore not be initialized again (as UI id " + uiId + "). ";

            if (getSession() != null && !getSession().equals(VaadinSession.getCurrent())) {
                message += "Furthermore, it is already attached to another VaadinSession. ";
            }
            message += "Please make sure you are not accidentally reusing an old UI instance.";

            throw new IllegalStateException(message);
        }
        this.uiId = uiId;
        this.embedId = embedId;

        // Actual theme - used for finding CustomLayout templates
        setTheme(request.getParameter("theme"));

        getPage().init(request);

        // Call the init overridden by the application developer
        init(request);
    }

    @Override
    public int getUIId() {
        return uiId;
    }

    @Override
    public String getEmbedId() {
        return embedId;
    }
}
