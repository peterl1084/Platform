package org.vaadin.platform.config;

import java.lang.reflect.Method;
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
public class Configuration {

    @Inject
    @PlatformDefaultConfiguration
    private Instance<Object> defaultConfigurations;

    @Inject
    @PlatformUserConfiguration
    private Instance<Object> userConfigurations;

    /**
     * Looks up for a configured value that is expected to be a sub type of
     * given specific type from all default and user specific configuration
     * beans available in the classpath.
     * 
     * @param baseType
     * @return the configured type of given specified base type.
     */
    public <T> Optional<Class<? extends T>> findConfiguredType(Class<T> baseType) {
        try {
            List<Object> configurationObjects = findConfigurationObjects();
            for (Object object : configurationObjects) {
                List<Method> methods = Arrays.asList(object.getClass().getMethods());
                List<Method> methodsFiltered = methods.stream()
                        .filter(method -> method.getReturnType().equals(Class.class)).collect(Collectors.toList());

                for (Method method : methodsFiltered) {
                    Class result = (Class) method.invoke(object);
                    if (baseType.isAssignableFrom(result)) {
                        return Optional.of(result);
                    }
                }
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Object> findConfigurationObjects() {
        Deque<Object> configs = new ArrayDeque<>();
        configs.addLast(findDefaultConfigurationObject());
        findUserConfigurationObjects().forEach(config -> configs.addFirst(config));
        return Collections.unmodifiableList(new ArrayList<>(configs));
    }

    private List<Object> findUserConfigurationObjects() {
        return StreamSupport.stream(userConfigurations.spliterator(), false).collect(Collectors.toList());
    }

    private Object findDefaultConfigurationObject() {
        if (defaultConfigurations.isAmbiguous()) {
            throw new RuntimeException(
                    "More than one bean annotated with @PlatformDefaultConfiguration, please don't do that!");
        } else {
            return defaultConfigurations.get();
        }
    }
}
