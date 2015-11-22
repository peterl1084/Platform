package org.vaadin.platform.ui.viewdisplay;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;

public abstract class AbstractViewDisplay implements PViewDisplay {
    private static final long serialVersionUID = 5675620585203542497L;
    private RootComponent root;
    private ViewArea viewArea;

    public AbstractViewDisplay(RootComponent root, ViewArea viewArea) {
        this.root = root;
        this.viewArea = viewArea;
    }

    @Override
    public RootComponent getRootComponent() {
        return root;
    }

    @Override
    public ViewArea getViewArea() {
        return viewArea;
    }

    @Override
    public void showView(View view) {
        if (view instanceof Component) {
            getViewArea().setContent((Component) view);
        } else {
            throw new RuntimeException("Given view " + view.getClass().getSimpleName() + " is not a component");
        }
    }
}
