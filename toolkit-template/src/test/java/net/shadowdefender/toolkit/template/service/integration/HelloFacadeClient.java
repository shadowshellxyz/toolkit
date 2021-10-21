package net.shadowdefender.toolkit.template.service.integration;

/**
 *
 * @author shadow
 */
public interface HelloFacadeClient {

    String say(String content);

    String doSomething(String address, String something);
}