package org.vaadin.platform.sidemenu;

import java.io.Serializable;

import com.vaadin.server.Resource;

/**
 * MenuItems are used in Menu
 * 
 * @author Peter / Vaadin
 */
public interface MenuItem extends Iterable<MenuItem>, Serializable {

    String getCaption();

    Resource getIcon();
}
