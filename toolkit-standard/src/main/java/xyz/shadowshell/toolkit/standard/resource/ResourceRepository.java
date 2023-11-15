package xyz.shadowshell.toolkit.standard.resource;

import xyz.shadowshell.toolkit.standard.repository.ObjectRepository;

/**
 * ResourceRepository
 *
 * @author shadow
 */
public interface ResourceRepository extends ObjectRepository<String, Resource> {

    /**
     * 构建身份Key
     *
     * @param groupId    分组ID
     * @param resourceId 资源ID
     * @return
     */
    String buildKey(String groupId, String resourceId);
}