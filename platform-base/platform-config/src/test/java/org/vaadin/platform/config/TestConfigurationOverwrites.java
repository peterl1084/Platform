package org.vaadin.platform.config;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vaadin.platform.config.testdata.PlatformDefaultTestConfiguration;
import org.vaadin.platform.config.testdata.PlatformUserTestConfiguration;
import org.vaadin.platform.config.testdata.TestTypeA;
import org.vaadin.platform.config.testdata.TestTypeAImpl2;
import org.vaadin.platform.config.testdata.TestTypeB;
import org.vaadin.platform.config.testdata.TestTypeBExtension;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PlatformDefaultTestConfiguration.class, PlatformUserTestConfiguration.class })
public class TestConfigurationOverwrites {

    @Inject
    private Configuration configuration;

    @Test
    public void testFetchingDefaultConfiguration() {
        Assert.assertEquals(TestTypeAImpl2.class, configuration.findConfiguredType(TestTypeA.class).get());
        Assert.assertEquals(TestTypeBExtension.class, configuration.findConfiguredType(TestTypeB.class).get());
    }
}
