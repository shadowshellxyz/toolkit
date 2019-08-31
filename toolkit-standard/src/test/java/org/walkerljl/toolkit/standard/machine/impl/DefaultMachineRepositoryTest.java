package org.walkerljl.toolkit.standard.machine.impl;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.MachineRepository;
import org.walkerljl.toolkit.standard.machine.abstracts.AbstractMachine;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.machine.impl.DefaultMachineRepository;
import org.walkerljl.toolkit.standard.repository.exception.ObjectRepositoryException;
import org.walkerljl.toolkit.standard.resource.Resource;
import org.walkerljl.toolkit.standard.resource.ResourceRepository;
import org.walkerljl.toolkit.standard.resource.abstracts.AbstractResource;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.standard.resource.impl.DefaultResourceRepository;

/**
 * DefaultMachineRepositoryTest
 *
 * @author xingxun
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
