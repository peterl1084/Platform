package org.vaadin.platform.data.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * BasePropertySet is the base implementation of PropertySet that can be used
 * as-is.
 */
public class BasePropertySet implements PropertySet {
    private static final long serialVersionUID = 1412195286151799401L;

    protected Map<String, Class<?>> propertyTypes;

    public BasePropertySet() {
        propertyTypes = new LinkedHashMap<>();
    }

    @Override
    public Collection<String> getPropertyIds() {
        List<String> propertyIds = new ArrayList<>(propertyTypes.size());
        propertyIds.addAll(propertyTypes.keySet());
        return propertyIds;
    }

    @Override
    public Class<?> getPropertyType(String propertyId) {
        return propertyTypes.get(propertyId);
    }
}
