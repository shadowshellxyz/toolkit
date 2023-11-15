package xyz.shadowshell.toolkit.standard.machine.impl;

import xyz.shadowshell.toolkit.standard.machine.MachineRepository;

/**
 * MachineRepositoryFactory
 *
 * @author shadow
 */
public class MachineRepositoryFactory {

    public static MachineRepository getDefaultRepository() {
        return MachineRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static MachineRepository defaultRepository = new DefaultMachineRepository();
    }
}