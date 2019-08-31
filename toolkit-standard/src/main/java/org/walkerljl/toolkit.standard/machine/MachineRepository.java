package org.walkerljl.toolkit.standard.machine;

import org.walkerljl.toolkit.standard.repository.ObjectRepository;

/**
 * 机器仓库
 *
 * @author xingxun
 * @Date 2016/12/12
 */
public interface MachineRepository extends ObjectRepository<String, Machine> {

    /**
     * 构建身份Key
     *
     * @param groupId 分组ID
     * @param machineId 机器ID
     * @return
     */
    String buildKey(String groupId, String machineId);
}
