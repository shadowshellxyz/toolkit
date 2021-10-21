package net.shadowdefender.toolkit.standard.machine.impl;

import net.shadowdefender.toolkit.standard.repository.abstracts.AbstractObjectRepository;
import net.shadowdefender.toolkit.standard.machine.Machine;
import net.shadowdefender.toolkit.standard.machine.MachineRepository;

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