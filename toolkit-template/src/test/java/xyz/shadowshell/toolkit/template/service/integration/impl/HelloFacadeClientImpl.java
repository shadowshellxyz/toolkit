package xyz.shadowshell.toolkit.template.service.integration.impl;

import xyz.shadowshell.toolkit.template.service.facade.HelloFacade;
import xyz.shadowshell.toolkit.standard.Result;
import xyz.shadowshell.toolkit.template.handle.sal.SalAssertUtil;
import xyz.shadowshell.toolkit.template.handle.sal.SalHandleTemplate;
import xyz.shadowshell.toolkit.template.handle.sal.SalHandler;
import xyz.shadowshell.toolkit.template.log.model.InvocationInfo;
import xyz.shadowshell.toolkit.template.service.integration.HelloFacadeClient;

/**
 * HelloFacadeClientImpl
 *
 * @author shadow
 */
public class HelloFacadeClientImpl implements HelloFacadeClient {

    private HelloFacade helloFacade;

    @Override
    public String say(String content) {
        InvocationInfo<String, String> invocationInfo = new InvocationInfo<>(getClass(), "say", content);
        return SalHandleTemplate.getInstance().handle(invocationInfo, new SalHandler<String, String>() {
            @Override
            public boolean checkParams(String content) {
                SalAssertUtil.assertParam(content != null, "content");
                return true;
            }

            @Override
            public String handle(String content) {
                Result<String> remotingResult = helloFacade.say(content);

                invocationInfo.markResult(true,
                        remotingResult,
                        remotingResult.getData());

                return remotingResult.getData();
            }
        });
    }

    @Override
    public String doSomething(String address, String something) {
        InvocationInfo<Object[], String> invocationInfo = new InvocationInfo<>(getClass(), "say", new Object[]{address, something});
        return SalHandleTemplate.getInstance().handle(invocationInfo, new SalHandler<Object[], String>() {
            @Override
            public boolean checkParams(Object[] params) {
                SalAssertUtil.assertParam(params[0] != null, "address");
                SalAssertUtil.assertParam(params[1] != null, "something");
                return true;
            }

            @Override
            public String handle(Object[] params) {
                Result<String> remotingResult = helloFacade.doSomething(String.valueOf(params[0]),
                        String.valueOf(params[1]));

                invocationInfo.markResult(true,
                        remotingResult,
                        remotingResult.getData());

                return remotingResult.getData();
            }
        });
    }

    /**
     * Setter method for property <tt>helloFacade</tt>.
     *
     * @param helloFacade value to be assigned to property helloFacade
     */
    public void setHelloFacade(HelloFacade helloFacade) {
        this.helloFacade = helloFacade;
    }
}