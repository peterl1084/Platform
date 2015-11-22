package org.vaadin.platform.config;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vaadin.platform.config.testdata.PlatformDefaultTestConfiguration;
import org.vaadin.platform.config.testdata.TestTypeA;
import org.vaadin.platform.config.testdata.TestTypeAImpl1;
import org.vaadin.platform.config.testdata.TestTypeB;
import org.vaadin.platform.config.testdata.TestTypeBImpl1;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PlatformDefaultTestConfiguration.class })
public class TestConfigurationDefaults {

    @Inject
    private Configuration configuration;

    @Test
    public void testFetchingDefaultConfiguration() {
        Assert.assertEquals(TestTypeAImpl1.class, configuration.findConfiguredType(TestTypeA.class).get());
        Assert.assertEquals(TestTypeBImpl1.class, configuration.findConfiguredType(TestTypeB.class).get());
    }
}
