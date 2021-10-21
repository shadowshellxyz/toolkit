package net.shadowdefender.configuration.client.impl;

import net.shadowdefender.toolkit.standard.resource.Resource;

/**
 * Configurator provider
 *
 * @author: ShadowDefender
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