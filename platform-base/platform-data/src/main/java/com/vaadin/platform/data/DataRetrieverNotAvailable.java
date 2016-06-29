package com.vaadin.platform.data;

public class DataRetrieverNotAvailable extends RuntimeException {
    private static final long serialVersionUID = 3981241192253370279L;
    private Class<?> dtoType;

    public DataRetrieverNotAvailable(Class<?> dtoType) {
        super("No retriever available for type " + dtoType.getCanonicalName());
        this.dtoType = dtoType;
    }

    public Class<?> getDtoType() {
        return dtoType;
    }
}
