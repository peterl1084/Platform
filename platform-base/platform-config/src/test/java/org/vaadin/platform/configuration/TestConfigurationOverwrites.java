package org.vaadin.platform.configuration;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vaadin.platform.configuration.ConfigurationProviderBean;
import org.vaadin.platform.configuration.testdata.PlatformDefaultTestConfiguration;
import org.vaadin.platform.configuration.testdata.PlatformUserTestConfiguration;
import org.vaadin.platform.configuration.testdata.TestTypeA;
import org.vaadin.platform.configuration.testdata.TestTypeAImpl2;
import org.vaadin.platform.configuration.testdata.TestTypeB;
import org.vaadin.platform.configuration.testdata.TestTypeBExtension;

@RunWith(CdiRunner.class)
@AdditionalClasses({ PlatformDefaultTestConfiguration.class, PlatformUserTestConfiguration.class })
public class TestConfigurationOverwrites {

    @Inject
    private ConfigurationProviderBean configuration;

    @Test
    public void testFetchingDefaultConfiguration() {
        Assert.assertEquals(TestTypeAImpl2.class, configuration.findConfiguredType(TestTypeA.class).get());
        Assert.assertEquals(TestTypeBExtension.class, configuration.findConfiguredType(TestTypeB.class).get());
    }
}
