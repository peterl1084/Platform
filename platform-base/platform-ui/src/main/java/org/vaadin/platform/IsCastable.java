package org.vaadin.platform;

/**
 * IsCastable allows casting this object to type T. The purpose of this
 * interface is twofold: it works as marker that tells that the implementation
 * of an interface of some type can be cast to T safely and it also allows
 * casting with one method invocation to correct return type.
 */
public interface IsCastable<T> {

    /**
     * @return this object as Component.
     */
    default T asCasted() {
        return (T) this;
    }
}
