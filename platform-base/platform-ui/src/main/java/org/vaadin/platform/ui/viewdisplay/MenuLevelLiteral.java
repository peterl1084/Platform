package org.vaadin.platform.ui.viewdisplay;

import javax.enterprise.util.AnnotationLiteral;

public class MenuLevelLiteral extends AnnotationLiteral<MenuLevel> implements MenuLevel {
    private static final long serialVersionUID = 6669526087276034533L;

    private int level;

    public MenuLevelLiteral(int level) {
        this.level = level;
    }

    @Override
    public int level() {
        return level;
    }
}
