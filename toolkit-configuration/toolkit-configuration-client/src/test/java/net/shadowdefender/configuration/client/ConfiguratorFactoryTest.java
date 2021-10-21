package net.shadowdefender.configuration.client;

import net.shadowdefender.configuration.client.impl.readonly.PropertiesConfiguratorProvider;

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
