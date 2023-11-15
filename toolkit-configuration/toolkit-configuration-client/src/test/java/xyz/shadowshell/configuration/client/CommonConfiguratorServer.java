package xyz.shadowshell.configuration.client;

import java.io.InputStream;
import java.util.Properties;

import xyz.shadowshell.toolkit.lang.PropertiesUtils;
import xyz.shadowshell.configuration.client.impl.CommonConfigurator;
import xyz.shadowshell.configuration.client.impl.ConfiguratorResources;
import xyz.shadowshell.configuration.client.impl.RemotableConfigurator;
import xyz.shadowshell.configuration.client.impl.RemoteConfigurator;

/**
 * @author: ShadowDefender
 */
public class CommonConfiguratorServer {

    public static void main(String[] args) throws InterruptedException {
        InputStream ins = null;

        try {
            ins = ConfiguratorFactory.class.getResourceAsStream("conf.properties");
        } catch (Exception e) {
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
        RemoteConfigurator remoteConfigurator = new RemoteConfigurator(remotableConfigurator);
        remoteConfigurator.init();

        synchronized (CommonConfiguratorServer.class) {
            CommonConfiguratorServer.class.wait();
        }
    }
}
