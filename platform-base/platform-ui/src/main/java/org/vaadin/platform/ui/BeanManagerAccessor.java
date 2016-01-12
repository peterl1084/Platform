package org.vaadin.platform.ui;

import javax.enterprise.inject.spi.BeanManager;

import com.vaadin.cdi.internal.CDIUtil;

public class BeanManagerAccessor {

    private static BeanManager beanManager;

    public static BeanManager getBeanManager() {
        if (beanManager == null) {
            beanManager = CDIUtil.lookupBeanManager();
        }

        return beanManager;
    }
}
