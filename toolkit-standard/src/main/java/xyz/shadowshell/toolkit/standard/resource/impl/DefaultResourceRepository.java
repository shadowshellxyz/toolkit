package xyz.shadowshell.toolkit.standard.resource.impl;

import xyz.shadowshell.toolkit.standard.repository.abstracts.AbstractObjectRepository;
import xyz.shadowshell.toolkit.standard.resource.ResourceRepository;
import xyz.shadowshell.toolkit.standard.resource.Resource;

/**
 * 默认的资源仓库
 *
 * @author shadow
 * @Date 2016/12/12
 */
public class DefaultResourceRepository extends AbstractObjectRepository<String, Resource> implements ResourceRepository {

    @Override
    public String buildKey(String groupId, String resourceId) {
        return String.format("%s:%s", groupId, resourceId);
    }
}
