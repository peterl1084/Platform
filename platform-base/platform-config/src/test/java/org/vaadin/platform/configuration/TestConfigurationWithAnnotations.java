package org.vaadin.platform.configuration;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vaadin.platform.configuration.testdata.PlatformAnnotationConfiguration;
import org.vaadin.platform.configuration.testdata.TestTypeA;
import org.vaadin.platform.configuration.testdata.TestTypeAImpl1;
import org.vaadin.platform.configuration.testdata.TestTypeAImpl2;
import org.vaadin.platform.configuration.testdata.TestTypeB;
import org.vaadin.platform.configuration.testdata.TestTypeBImpl1;

@RunWith(CdiRunner.class)
@AdditionalClasses({ ConfigurationProviderBean.class, PlatformAnnotationConfiguration.class })
public class TestConfigurationWithAnnotations {

    @Inject
    private ConfigurationProvider configuration;

    @Test
    public void testFindTypeWithAnnoations() {
        Assert.assertEquals(TestTypeAImpl1.class,
                configuration.findConfiguredType(TestTypeA.class, new InitialViewLiteral()).get());
        Assert.assertEquals(TestTypeAImpl2.class, configuration.findConfiguredType(TestTypeA.class).get());
        Assert.assertEquals(TestTypeBImpl1.class, configuration.findConfiguredType(TestTypeB.class).get());
    }
}
