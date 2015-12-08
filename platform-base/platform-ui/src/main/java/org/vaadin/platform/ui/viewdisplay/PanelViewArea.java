package org.vaadin.platform.ui.viewdisplay;

import org.vaadin.platform.ui.viewdisplay.PlatformViewDisplay.ViewAreaComponent;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class PanelViewArea extends CustomComponent implements ViewAreaComponent {
    private static final long serialVersionUID = 8793364361651660486L;
    private Panel panel;

    public PanelViewArea() {
        setSizeFull();

        panel = new Panel();
        panel.setSizeFull();

        setCompositionRoot(panel);

        panel.setContent(new Label("Hello from panel"));
    }

    @Override
    public void setView(View view) {
        panel.setContent((Component) view);
    }
}
