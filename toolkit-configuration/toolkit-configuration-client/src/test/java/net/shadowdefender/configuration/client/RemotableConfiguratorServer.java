package net.shadowdefender.configuration.client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import net.shadowdefender.configuration.client.impl.CommonConfigurator;
import net.shadowdefender.configuration.client.impl.ConfiguratorResources;

/**
 * @author: ShadowDefender
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
