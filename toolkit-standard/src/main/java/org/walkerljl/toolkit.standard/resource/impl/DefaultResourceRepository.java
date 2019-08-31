package org.walkerljl.toolkit.standard.resource.impl;

import org.walkerljl.toolkit.standard.repository.abstracts.AbstractObjectRepository;
import org.walkerljl.toolkit.standard.resource.Resource;
import org.walkerljl.toolkit.standard.resource.ResourceRepository;

/**
 * 默认的资源仓库
 *
 * @author xingxun
 * @Date 2016/12/12
 */
public class DefaultResourceRepository extends AbstractObjectRepository<String, Resource> implements ResourceRepository {

    @Override
    public String buildKey(String groupId, String resourceId) {
        return String.format("%s:%s", groupId, resourceId);
    }
}
