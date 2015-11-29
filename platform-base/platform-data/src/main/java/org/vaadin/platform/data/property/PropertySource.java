package org.vaadin.platform.data.property;

/**
 * PropertySource is a PropertySet with added capability of having property
 * values.
 */
public interface PropertySource extends PropertySet {

    /**
     * @param propertyId
     * @return value of given propertyId or null if no property with given
     *         propertyId is found.
     * @throws IllegalArgumentException
     *             if propertyId is null
     */
    Object getPropertyValue(String propertyId);

    /**
     * 
     * @param propertyId
     * @param expectedType
     * @return typed value of given propertyId or null if no property with given
     *         propertyId is found or if its of different non-assignable type of
     *         given expectedType.
     * @throws IllegalArgumentException
     *             if propertyId is null
     * @throws IllegalArgumentException
     *             if expectedType is null
     */
    <T> T getPropertyValue(String propertyId, Class<T> expectedType);
}
