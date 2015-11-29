package org.vaadin.platform.data.property;

/**
 * PropertyValueAdder is a role interface for PropertySources that allows adding
 * properties with values.
 */
public interface PropertyValueAdder {

    /**
     * Adds any object as property with given propertyId. If an existing
     * property with same propertyId existed it will be overwritten with new
     * given propertyValue. This method cannot be used for adding null values.
     * 
     * @param propertyId
     * @param value
     * @return true if change was made, false if nothing changed.
     */
    boolean addProperty(String propertyId, Object value);

    /**
     * Adds property with given propertyId, propertyType and propertyValue. If
     * an existing property with same propertyId exists it will be overwritten
     * with new given value. With this method its possible to add typed
     * properties with null values.
     * 
     * @param propertyId
     * @param propertyValue
     * @return true if change was made, false if nothing changed.
     */
    <T> boolean addProperty(String propertyId, Class<T> propertyType, T propertyValue);
}
