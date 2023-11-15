package xyz.shadowshell.configuration.client;

import java.io.InputStream;
import java.util.Properties;

import net.shadowdefender.toolkit.lang.PropertiesUtils;
import xyz.shadowshell.configuration.client.impl.RemotableConfigurator;
import xyz.shadowshell.configuration.client.impl.RemoteConfigurator;
import xyz.shadowshell.configuration.client.impl.StdConfigurator;
import xyz.shadowshell.configuration.client.impl.CommonConfigurator;
import xyz.shadowshell.configuration.client.impl.ConfiguratorProvider;
import xyz.shadowshell.configuration.client.impl.ConfiguratorProviderRepository;
import xyz.shadowshell.configuration.client.impl.ConfiguratorResources;
import net.shadowdefender.toolkit.logging.Logger;
import net.shadowdefender.toolkit.logging.LoggerFactory;

/**
 * @author shadow
 */
public class ConfiguratorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguratorFactory.class);

    private static Configurator stdConfigurator;
    private static Configurator remoteConfigurator;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (stdConfigurator != null) {
                    stdConfigurator.destroy();
                }

                if (remoteConfigurator != null) {
                    remoteConfigurator.destroy();
                }
            }
        });
    }

    /**
     * Bind
     *
     * @param provider
     */
    public static void bind(ConfiguratorProvider provider) {
        ConfiguratorProviderRepository.getInstance().bind(provider);
    }

    public static void unbind(ConfiguratorProvider provider) {
        ConfiguratorProviderRepository.getInstance().unbind(provider.getId());
    }

    public static Configurator getStdConfigurator() {
        if (stdConfigurator == null) {
            synchronized (ConfiguratorFactory.class) {
                if (stdConfigurator == null) {
                    ConfiguratorResources resources = new ConfiguratorResources("1", "StdConfigurator");
                    RemotableConfigurator remotableConfigurator = new CommonConfigurator(resources);
                    stdConfigurator = new StdConfigurator(remotableConfigurator);
                    stdConfigurator.init();
                }
            }
        }
        return stdConfigurator;
    }

    public static Configurator getRemoteConfigurator() {
        if (remoteConfigurator == null) {
            synchronized (ConfiguratorFactory.class) {
                if (remoteConfigurator == null) {
                    InputStream ins = null;

                    try {
                        ins = ConfiguratorFactory.class.getResourceAsStream("conf.properties");
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage(), e);
                        throw new ConfiguratorException(e);
                    }
                    Properties properties = PropertiesUtils.createFromInputStream(ins);
                    int port = Integer.parseInt(properties.getProperty("rmiRegistryPort"));
                    String host = properties.getProperty("rmiRegistryHost");

                    ConfiguratorResources resources = new ConfiguratorResources("2", "RemoteConfigurator");
                    resources.setRmiRegistryPort(port);
                    resources.setRmiRegistryHost(host);
                    resources.setRmiCreateRegistryStrategy("always");
                    RemotableConfigurator remotableConfigurator = new CommonConfigurator(resources);
                    remoteConfigurator = new RemoteConfigurator(remotableConfigurator);
                    remoteConfigurator.init();
                }
            }
        }
        return remoteConfigurator;
    }
}