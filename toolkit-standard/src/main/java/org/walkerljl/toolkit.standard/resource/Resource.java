package org.walkerljl.toolkit.standard.resource;

import org.walkerljl.toolkit.standard.Identifer;
import org.walkerljl.toolkit.standard.resource.exception.CannotDestroyResourceException;
import org.walkerljl.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * 描述一种可初始化和销毁的资源
 *
 * @author: xingxun
 */
public interface Resource extends Identifer {

    /**
     * 返回实例ID
     *
     * @return 实例ID
     */
    String getInstanceId();

    /**
     * 初始化资源
     *
     * @return
     * @throws CannotInitResourceException
     */
    Resource init() throws CannotInitResourceException;

    /**
     * 销毁资源
     *
     * @return
     * @throws CannotDestroyResourceException
     */
    Resource destroy() throws CannotDestroyResourceException;
}
