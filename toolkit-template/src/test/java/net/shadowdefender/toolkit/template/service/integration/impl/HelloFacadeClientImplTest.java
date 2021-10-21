package net.shadowdefender.toolkit.template.service.integration.impl;

import net.shadowdefender.toolkit.template.service.facade.HelloFacade;
import net.shadowdefender.toolkit.template.service.facade.impl.HelloFacadeImpl;
import org.junit.Test;
import net.shadowdefender.toolkit.template.BaseUnitTest;

/**
 * HelloFacadeClientImplTest
 *
 * @author shadow
 */
public class HelloFacadeClientImplTest extends BaseUnitTest {
    
    @Test
    public void testForNormal() {
        HelloFacade helloFacade = new HelloFacadeImpl();
        HelloFacadeClientImpl helloFacadeClient = new HelloFacadeClientImpl();
        helloFacadeClient.setHelloFacade(helloFacade);
        helloFacadeClient.say("world");
        helloFacadeClient.doSomething("127.0.0.1", "haha");
    }

    @Test
    public void testForException() {
        HelloFacade helloFacade = new HelloFacadeImpl();
        HelloFacadeClientImpl helloFacadeClient = new HelloFacadeClientImpl();
        helloFacadeClient.setHelloFacade(helloFacade);

        helloFacadeClient.say(null);
        helloFacadeClient.doSomething(null, null);
    }
}