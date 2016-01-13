package org.vaadin.platform.sidemenu;

import java.util.Iterator;
import java.util.List;

public interface MenuItemProvider {
    List<MenuItem> getMenuItems();

    Iterator<MenuItem> menuItemIterator();
}
