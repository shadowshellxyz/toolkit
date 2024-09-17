package xyz.shadowshell.configuration.client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import xyz.shadowshell.configuration.client.impl.CommonConfigurator;
import xyz.shadowshell.configuration.client.impl.ConfiguratorResources;

/**
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
public class RemotableConfiguratorServer {

    public static void main(String[] args) throws Exception {
        String name = "rmi://127.0.0.1:1099/Configurator";
        ConfiguratorResources resources = new ConfiguratorResources("Configurator", "Configurator");
        resources.setRmiCreateRegistryStrategy("always");
        resources.setRmiRegistryPort(1099);
        resources.setRmiRegistryHost(name);
        LocateRegistry.createRegistry(resources.getRmiRegistryPort());
        Naming.rebind(name, new CommonConfigurator(resources));

        synchronized (RemotableConfiguratorServer.class) {
            RemotableConfiguratorServer.class.wait();
        }
    }
}
