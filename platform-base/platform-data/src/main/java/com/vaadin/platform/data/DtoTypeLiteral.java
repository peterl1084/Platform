package com.vaadin.platform.data;

import javax.enterprise.util.AnnotationLiteral;

class DtoTypeLiteral extends AnnotationLiteral<DtoType> implements DtoType {
    private static final long serialVersionUID = 3825193994680093674L;
    private final Class<?> value;

    public DtoTypeLiteral(final Class<?> value) {
        this.value = value;
    }

    @Override
    public Class<?> value() {
        return value;
    }
}
