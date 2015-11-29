package org.vaadin.platform.data.property;

import java.io.Serializable;

/**
 * PropertyTypeAdder is a role interface for PropertySets that allows adding
 * properties with type information.
 */
public interface PropertyTypeAdder extends Serializable {

    /**
     * Adds propertyId with given propertyType. If propertyId or propertyType is
     * null no action is performed.
     * 
     * @param propertyId
     * @param propertyType
     * @return true if change was made, false otherwise.
     */
    boolean addPropertyType(String propertyId, Class<?> propertyType);
}
