package net.shadowdefender.toolkit.standard.machine.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import net.shadowdefender.toolkit.standard.machine.Machine;
import net.shadowdefender.toolkit.standard.machine.MachineRepository;
import net.shadowdefender.toolkit.standard.machine.abstracts.AbstractMachine;
import net.shadowdefender.toolkit.standard.machine.exception.CannotStartMachineException;
import net.shadowdefender.toolkit.standard.machine.exception.CannotStopMachineException;
import net.shadowdefender.toolkit.standard.repository.exception.ObjectRepositoryException;

/**
 * DefaultMachineRepositoryTest
 *
 * @author shadow
 */
public class DefaultMachineRepositoryTest {

    @Test
    public void test() {

        String expectedObjectId = "objectId";

        MachineRepository objectRepository = new DefaultMachineRepository();

        objectRepository.lookup("");
        objectRepository.lookup(null);

        Machine expected = new AbstractMachine() {

            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }
        };
        boolean actualFlag = false;

        //register
        try {
            objectRepository.register("", null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(null, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);
        actualFlag = false;
        try {
            objectRepository.register(expectedObjectId, null);
        } catch (ObjectRepositoryException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        //unregister
        objectRepository.unregister("");
        objectRepository.unregister(null);

        Machine actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);

        objectRepository.register(expectedObjectId, expected);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertEquals(actual, expected);

        Collection<Machine> actuals = (Collection<Machine>)objectRepository.lookupAll();
        Assert.assertEquals(actuals.size(), 1);
        Assert.assertEquals(actuals.iterator().next(), expected);
        try {
            actuals.add(expected);
        } catch (UnsupportedOperationException e) {
            actualFlag = true;
        }
        Assert.assertTrue(actualFlag);

        objectRepository.unregister(expectedObjectId);
        actual = objectRepository.lookup(expectedObjectId);
        Assert.assertNull(actual);
    }

    @Test
    public void buildKey() {

        String groupId = "groupId";
        String machineId = "machineId";

        MachineRepository objectRepository = new DefaultMachineRepository();
        String actual = objectRepository.buildKey(groupId, machineId);
        Assert.assertEquals(String.format("%s:%s", groupId, machineId), actual);
    }
}
