package org.vaadin.platform.ui;

import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.navigation.NavigationManager;
import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

public abstract class PlatformUI extends UI {
    private static final long serialVersionUID = -4457107924174041117L;
    private int uiId = -1;
    private String embedId;

    @Inject
    private BeanProvider provider;

    private PlatformViewDisplay viewDisplay;

    @Inject
    private NavigationManager navigationManager;

    @Override
    protected void init(VaadinRequest request) {
        viewDisplay = provider.getReference(PlatformViewDisplay.class);
        setContent(viewDisplay.asComponent());
        navigationManager.initialize();
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
