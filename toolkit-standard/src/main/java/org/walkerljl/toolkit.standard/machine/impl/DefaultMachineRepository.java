package org.walkerljl.toolkit.standard.machine.impl;

import org.walkerljl.toolkit.standard.machine.Machine;
import org.walkerljl.toolkit.standard.machine.MachineRepository;
import org.walkerljl.toolkit.standard.repository.abstracts.AbstractObjectRepository;

/**
 * DefaultMachineRepository
 *
 * @author xingxun
 */
public class DefaultMachineRepository extends AbstractObjectRepository<String, Machine> implements MachineRepository {

    @Override
    public String buildKey(String groupId, String machineId) {
        return String.format("%s:%s", groupId, machineId);
    }
}