package org.vaadin.platform.config.testdata;

import org.vaadin.platform.config.PlatformUserConfiguration;

@PlatformUserConfiguration
public class PlatformUserTestConfiguration {

    public Class<? extends TestTypeA> getMyTestTypeA() {
        return TestTypeAImpl2.class;
    }

    public Class<? extends TestTypeB> getMyTestTypeB() {
        return TestTypeBExtension.class;
    }
}
