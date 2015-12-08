package org.vaadin.platform.config;

import java.util.Optional;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vaadin.platform.config.testdata.TestTypeA;
import org.vaadin.platform.config.testdata.TestTypeB;

@RunWith(CdiRunner.class)
@AdditionalClasses({}) // No configurations in classpath
public class TestConfigurationNoConfigurationsFound {

    @Inject
    private ConfigurationBean configuration;

    @Test
    public void testFetchingDefaultConfiguration() {
        Assert.assertEquals(Optional.empty(), configuration.findConfiguredType(TestTypeA.class));
        Assert.assertEquals(Optional.empty(), configuration.findConfiguredType(TestTypeB.class));
    }
}
