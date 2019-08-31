package org.walkerljl.toolkit.standard.machine.impl;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.toolkit.standard.BaseUnitTest;
import org.walkerljl.toolkit.standard.machine.MachineRepository;

/**
 * MachineRepositoryFactoryTest
 *
 * @author xingxun
 */
public class MachineRepositoryFactoryTest extends BaseUnitTest {

    @Test
    public void getDefaultRepository() {

        MachineRepository expected = MachineRepositoryFactory.getDefaultRepository();
        MachineRepository actual = MachineRepositoryFactory.getDefaultRepository();

        Assert.assertTrue(actual instanceof DefaultMachineRepository);
        Assert.assertEquals(expected, actual);
    }

}