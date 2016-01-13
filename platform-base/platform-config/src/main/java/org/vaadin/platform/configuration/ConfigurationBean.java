package org.vaadin.platform.configuration;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
class ConfigurationBean implements ConfigurationProvider {

    @Inject
    @DefaultConfiguration
    private Instance<Object> defaultConfigurations;

    @Inject
    @SpecializedConfiguration
    private Instance<Object> userConfigurations;

    /**
     * Looks up for a configured value that is expected to be a sub type of
     * given specific type from all default and user specific configuration
     * beans available in the classpath.
     * 
     * @param baseType
     * @return the configured type of given specified base type.
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T> Optional<Class<? extends T>> findConfiguredType(Class<T> baseType) {
        try {
            List<Object> configurationObjects = findConfigurationObjects();
            for (Object object : configurationObjects) {
                List<Method> methods = Arrays.asList(object.getClass().getMethods());

                for (Method method : methods) {
                    Class<?> genericReturnType = extractGenericReturnType(method);
                    if (genericReturnType == null) {
                        continue;
                    }

                    if (baseType.isAssignableFrom(genericReturnType)) {
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }

                        return Optional.of((Class) method.invoke(object));
                    }
                }
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("rawtypes")
    private Class<?> extractGenericReturnType(Method method) {
        Type returnType = method.getGenericReturnType();

        if (returnType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) returnType;
            List<Type> typeArguments = Arrays.asList(pType.getActualTypeArguments());
            if (typeArguments.size() != 1) {
                return null;
            }
            for (Type type : typeArguments) {
                if (type instanceof WildcardType) {
                    WildcardType wcType = (WildcardType) type;
                    Type wcTypeLowerBound = Arrays.asList(wcType.getUpperBounds()).get(0);
                    return (Class) wcTypeLowerBound;
                } else {
                    return (Class) type;
                }
            }
        }

        return null;
    }

    private List<Object> findConfigurationObjects() {
        Deque<Object> configs = new ArrayDeque<>();
        findDefaultConfigurationObject().forEach(config -> configs.addLast(config));
        findUserConfigurationObjects().forEach(config -> configs.addFirst(config));
        return Collections.unmodifiableList(new ArrayList<>(configs));
    }

    private List<Object> findUserConfigurationObjects() {
        return StreamSupport.stream(userConfigurations.spliterator(), false).collect(Collectors.toList());
    }

    private List<Object> findDefaultConfigurationObject() {
        return StreamSupport.stream(defaultConfigurations.spliterator(), false).collect(Collectors.toList());
    }
}
