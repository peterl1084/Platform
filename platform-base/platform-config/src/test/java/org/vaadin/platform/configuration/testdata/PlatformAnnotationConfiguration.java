package org.vaadin.platform.configuration.testdata;

import org.vaadin.platform.configuration.DefaultConfiguration;
import org.vaadin.platform.configuration.InitialView;

@DefaultConfiguration
public class PlatformAnnotationConfiguration {

    public Class<? extends TestTypeA> getFirstView() {
        return TestTypeAImpl2.class;
    }

    @InitialView
    public Class<? extends TestTypeA> getInitialView() {
        return TestTypeAImpl1.class;
    }

    public Class<? extends TestTypeB> getSecondView() {
        return TestTypeBImpl1.class;
    }
}
