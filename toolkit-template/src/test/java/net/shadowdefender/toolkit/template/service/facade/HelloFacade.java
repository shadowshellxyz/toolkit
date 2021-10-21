package net.shadowdefender.toolkit.template.service.facade;

import net.shadowdefender.toolkit.standard.Result;

/**
 *
 * @author shadow
 */
public interface HelloFacade {

    Result<String> say(String content);

    Result<String> doSomething(String address, String something);

}