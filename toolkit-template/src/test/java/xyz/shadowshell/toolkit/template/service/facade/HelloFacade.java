package xyz.shadowshell.toolkit.template.service.facade;

import xyz.shadowshell.toolkit.standard.Result;

/**
 * @author shadow
 */
public interface HelloFacade {

    Result<String> say(String content);

    Result<String> doSomething(String address, String something);

}