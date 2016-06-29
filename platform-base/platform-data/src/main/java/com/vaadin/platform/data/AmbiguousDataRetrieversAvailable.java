package com.vaadin.platform.data;

public class AmbiguousDataRetrieversAvailable extends RuntimeException {
    private static final long serialVersionUID = 5147826230059147246L;
    private Class<?> dtoType;

    public AmbiguousDataRetrieversAvailable(Class<?> dtoType) {
        super("Ambiguous data retrievers available for type " + dtoType.getCanonicalName());
        this.dtoType = dtoType;
    }

    public Class<?> getDtoType() {
        return dtoType;
    }
}
