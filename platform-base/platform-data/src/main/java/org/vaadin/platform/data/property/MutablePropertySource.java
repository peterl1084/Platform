package org.vaadin.platform.data.property;

public class MutablePropertySource extends BasePropertySource implements PropertyValueAdder {
    private static final long serialVersionUID = -2301280363651374494L;

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean addProperty(String propertyId, Object value) {
        if (propertyId == null) {
            return false;
        }
        if (value == null) {
            return false;
        }

        return addProperty(propertyId, (Class) value.getClass(), value);
    }

    @Override
    public <T> boolean addProperty(String propertyId, Class<T> propertyType, T value) {
        if (propertyId == null) {
            return false;
        }
        if (propertyType == null) {
            return false;
        }

        boolean changed = false;

        Class<?> oldType = propertyTypes.put(propertyId, propertyType);
        if (oldType == null || !propertyType.equals(oldType)) {
            changed = true;
        }

        Object oldValue = propertyValues.put(propertyId, value);
        if (oldValue == null && value != null) {
            changed = true;
        }
        if (oldValue != null && !oldValue.equals(value)) {
            changed = true;
        }

        return changed;
    }
}
