package org.walkerljl.toolkit.standard.machine.impl;

import org.walkerljl.toolkit.standard.machine.MachineRepository;

/**
 * MachineRepositoryFactory
 *
 * @author xingxun
 */
public class MachineRepositoryFactory {

    public static MachineRepository getDefaultRepository() {
        return MachineRepositoryFactory.FactoryHolder.defaultRepository;
    }

    private static class FactoryHolder {
        private static MachineRepository defaultRepository = new DefaultMachineRepository();
    }
}