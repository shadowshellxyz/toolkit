package net.shadowdefender.toolkit.template.store;

import java.util.concurrent.BlockingQueue;

import net.shadowdefender.toolkit.standard.exception.AppException;
import net.shadowdefender.toolkit.standard.machine.Machine;
import net.shadowdefender.toolkit.standard.machine.abstracts.AbstractMachine;
import net.shadowdefender.toolkit.standard.machine.exception.CannotStartMachineException;
import net.shadowdefender.toolkit.standard.machine.exception.CannotStopMachineException;
import net.shadowdefender.toolkit.standard.machine.exception.MachineException;

/**
 * LocalQueueStoreTemplate
 *
 * @author shadow
 * @Date 2016/12/19
 */
public class LocalQueueStoreTemplate<T> extends AbstractMachine implements Machine {

    private String           serviceGroup;
    private String           serviceId;
    private BlockingQueue<T> blockingQueue;
    private Storer           storer;

    public LocalQueueStoreTemplate(String serviceGroup, String serviceId, BlockingQueue<T> blockingQueue, Storer<T> storer) {
        this.serviceGroup = serviceGroup;
        this.serviceId = serviceId;
        this.blockingQueue = blockingQueue;
        this.storer = storer;
    }

    @Override
    public String getId() {
        return serviceId;
    }

    @Override
    public String getGroup() {
        return serviceGroup;
    }

    @Override
    public void processStart() throws CannotStartMachineException {

    }

    @Override
    protected void processRun() throws MachineException {
        while (isRunning() || (!isRunning() && blockingQueue.size() > 0)) {
            T element = null;
            try {
                element = blockingQueue.take();
                storer.store(element);
            } catch (Throwable e) {
                //TraceUtils.traceExceptionQuietly(getServerName() + " occurs error, skip and continue.",
                //        new Object[]{element}, e);
                throw new AppException(e);
            }
        }
    }

    @Override
    public void processStop() throws CannotStopMachineException {

    }

    @Override
    public String getInstanceId() {
        return Thread.currentThread().getId() + "";
    }
}
