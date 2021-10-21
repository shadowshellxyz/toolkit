package net.shadowdefender.toolkit.standard.resource.impl;

import org.junit.Assert;
import org.junit.Test;
import net.shadowdefender.toolkit.standard.BaseUnitTest;
import net.shadowdefender.toolkit.standard.resource.ResourceRepository;

/**
 * ResourceRepositoryFactoryTest
 *
 * @author shadow
 */
public class ResourceRepositoryFactoryTest extends BaseUnitTest {

    @Test
    public void getDefaultRepository() {

        ResourceRepository expected = ResourceRepositoryFactory.getDefaultRepository();
        ResourceRepository actual = ResourceRepositoryFactory.getDefaultRepository();

        Assert.assertTrue(actual instanceof DefaultResourceRepository);
        Assert.assertEquals(expected, actual);
    }

}