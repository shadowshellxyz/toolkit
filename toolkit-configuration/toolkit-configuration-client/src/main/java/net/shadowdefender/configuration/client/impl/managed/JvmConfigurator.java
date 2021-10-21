package net.shadowdefender.configuration.client.impl.managed;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.shadowdefender.configuration.client.impl.AbstractConfiguratorProvider;
import net.shadowdefender.configuration.client.impl.ConfiguratorProvider;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;
import net.shadowdefender.toolkit.standard.resource.Resource;
import net.shadowdefender.toolkit.standard.resource.exception.CannotDestroyResourceException;

/**
 * 基于JVM可管理的配置器
 *
 * @author shadow
 */
public class JvmConfigurator extends AbstractConfiguratorProvider implements ConfiguratorProvider {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 配置信息容器
     */
    private Map<String, Object> propertiesMap = null;

    /**
     * 构造函数
     */
    public JvmConfigurator() {
        propertiesMap = new ConcurrentHashMap<String, Object>();
    }

    @Override
    public Object get(String key) {
        if (!validateKey(key)) {
            return null;
        }
        return propertiesMap.get(key);
    }

    @Override
    public void set(String key, String value) {
        if (validateKey(key)) {
            propertiesMap.put(wrapKey(key), value);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("%s覆盖配置, Key:%s, Value:%s", getClass(), key, value));
            }
        } else {
            LOGGER.warn(String.format("%s无效的配置Key:%s", getClass(), key));
        }
    }

    @Override
    public void del(String key) {
        if (validateKey(key)) {
            propertiesMap.remove(wrapKey(key));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("%s移除配置, Key:%s", getClass(), key));
            }
        } else {
            LOGGER.warn(String.format("%s无效的配置Key:%s", getClass(), key));
        }
    }

    @Override
    public Resource destroy() throws CannotDestroyResourceException {
        propertiesMap = new ConcurrentHashMap<String, Object>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("%s清除缓存", getClass()));
        }

        return this;
    }

    /**
     * 校验Key是否合法
     *
     * @param key
     * @return
     */
    private boolean validateKey(String key) {
        return key != null && !"".equals(key);
    }

    @Override
    public String getId() {
        return "JvmConfigurator";
    }

    @Override
    public String getName() {
        return getId();
    }
}