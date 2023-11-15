package xyz.shadowshell.toolkit.standard.machine.abstracts;

import xyz.shadowshell.toolkit.standard.machine.Machine;
import xyz.shadowshell.toolkit.standard.machine.exception.CannotStartMachineException;
import xyz.shadowshell.toolkit.standard.machine.exception.MachineException;
import xyz.shadowshell.toolkit.standard.machine.exception.MachineRunException;
import xyz.shadowshell.toolkit.standard.machine.impl.MachineRepositoryFactory;
import xyz.shadowshell.toolkit.standard.resource.abstracts.AbstractResource;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotDestroyResourceException;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotInitResourceException;
import xyz.shadowshell.toolkit.logging.Logger;
import xyz.shadowshell.toolkit.logging.LoggerFactory;
import xyz.shadowshell.toolkit.standard.machine.MachineRepository;
import xyz.shadowshell.toolkit.standard.machine.exception.CannotStopMachineException;

/**
 * AbstractMachine
 *
 * @author shadow
 * @Date 2016/12/9
 */
public abstract class AbstractMachine extends AbstractResource implements Machine {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMachine.class);

    /**
     * 是否初始化标志
     */
    private volatile boolean running = false;

    private MachineRepository machineRepository = MachineRepositoryFactory.getDefaultRepository();

    /**
     * 处理启动
     *
     * @throws CannotStartMachineException
     */
    public abstract void processStart() throws CannotStartMachineException;

    /**
     * 处理停止
     *
     * @throws CannotStopMachineException
     */
    public abstract void processStop() throws CannotStopMachineException;

    @Override
    public void processInit() throws CannotInitResourceException {

    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {

    }

    @Override
    public Machine start() throws CannotStartMachineException {

        long startTime = System.currentTimeMillis();
        try {
            if (!running) {
                synchronized (this) {
                    if (!running) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s is starting.", getServerName()));
                        }

                        init();
                        processStart();
                        machineRepository.register(machineRepository.buildKey(getGroup(), getId()), this);
                        running = true;

                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has started,consume %s milliseconds.", getServerName(),
                                    (System.currentTimeMillis() - startTime)));
                        }

                        //do call back
                        try {
                            callbackOnSuccessToStart();
                        } catch (Throwable ignore) {
                            //ignore
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when starting.", getServerName()), e);
            //do call back
            try {
                callbackOnFailureToStart();
            } catch (Throwable ignore) {
                //ignore
            }
            throw new CannotStartMachineException(e);
        }
        return this;
    }

    @Override
    public void callbackOnSuccessToStart() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when success to start.", getServerName()));
        }
    }

    /**
     * Do process run.
     *
     * @throws MachineException
     */
    protected void processRun() throws MachineRunException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s is running.", getServerName()));
        }
    }

    @Override
    public Machine run() throws MachineException {
        if (isRunning()) {
            processRun();
        }
        return this;
    }

    @Override
    public void callbackOnFailureToStart() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when failure to start.", getServerName()));
        }
    }

    @Override
    public Machine stop() throws CannotStopMachineException {
        long startTime = System.currentTimeMillis();
        try {
            if (running) {
                synchronized (this) {
                    if (running) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s is stopping.", getServerName()));
                        }

                        processStop();
                        destroy();
                        machineRepository.unregister(machineRepository.buildKey(getGroup(), getId()));
                        running = false;

                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info(String.format("%s has stopped,consume %s milliseconds.", getServerName(),
                                    (System.currentTimeMillis() - startTime)));
                        }

                        //do call back
                        try {
                            callbackOnSuccessToStop();
                        } catch (Throwable ignore) {
                            //ignore
                        }
                    }
                }
            }
        } catch (Throwable e) {
            LOGGER.error(String.format("%s occurs some erros when stopping.", getServerName()), e);
            //do call back
            try {
                callbackOnFailureToStop();
            } catch (Throwable ignore) {
                //ignore
            }
            throw new CannotStopMachineException(e);
        }
        return this;
    }

    @Override
    public void callbackOnSuccessToStop() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when success to stop.", getServerName()));
        }
    }

    @Override
    public void callbackOnFailureToStop() throws MachineException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s callback when failure to stop.", getServerName()));
        }
    }

    @Override
    public Machine restart() throws CannotStartMachineException, CannotStopMachineException {
        synchronized (this) {
            stop();
            start();
        }
        return this;
    }

    @Override
    public boolean isRunning() {
        synchronized (this) {
            return running;
        }
    }

    /**
     * Get service name
     *
     * @return
     */
    protected String getServerName() {
        return String.format("Machine[id=%s,group=%s,instanceId=%s]", getId(), getGroup(), getInstanceId());
    }
}
