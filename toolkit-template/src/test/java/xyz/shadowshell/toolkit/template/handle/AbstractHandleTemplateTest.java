package xyz.shadowshell.toolkit.template.handle;

import org.testng.Assert;
import org.testng.annotations.Test;
import xyz.shadowshell.toolkit.standard.exception.AppException;
import xyz.shadowshell.toolkit.template.BaseUnitTest;
import xyz.shadowshell.toolkit.template.handle.service.ServiceErrorCode;
import xyz.shadowshell.toolkit.template.log.model.InvocationInfo;
import xyz.shadowshell.toolkit.template.log.Logger;

/**
 * @author shadow
 */
public class AbstractHandleTemplateTest extends BaseUnitTest {

    private AbstractHandleTemplate abstractHandleTemplate = new DefaultHandleTemplate();

    @Test
    public void testForLogger() {
        abstractHandleTemplate.getDigestLogger();
        abstractHandleTemplate.getDetailLogger();
        abstractHandleTemplate.getErrorLogger();
    }

    @Test
    public void canRethrowException() {
        boolean actual = abstractHandleTemplate.canRethrowException();
        Assert.assertTrue(actual);
    }

    @Test
    public void doLog() {
        abstractHandleTemplate.doLog(null);

        InvocationInfo<String, Object> invocationInfo = new InvocationInfo<>(getClass(),
                "test", "testParam");
        abstractHandleTemplate.doLog(invocationInfo);

        invocationInfo.setThrowable(new AppException());
        abstractHandleTemplate.doLog(invocationInfo);

        invocationInfo.setThrowable(new AppException(ServiceErrorCode.UNKNOWN));
        abstractHandleTemplate.doLog(invocationInfo);

        invocationInfo.setThrowable(new RuntimeException("测试异常"));
        abstractHandleTemplate.doLog(invocationInfo);
    }
}

class DefaultHandleTemplate extends AbstractHandleTemplate {

    @Override
    protected Logger getDigestLogger() {
        return null;
    }

    @Override
    protected Logger getDetailLogger() {
        return null;
    }

    @Override
    protected Logger getErrorLogger() {
        return null;
    }
}

