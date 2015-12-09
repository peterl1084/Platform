package org.vaadin.platform.ui.viewdisplay;

import java.util.Optional;

import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;
import org.vaadin.platform.ui.menu.SideMenu;

import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

@UIScoped
public class DefaultViewDisplayBean extends CustomComponent implements PlatformViewDisplay {
    private static final long serialVersionUID = 6108890114881391778L;

    private ViewArea viewArea;

    private HorizontalLayout mainLayout;

    @Inject
    public DefaultViewDisplayBean(BeanProvider beanProvider) {
        setSizeFull();

        mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();

        viewArea = beanProvider.getReference(ViewArea.class);
        Optional<SideMenu> menuOptional = beanProvider.getOptionalReference(SideMenu.class);

        if (menuOptional.isPresent()) {
            mainLayout.addComponent(menuOptional.get().asComponent());
        }

        mainLayout.addComponent(viewArea.asComponent());
        mainLayout.setExpandRatio(viewArea.asComponent(), 1);

        setCompositionRoot(mainLayout);
    }

    @Override
    public void showView(View view) {
        viewArea.showView(view);
    }
}
