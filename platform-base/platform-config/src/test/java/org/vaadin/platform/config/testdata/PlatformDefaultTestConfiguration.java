package org.vaadin.platform.config.testdata;

import org.vaadin.platform.config.PlatformDefaultConfiguration;

@PlatformDefaultConfiguration
public class PlatformDefaultTestConfiguration {

    public Class<? extends TestTypeA> getTestTypeA() {
        return TestTypeAImpl1.class;
    }

    public Class<? extends TestTypeB> getTestTypeB() {
        return TestTypeBImpl1.class;
    }
}
