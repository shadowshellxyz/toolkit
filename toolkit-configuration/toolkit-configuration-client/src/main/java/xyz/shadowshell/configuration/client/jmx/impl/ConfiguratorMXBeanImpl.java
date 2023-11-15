package xyz.shadowshell.configuration.client.jmx.impl;

import xyz.shadowshell.configuration.client.impl.RemotableConfigurator;
import xyz.shadowshell.configuration.client.jmx.ConfiguratorMXBean;
import xyz.shadowshell.configuration.client.ConfiguratorException;
import net.shadowdefender.toolkit.standard.exception.AppException;
import net.shadowdefender.toolkit.standard.resource.Resource;

/**
 * ConfiguratorMXBeanImpl
 *
 * @author: ShadowDefender
 */
public class ConfiguratorMXBeanImpl implements ConfiguratorMXBean {

    private RemotableConfigurator target;

    public ConfiguratorMXBeanImpl(RemotableConfigurator target) {
        this.target = target;
    }

    @Override
    public Resource init() throws AppException {
        try {
            target.init();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }

        return this;
    }

    @Override
    public Resource destroy() {
        try {
            target.destroy();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }

        return this;
    }

    @Override
    public String getInstanceId() {
        try {
            return target.getInstanceId();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getId() {
        try {
            return target.getId();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getName() {
        try {
            return target.getName();
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getAsString(String key) {
        try {
            return target.getAsString(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public void set(String key, String value) {
        try {
            target.set(key, value);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }

    @Override
    public void del(String key) {
        try {
            target.del(key);
        } catch (Exception e) {
            throw new ConfiguratorException(e);
        }
    }
}
