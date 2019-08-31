package org.walkerljl.toolkit.standard.resource;

import org.walkerljl.toolkit.standard.repository.ObjectRepository;

/**
 * ResourceRepository
 *
 * @author xingxun
 */
public interface ResourceRepository extends ObjectRepository<String, Resource> {

    /**
     * 构建身份Key
     *
     * @param groupId 分组ID
     * @param resourceId 资源ID
     * @return
     */
    String buildKey(String groupId, String resourceId);
}