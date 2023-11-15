package xyz.shadowshell.toolkit.template.handle.sal;

import org.junit.Test;
import xyz.shadowshell.toolkit.template.BaseUnitTest;

/**
 * @author shadow
 */
public class SalHandleTemplateTest extends BaseUnitTest {

    @Test
    public void testForLogger() {

        SalHandleTemplate.getInstance().getDigestLogger();
        SalHandleTemplate.getInstance().getDetailLogger();
        SalHandleTemplate.getInstance().getErrorLogger();
    }
}