package xyz.shadowshell.configuration.client.jmx;

import xyz.shadowshell.toolkit.standard.resource.Resource;

/**
 * 配置器MBean
 *
 * @author: ShadowDefender
 */
public interface ConfiguratorMXBean extends Resource {

    /**
     * Get property
     *
     * @param key key
     * @return String object
     */
    String getAsString(String key);

    /**
     * Set property<br>
     * Replace the old value if the value exists
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * Remove property by the key
     *
     * @param key
     */
    void del(String key);
}
