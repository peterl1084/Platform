package org.vaadin.platform.data.property;

import java.util.Arrays;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TestMutablePropertySource {

    @Test
    public void testMutablePropertySource_NoPropertiesInSource_SizeZeroAndNullTypesReturned() {
        MutablePropertySource ps = new MutablePropertySource();
        Assert.assertTrue(ps.getPropertyIds().isEmpty());
        Assert.assertNull(ps.getPropertyValue("firstname"));

        String value = ps.getPropertyValue("firstname", String.class);
        Assert.assertNull(value);
    }

    @Test
    public void testMutablePropertySource_PropertyAdded_PropertyExistsWithCorrectValueAndType() {
        MutablePropertySource ps = new MutablePropertySource();

        Assert.assertTrue(ps.addProperty("firstname", String.class, "Peter"));
        Assert.assertTrue(ps.addProperty("lastname", "Lehto"));
        Assert.assertTrue(ps.addProperty("dateOfBirth", Date.class, null));

        Assert.assertEquals(3, ps.getPropertyIds().size());
        Assert.assertEquals(Arrays.asList("firstname", "lastname", "dateOfBirth"), ps.getPropertyIds());

        Assert.assertEquals(String.class, ps.getPropertyType("firstname"));
        Assert.assertEquals(String.class, ps.getPropertyType("lastname"));
        Assert.assertEquals(Date.class, ps.getPropertyType("dateOfBirth"));

        Assert.assertEquals("Peter", ps.getPropertyValue("firstname"));
        CharSequence charSequenceOfString = ps.getPropertyValue("firstname", String.class);
        Assert.assertEquals("Peter", charSequenceOfString);

        CharSequence charSequence = ps.getPropertyValue("firstname", CharSequence.class);
        Assert.assertEquals("Peter", charSequence);

        Assert.assertEquals("Lehto", ps.getPropertyValue("lastname"));
        Assert.assertNull(ps.getPropertyValue("dateOfBirth"));
    }

    @Test
    public void testMutablePropertySource_PropertyAdded_PropertyAlreadyExistedWithSameTypeAndValue_NoChangeMade() {
        MutablePropertySource ps = new MutablePropertySource();

        Assert.assertTrue(ps.addProperty("firstname", String.class, "Peter"));
        Assert.assertFalse(ps.addProperty("firstname", "Peter"));
    }

    @Test
    public void testMutablePropertySource_PropertyAdded_PropertyAlreadyExistedWithSameTypeAndValue_NoChangeMade2() {
        MutablePropertySource ps = new MutablePropertySource();

        Assert.assertTrue(ps.addProperty("firstname", "Peter"));
        Assert.assertFalse(ps.addProperty("firstname", String.class, "Peter"));
    }
}
