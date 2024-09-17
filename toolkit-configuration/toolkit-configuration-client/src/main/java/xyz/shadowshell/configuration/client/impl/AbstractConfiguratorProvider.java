package xyz.shadowshell.configuration.client.impl;

import xyz.shadowshell.toolkit.logging.Logger;
import xyz.shadowshell.toolkit.logging.LoggerFactory;
import xyz.shadowshell.toolkit.standard.resource.abstracts.AbstractResource;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotDestroyResourceException;
import xyz.shadowshell.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * 抽象的配置器提供者
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
public abstract class AbstractConfiguratorProvider extends AbstractResource implements ConfiguratorProvider {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * 默认构造函数
     */
    public AbstractConfiguratorProvider() {
    }

    /**
     * 包装Key,统一转换成小写的Key且trim前后空格
     *
     * @param key
     * @return
     */
    protected String wrapKey(String key) {
        return key == null ? null : key.trim().toLowerCase();
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void del(String key) {

    }

    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String getGroup() {
        return "orgwalkerljl-configurator-provider";
    }

    @Override
    public void processInit() throws CannotInitResourceException {

    }

    @Override
    public void processDestroy() throws CannotDestroyResourceException {

    }
}