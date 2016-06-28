package org.vaadin.platform.ui.viewdisplay;

import java.util.Optional;

import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.sidemenu.MainMenu;

import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

@UIScoped
class DefaultViewDisplayBean extends CustomComponent implements PlatformViewDisplay {
    private static final long serialVersionUID = 6108890114881391778L;

    private ViewArea viewArea;

    private HorizontalLayout mainLayout;

    @Inject
    public DefaultViewDisplayBean(BeanProvider beanProvider) {
        setSizeFull();

        mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        viewArea = beanProvider.getReference(ViewArea.class);
        Optional<MainMenu> menuOptional = beanProvider.getOptionalReference(MainMenu.class);

        if (menuOptional.isPresent()) {
            mainLayout.addComponent(menuOptional.get().asCasted());
        }

        mainLayout.addComponent(viewArea.asCasted());
        mainLayout.setExpandRatio(viewArea.asCasted(), 1);

        setCompositionRoot(mainLayout);
    }

    @Override
    public void showView(View view) {
        viewArea.showView(view);
    }
}
