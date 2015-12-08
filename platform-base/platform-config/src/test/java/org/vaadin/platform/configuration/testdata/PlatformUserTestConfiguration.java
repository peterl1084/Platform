package org.vaadin.platform.configuration.testdata;

import org.vaadin.platform.configuration.SpecializedConfiguration;

@SpecializedConfiguration
public class PlatformUserTestConfiguration {

    public Class<? extends TestTypeA> getMyTestTypeA() {
        return TestTypeAImpl2.class;
    }

    public Class<? extends TestTypeB> getMyTestTypeB() {
        return TestTypeBExtension.class;
    }
}
