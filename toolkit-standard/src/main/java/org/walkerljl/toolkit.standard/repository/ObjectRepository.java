package org.walkerljl.toolkit.standard.repository;

import java.util.Collection;

/**
 * 对象仓储
 *
 * @author xingxun
 *
 * @param <KEY>
 * @param <VALUE>
 */
public interface ObjectRepository<KEY, VALUE> {

    /**
     * 注册对象
     *
     * @param key 对象Key
     * @param value 对象
     */
    void register(KEY key, VALUE value);

    /**
     * 移除指定对象
     *
     * @param key 对象Key
     */
    void unregister(KEY key);

    /**
     * 查找对象
     *
     * @param key 对象Key
     * @return
     */
    VALUE lookup(KEY key);

    /**
     * 查找所有对象
     *
     * @return
     */
    Collection<? extends VALUE> lookupAll();
}