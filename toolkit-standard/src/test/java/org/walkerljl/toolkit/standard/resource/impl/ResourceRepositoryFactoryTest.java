package org.walkerljl.toolkit.standard.resource.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.toolkit.standard.BaseUnitTest;
import org.walkerljl.toolkit.standard.machine.MachineRepository;
import org.walkerljl.toolkit.standard.machine.impl.DefaultMachineRepository;
import org.walkerljl.toolkit.standard.machine.impl.MachineRepositoryFactory;
import org.walkerljl.toolkit.standard.resource.ResourceRepository;

/**
 * ResourceRepositoryFactoryTest
 *
 * @author xingxun
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