package org.vaadin.platform.sidemenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.platform.configuration.bean.BeanProvider;

import com.vaadin.ui.CssLayout;

public abstract class MenuBase extends CssLayout implements Menu, HasMenuItemProvider {
    private static final long serialVersionUID = 6794588988549452922L;

    private List<MenuItem> menuItems;

    private MenuItemProvider menuItemProvider;

    @Inject
    private BeanProvider beanProvider;

    public MenuBase() {
        menuItems = new ArrayList<>();
    }

    @PostConstruct
    protected void initialize() {
        Optional<MenuItemProvider> itemProviderOptional = beanProvider.getOptionalReference(MenuItemProvider.class);
        if (itemProviderOptional.isPresent()) {
            menuItemProvider = itemProviderOptional.get();
        }
    }

    @Override
    public MenuItemProvider getMenuItemProvider() {
        return menuItemProvider;
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(new ArrayList<>(menuItems));
    }

    @Override
    public Iterator<MenuItem> menuItemIterator() {
        return getMenuItems().iterator();
    }
}
