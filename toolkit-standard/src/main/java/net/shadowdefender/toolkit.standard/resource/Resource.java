package net.shadowdefender.toolkit.standard.resource;

import net.shadowdefender.toolkit.standard.resource.exception.CannotDestroyResourceException;
import net.shadowdefender.toolkit.standard.resource.exception.CannotInitResourceException;
import net.shadowdefender.toolkit.standard.Identifer;

/**
 * 描述一种可初始化和销毁的资源
 *
 * @author: ShadowDefender
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
