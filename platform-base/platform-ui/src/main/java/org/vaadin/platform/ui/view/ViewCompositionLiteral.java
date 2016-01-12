package org.vaadin.platform.ui.view;

import javax.enterprise.util.AnnotationLiteral;

import com.vaadin.ui.UI;

class ViewCompositionLiteral extends AnnotationLiteral<ViewComposition> implements ViewComposition {
    private static final long serialVersionUID = -5760308843321729813L;

    @Override
    public String name() {
        return null;
    }

    @Override
    public Class<? extends UI>[] uis() {
        return null;
    }
}
