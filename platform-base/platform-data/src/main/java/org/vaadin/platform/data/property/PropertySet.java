package org.vaadin.platform.data.property;

import java.io.Serializable;
import java.util.Collection;

/**
 * PropertySet is super interface for objects containing information about
 * property ids and their corresponding types. PropertySet alone does not
 * contain possible property values but rather just the id and type information
 * of which properties and types are available.
 */
public interface PropertySet extends Serializable {

    /**
     * @return Collection of property ids stored into this property set. The
     *         type of the collection is determined by particular
     *         implementation.
     */
    Collection<String> getPropertyIds();

    /**
     * @param propertyId
     * @return true if this PropertySource has a property with given propertyId,
     *         false otherwise.
     */
    default boolean hasProperty(String propertyId) {
        return getPropertyIds().contains(propertyId);
    }

    /**
     * @param propertyId
     * @param propertyType
     * @return true if this PropertySource has a property with given propertyId
     *         which is also of given propertyType or if propertyType is
     *         assignable from (super class of) real type of the property.
     */
    default boolean hasProperty(String propertyId, Class<?> propertyType) {
        if (propertyType == null) {
            throw new IllegalArgumentException("propertyType cannot be null");
        }

        if (hasProperty(propertyId)) {
            Class<?> realType = getPropertyType(propertyId);
            if (realType == null) {
                return false;
            }
            return propertyType.isAssignableFrom(realType);
        }

        return false;
    }

    /**
     * @param propertyId
     * @return type of the property with given propertyId. If no such property
     *         exists null will be returned.
     */
    Class<?> getPropertyType(String propertyId);
}
