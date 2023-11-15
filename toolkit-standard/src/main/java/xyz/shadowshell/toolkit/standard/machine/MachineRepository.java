package xyz.shadowshell.toolkit.standard.machine;

import xyz.shadowshell.toolkit.standard.repository.ObjectRepository;

/**
 * 机器仓库
 *
 * @author shadow
 * @Date 2016/12/12
 */
public interface MachineRepository extends ObjectRepository<String, Machine> {

    /**
     * 构建身份Key
     *
     * @param groupId   分组ID
     * @param machineId 机器ID
     * @return
     */
    String buildKey(String groupId, String machineId);
}
