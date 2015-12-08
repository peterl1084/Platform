package org.vaadin.platform.config.testdata;

import org.vaadin.platform.config.SpecializedConfiguration;

@SpecializedConfiguration
public class PlatformUserTestConfiguration {

    public Class<? extends TestTypeA> getMyTestTypeA() {
        return TestTypeAImpl2.class;
    }

    public Class<? extends TestTypeB> getMyTestTypeB() {
        return TestTypeBExtension.class;
    }
}
