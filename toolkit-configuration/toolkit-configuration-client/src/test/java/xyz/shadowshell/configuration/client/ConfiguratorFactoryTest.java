package xyz.shadowshell.configuration.client;

import xyz.shadowshell.configuration.client.impl.readonly.PropertiesConfiguratorProvider;

/**
 * @author: ShadowDefender
 */
public class ConfiguratorFactoryTest {

    public static void main(String[] args) {

        ConfiguratorFactory.bind(new PropertiesConfiguratorProvider("org/walkerljl/commons/conf.properties"));

        Configurator stdConfigurator = ConfiguratorFactory.getStdConfigurator();
        System.out.println(stdConfigurator.getAsString("userid"));
    }
}
