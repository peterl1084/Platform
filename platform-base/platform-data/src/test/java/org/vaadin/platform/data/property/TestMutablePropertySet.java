package org.vaadin.platform.data.property;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class TestMutablePropertySet {

    @Test
    public void testMutablePropertySet_NoPropertiesInSet_PropertyTypeForPropertyIdRequested_NullReturned() {
        MutablePropertySet ps = new MutablePropertySet();
        Assert.assertTrue(ps.getPropertyIds().isEmpty());
        Assert.assertNull(ps.getPropertyType("firstname"));
        Assert.assertFalse(ps.hasProperty("firstname"));
        Assert.assertFalse(ps.hasProperty("firstname", String.class));
    }

    @Test
    public void testMutablePropertySet_PropertiesAdded_PropertiesExistsAndHaveCorrectTypes() {
        MutablePropertySet ps = new MutablePropertySet();

        Assert.assertTrue(ps.addPropertyType("firstname", String.class));
        Assert.assertTrue(ps.addPropertyType("lastname", String.class));

        Assert.assertEquals(Arrays.asList("firstname", "lastname"), ps.getPropertyIds());

        Assert.assertEquals(String.class, ps.getPropertyType("firstname"));
        Assert.assertEquals(String.class, ps.getPropertyType("lastname"));

        Assert.assertTrue(ps.hasProperty("firstname", String.class));
        Assert.assertTrue(ps.hasProperty("lastname", String.class));

        // Should also support super class types.
        Assert.assertTrue(ps.hasProperty("firstname", CharSequence.class));
    }

    @Test
    public void testMutablePropertySet_PropertiesAdded_PropertyWithSameIdButDifferentTypeExist_TypeChanged() {
        MutablePropertySet ps = new MutablePropertySet();

        Assert.assertTrue(ps.addPropertyType("firstname", String.class));
        Assert.assertEquals(String.class, ps.getPropertyType("firstname"));

        Assert.assertTrue(ps.addPropertyType("lastname", String.class));

        Assert.assertTrue(ps.addPropertyType("firstname", CharSequence.class));
        Assert.assertEquals(CharSequence.class, ps.getPropertyType("firstname"));
    }

    @Test
    public void testMutablePropertySet_PropertiesAdded_PropertyWithSameIdAndTypeAlreadyExists_NoChangeMade() {
        MutablePropertySet ps = new MutablePropertySet();

        Assert.assertTrue(ps.addPropertyType("firstname", String.class));
        Assert.assertTrue(ps.addPropertyType("lastname", String.class));

        Assert.assertFalse(ps.addPropertyType("firstname", String.class));
        Assert.assertFalse(ps.addPropertyType("lastname", String.class));
    }

    @Test
    public void testMutablePropertySet_PropertiesInSet_PropertyQueriedWithDifferentType_FalseReturned() {
        MutablePropertySet ps = new MutablePropertySet();

        Assert.assertTrue(ps.addPropertyType("firstname", String.class));
        Assert.assertTrue(ps.addPropertyType("lastname", String.class));

        Assert.assertTrue(ps.hasProperty("firstname"));
        Assert.assertTrue(ps.hasProperty("firstname", String.class));

        Assert.assertTrue(ps.hasProperty("lastname"));
        Assert.assertTrue(ps.hasProperty("lastname", String.class));

        Assert.assertFalse(ps.hasProperty("firstname", Double.class));
        Assert.assertFalse(ps.hasProperty("lastname", Integer.class));
    }
}
