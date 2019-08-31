package org.walkerljl.toolkit.standard.machine.abstracts;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;
import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.MachineRepository;
import org.walkerljl.toolkit.standard.machine.exception.CannotStartMachineException;
import org.walkerljl.toolkit.standard.machine.exception.CannotStopMachineException;
import org.walkerljl.toolkit.standard.machine.exception.MachineException;
import org.walkerljl.toolkit.standard.machine.exception.MachineRunException;
import org.walkerljl.toolkit.standard.machine.impl.MachineRepositoryFactory;
import org.walkerljl.toolkit.standard.resource.ResourceRepository;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;
import org.walkerljl.toolkit.standard.resource.impl.ResourceRepositoryFactory;

/**
 * AbstractMachineTest
 *
 * @author xingxun
 * @Date 2016/1/3
 */
public class AbstractMachineTest {

    @Test
    public void identity() {

        DefaultMacine actual = new DefaultMacine("identity");

        Assert.assertEquals("DefaultMacine", actual.getId());
        Assert.assertEquals(actual.getId(), actual.getName());
        Assert.assertEquals("identity", actual.getGroup());
    }

    @Test
    public void startAndStop() {

        DefaultMacine actual = new DefaultMacine("startAndStop");

        MachineRepository objectRepository = MachineRepositoryFactory.getDefaultRepository();
        String key = objectRepository.buildKey(actual.getGroup(), actual.getId());

        Assert.assertNull(objectRepository.lookup(key));
        Assert.assertFalse(actual.isInited());

        actual.start();
        Assert.assertTrue(actual.isInited());
        Assert.assertTrue(actual.isStarted());
        Assert.assertTrue(actual.isRunning());
        Assert.assertEquals(actual, objectRepository.lookup(key));

        actual.stop();
        Assert.assertFalse(actual.isRunning());
        Assert.assertNull(objectRepository.lookup(key));
    }

    @Test
    public void initAndDestroy() {

        DefaultMacine actual = new DefaultMacine("initAndDestroy");

        ResourceRepository objectRepository = ResourceRepositoryFactory.getDefaultRepository();
        String key = objectRepository.buildKey(actual.getGroup(), actual.getId());

        Assert.assertNull(objectRepository.lookup(key));
        Assert.assertFalse(actual.isInited());

        actual.init();
        Assert.assertTrue(actual.isInited());
        Assert.assertEquals(actual, objectRepository.lookup(key));

        actual.destroy();
        Assert.assertFalse(actual.isInited());
        Assert.assertNull(objectRepository.lookup(key));
    }

    @Test
    public void isRunning() {

        DefaultMacine actual = new DefaultMacine("isRunning");

        Assert.assertFalse(actual.isRunning());

        actual.start();
        Assert.assertTrue(actual.isRunning());

        actual.stop();
        Assert.assertFalse(actual.isRunning());
    }

    @Test
    public void restart() {

        DefaultMacine actual = new DefaultMacine("restart");

        Assert.assertFalse(actual.isRunning());

        actual.restart();
        Assert.assertTrue(actual.isRunning());

        actual.stop();
        actual.restart();
        Assert.assertTrue(actual.isRunning());
    }

    @Test
    public void callbackOnSuccessToStart() {

        DefaultMacine actual = new DefaultMacine("callbackOnSuccessToStart");
        Assert.assertFalse(actual.isCallbackOnSuccessToStart());

        actual.start();
        Assert.assertTrue(actual.isCallbackOnSuccessToStart());
    }

    @Test
    public void run() {

        DefaultMacine actual = new DefaultMacine("run");
        Assert.assertFalse(actual.isProcessRun());

        actual.run();
        Assert.assertFalse(actual.isProcessRun());

        actual.start();
        actual.run();
        Assert.assertTrue(actual.isProcessRun());
    }

    @Test
    public void callbackOnSuccessToStop() {

        DefaultMacine actual = new DefaultMacine("callbackOnSuccessToStop");
        Assert.assertFalse(actual.isCallbackOnSuccessToStop());

        actual.start();
        Assert.assertFalse(actual.isCallbackOnSuccessToStop());

        actual.stop();
        Assert.assertTrue(actual.isCallbackOnSuccessToStop());
    }

    @Test
    public void callbackOnFailureToStart() {

        final AtomicInteger operationFalg = new AtomicInteger(0);
        Machine actual = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {
                throw new RuntimeException();
            }

            @Override
            public void processStop() throws CannotStopMachineException {

            }

            @Override
            public void callbackOnFailureToStart() throws MachineException {
                operationFalg.set(1);
            }
        };

        try {
            actual.start();
        } catch (Throwable e) {
            //ignore
        }
        Assert.assertEquals(1, operationFalg.get());
    }

    @Test
    public void callbackOnFailureToStop() {

        final AtomicInteger operationFalg = new AtomicInteger(0);
        Machine actual = new AbstractMachine() {
            @Override
            public void processStart() throws CannotStartMachineException {

            }

            @Override
            public void processStop() throws CannotStopMachineException {
                throw new RuntimeException();
            }

            @Override
            public void callbackOnFailureToStop() throws MachineException {
                operationFalg.set(1);
            }
        };

        actual.start();
        try {
           actual.stop();
        } catch (Throwable e) {
           //ignore
        }
        Assert.assertEquals(1, operationFalg.get());
    }
}

class DefaultMacine extends AbstractMachine implements Machine {

    private boolean inited = false;
    private boolean started = false;
    private boolean callbackOnSuccessToStart = false;
    private boolean processRun = false;
    private boolean callbackOnSuccessToStop = false;

    private String groupId;

    public DefaultMacine(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getGroup() {
        return groupId;
    }

    @Override
    public void processStart() throws CannotStartMachineException {
        started = true;
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        started = false;
    }

    @Override
    public void processInit() throws CannotInitResourceException {
        inited = true;
    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {
        inited = false;
    }

    @Override
    public void callbackOnSuccessToStart() throws MachineException {
        callbackOnSuccessToStart = true;
    }

    @Override
    protected void processRun() throws MachineRunException {
        processRun = true;
    }

    @Override
    public void callbackOnSuccessToStop() throws MachineException {
        callbackOnSuccessToStop = true;
    }

    public boolean isInited() {
        return inited;
    }

    /**
     * Getter method for property <tt>started</tt>.
     *
     * @return property value of started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Getter method for property <tt>callbackOnSuccessToStart</tt>.
     *
     * @return property value of callbackOnSuccessToStart
     */
    public boolean isCallbackOnSuccessToStart() {
        return callbackOnSuccessToStart;
    }

    /**
     * Getter method for property <tt>processRun</tt>.
     *
     * @return property value of processRun
     */
    public boolean isProcessRun() {
        return processRun;
    }

    /**
     * Getter method for property <tt>callbackOnSuccessToStop</tt>.
     *
     * @return property value of callbackOnSuccessToStop
     */
    public boolean isCallbackOnSuccessToStop() {
        return callbackOnSuccessToStop;
    }
}
