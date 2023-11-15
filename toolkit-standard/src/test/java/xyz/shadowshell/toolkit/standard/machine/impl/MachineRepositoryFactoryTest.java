package xyz.shadowshell.toolkit.standard.machine.impl;

import org.junit.Assert;
import org.junit.Test;
import xyz.shadowshell.toolkit.standard.BaseUnitTest;
import xyz.shadowshell.toolkit.standard.machine.MachineRepository;

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