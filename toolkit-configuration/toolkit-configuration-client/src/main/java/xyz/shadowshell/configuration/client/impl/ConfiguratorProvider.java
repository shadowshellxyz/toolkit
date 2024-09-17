package xyz.shadowshell.configuration.client.impl;

import xyz.shadowshell.toolkit.standard.resource.Resource;

/**
 * Configurator provider
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
public interface ConfiguratorProvider extends Resource {

    /**
     * Get
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * Set
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * Del
     *
     * @param key
     */
    void del(String key);
}