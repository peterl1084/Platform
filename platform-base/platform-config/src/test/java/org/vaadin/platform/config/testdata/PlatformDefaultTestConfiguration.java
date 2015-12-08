package org.vaadin.platform.config.testdata;

import org.vaadin.platform.config.DefaultConfiguration;

@DefaultConfiguration
public class PlatformDefaultTestConfiguration {

    public Class<? extends TestTypeA> getTestTypeA() {
        return TestTypeAImpl1.class;
    }

    public Class<TestTypeBImpl1> getTestTypeB() {
        return TestTypeBImpl1.class;
    }
}
