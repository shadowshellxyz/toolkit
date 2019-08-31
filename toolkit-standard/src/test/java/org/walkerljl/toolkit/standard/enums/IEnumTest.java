package org.walkerljl.toolkit.standard.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * IEnumTest
 *
 * @author xingxun
 */
public class IEnumTest {

    @Test
    public void test() {

        IEnum actual = new IEnum() {

            @Override
            public String getCode() {
                return "code";
            }

            @Override
            public String getDescription() {
                return "description";
            }

            @Override
            public IEnum getEnumObject(String code) {
                return this;
            }
        };

        Assert.assertEquals("code", actual.getCode());
        Assert.assertEquals("description", actual.getDescription());
        Assert.assertEquals(actual, actual.getEnumObject(actual.getCode()));

    }
}