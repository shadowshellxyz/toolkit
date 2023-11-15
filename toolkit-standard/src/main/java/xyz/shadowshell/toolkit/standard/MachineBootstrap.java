package xyz.shadowshell.toolkit.standard;

import xyz.shadowshell.toolkit.standard.machine.Machine;
import xyz.shadowshell.toolkit.standard.machine.MachineRepository;
import xyz.shadowshell.toolkit.standard.machine.abstracts.AbstractMachine;
import xyz.shadowshell.toolkit.standard.machine.exception.CannotStartMachineException;
import xyz.shadowshell.toolkit.standard.machine.exception.CannotStopMachineException;
import xyz.shadowshell.toolkit.standard.machine.impl.MachineRepositoryFactory;

/**
 * MachineBootstrap
 *
 * @author shadow
 * @Date 2016/12/6
 */
public class MachineBootstrap extends AbstractMachine implements Machine {

    private MachineRepository machineRepository = MachineRepositoryFactory.getDefaultRepository();

    /**
     * 启动入口
     *
     * @param args
     * @throws CannotStartMachineException
     */
    public static void main(String[] args) throws CannotStartMachineException {
        new MachineBootstrap().start();
        synchronized (MachineBootstrap.class) {
            while (true) {
                try {
                    MachineBootstrap.class.wait();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    @Override
    public void processStart() throws CannotStartMachineException {
        for (Machine machine : machineRepository.lookupAll()) {
            if (machine == null) {
                continue;
            }
            if (getGroup().equalsIgnoreCase(machine.getGroup())
                    && getId().equalsIgnoreCase(machine.getId())) {
                continue;
            }

            machine.start();
        }
    }

    @Override
    public void processStop() throws CannotStopMachineException {
        for (Machine machine : machineRepository.lookupAll()) {
            if (machine == null) {
                continue;
            }
            if (getGroup().equalsIgnoreCase(machine.getGroup())
                    && getId().equalsIgnoreCase(machine.getId())) {
                continue;
            }

            machine.stop();
        }
    }

    @Override
    public String getId() {
        return getClass().getSimpleName();
    }

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getGroup() {
        return getClass().getInterfaces()[0].getSimpleName();
    }
}
