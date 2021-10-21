package net.shadowdefender.toolkit.standard.machine.impl;

import org.junit.Assert;
import org.junit.Test;
import net.shadowdefender.toolkit.standard.BaseUnitTest;
import net.shadowdefender.toolkit.standard.machine.MachineRepository;

/**
 * MachineRepositoryFactoryTest
 *
 * @author shadow
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