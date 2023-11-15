package xyz.shadowshell.toolkit.standard.machine.impl;

import xyz.shadowshell.toolkit.standard.repository.abstracts.AbstractObjectRepository;
import xyz.shadowshell.toolkit.standard.machine.Machine;
import xyz.shadowshell.toolkit.standard.machine.MachineRepository;

/**
 * DefaultMachineRepository
 *
 * @author shadow
 */
public class DefaultMachineRepository extends AbstractObjectRepository<String, Machine> implements MachineRepository {

    @Override
    public String buildKey(String groupId, String machineId) {
        return String.format("%s:%s", groupId, machineId);
    }
}