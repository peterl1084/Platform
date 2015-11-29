package org.vaadin.platform.data.property;

/**
 * MutablePropertySet is BasePropertySet with added functionality to add
 * properties.
 */
public class MutablePropertySet extends BasePropertySet implements PropertyTypeAdder {
    private static final long serialVersionUID = -7944003007350352629L;

    @Override
    public boolean addPropertyType(String propertyId, Class<?> propertyType) {
        if (propertyId == null) {
            return false;
        }

        if (propertyType == null) {
            return false;
        }

        Class<?> oldType = propertyTypes.put(propertyId, propertyType);
        if (oldType == null) {
            return true;
        } else {
            return !propertyType.equals(oldType);
        }
    }
}
