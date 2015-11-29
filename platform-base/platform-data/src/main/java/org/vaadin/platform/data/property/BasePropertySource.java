package org.vaadin.platform.data.property;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePropertySource extends BasePropertySet implements PropertySource {
    private static final long serialVersionUID = -8284252184320522350L;

    protected Map<String, Object> propertyValues;

    public BasePropertySource() {
        propertyValues = new HashMap<>();
    }

    @Override
    public Object getPropertyValue(String propertyId) {
        if (propertyId == null) {
            throw new IllegalArgumentException("propertyId cannot be null");
        }

        return propertyValues.get(propertyId);
    }

    @Override
    public <T> T getPropertyValue(String propertyId, Class<T> expectedType) {
        if (expectedType == null) {
            throw new IllegalArgumentException("expectedType cannot be null");
        }

        Object propertyValue = getPropertyValue(propertyId);
        if (propertyValue == null) {
            return null;
        } else {
            if (expectedType.isAssignableFrom(propertyValue.getClass())) {
                return expectedType.cast(propertyValue);
            }

            return null;
        }
    }
}
