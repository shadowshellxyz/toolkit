package xyz.shadowshell.configuration.client.impl.readonly;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.shadowdefender.toolkit.lang.io.StreamUtils;
import xyz.shadowshell.configuration.client.impl.AbstractConfiguratorProvider;
import xyz.shadowshell.configuration.client.impl.ConfiguratorProvider;
import net.shadowdefender.toolkit.standard.resource.Resource;
import net.shadowdefender.toolkit.standard.resource.exception.CannotDestroyResourceException;
import net.shadowdefender.toolkit.standard.resource.exception.CannotInitResourceException;

/**
 * 基于JAVA Properties文件的只读配置器<br>
 * <li> 只在创建配置器的时候获取Properties的配置数据
 *
 * @author shadow
 */
public class PropertiesConfiguratorProvider extends AbstractConfiguratorProvider implements ConfiguratorProvider {

    private Map<String, String> propertiesMap = new HashMap<String, String>();
    private String[] propertiesFiles;
    private volatile boolean inited = false;

    /**
     * 默认构造函数
     */
    public PropertiesConfiguratorProvider() {

    }

    /**
     * 构造函数
     *
     * @param propertiesFiles
     */
    public PropertiesConfiguratorProvider(String... propertiesFiles) {
        this.propertiesFiles = propertiesFiles;
    }

    @Override
    public Resource init() throws CannotInitResourceException {
        if (!inited) {
            synchronized (this) {
                if (!inited) {
                    for (String propertiesFile : propertiesFiles) {
                        load(propertiesFile);
                    }
                }
            }
        }

        return this;
    }

    @Override
    public Resource destroy() throws CannotDestroyResourceException {
        propertiesMap = null;

        return this;
    }

    @Override
    public Object get(String key) {
        return propertiesMap.get(key);
    }

    @Override
    public void set(String key, String value) {
        propertiesMap.put(key, String.valueOf(value));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Add or update one config information,key:%s.value:%s.", key, value));
        }
    }

    /**
     * 加载配置
     *
     * @param propertiesFile
     * @throws IOException
     */
    private void load(String propertiesFile) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile);
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(in);
        } catch (Exception e) {
            String errMsg = String.format("Can not load property file:%s.", propertiesFile);
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(errMsg, e);
        } finally {
            StreamUtils.close(in);
        }
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            if (value == null || "".equals(value)) {
                continue;
            }
            propertiesMap.put(wrapKey(key), value);
        }
    }

    @Override
    public String getId() {
        return "PropertiesConfiguratorProvider";
    }
}