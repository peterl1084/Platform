package org.vaadin.platform.sidemenu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.vaadin.platform.IsCastable;

import com.vaadin.ui.Component;

/**
 * Menu is base interface for menu implementations.
 */
public interface Menu extends IsCastable<Component>, Serializable {
    List<MenuItem> getMenuItems();

    Iterator<MenuItem> menuItemIterator();
}
