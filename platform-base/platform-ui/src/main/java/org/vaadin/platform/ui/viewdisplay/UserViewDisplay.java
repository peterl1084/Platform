package org.vaadin.platform.ui.viewdisplay;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.platform.configuration.factory.BeanProvider;

import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

@UIScoped
public class UserViewDisplay extends CustomComponent implements PlatformViewDisplay {
    private static final long serialVersionUID = 6108890114881391778L;

    @Inject
    private BeanProvider beanProvider;

    private ViewAreaComponent viewArea;

    private HorizontalLayout mainLayout;

    public UserViewDisplay() {
        setSizeFull();

        mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        setCompositionRoot(mainLayout);
    }

    @PostConstruct
    protected void initialize() {
        viewArea = beanProvider.getReference(ViewAreaComponent.class);
        Optional<ViewMenuComponent> menuOptional = beanProvider.getOptionalReference(ViewMenuComponent.class,
                new MenuLevelLiteral(1));

        if (menuOptional.isPresent()) {
            mainLayout.addComponent(menuOptional.get());
            mainLayout.setExpandRatio(menuOptional.get(), 20);
        }

        mainLayout.addComponent(viewArea);
        mainLayout.setExpandRatio(viewArea, menuOptional.isPresent() ? 80 : 100);
    }

    @Override
    public void showView(View view) {

    }

    @Override
    public ViewAreaComponent getViewArea() {
        return viewArea;
    }

}
