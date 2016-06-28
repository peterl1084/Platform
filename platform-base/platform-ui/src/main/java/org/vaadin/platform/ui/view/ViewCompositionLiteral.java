package org.vaadin.platform.ui.view;

import javax.enterprise.util.AnnotationLiteral;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;

public class ViewCompositionLiteral extends AnnotationLiteral<ViewComposition> implements ViewComposition {
    private static final long serialVersionUID = 8062277646467012829L;

    private final String name;

    public ViewCompositionLiteral(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Class<UI>[] uis() {
        return new Class[0];
    }

    @Override
    public boolean showInMenu() {
        return false;
    }

    @Override
    public String caption() {
        return "";
    }

    @Override
    public FontAwesome icon() {
        return FontAwesome.USER;
    }

    @Override
    public int order() {
        return 0;
    }
}
