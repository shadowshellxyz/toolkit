package net.shadowdefender.toolkit.standard.exception;

import org.junit.Assert;
import org.junit.Test;
import net.shadowdefender.toolkit.standard.BaseUnitTest;

/**
 * UncheckedExceptionTest
 *
 * @author shadow
 */
public class UncheckedExceptionTest extends BaseUnitTest {

    @Test
    public void test() {

        String expectedErrorMsg = "errorMsg";
        UncheckedException actual = new UncheckedException();
        Assert.assertNull(actual.getMessage());
        Assert.assertNull(actual.getCause());

        actual = new UncheckedException(expectedErrorMsg);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);

        Throwable expectedThrowable = new RuntimeException();
        actual = new UncheckedException(expectedThrowable);
        Assert.assertEquals(actual.getCause(), expectedThrowable);

        actual = new UncheckedException(expectedErrorMsg, expectedThrowable);
        Assert.assertEquals(actual.getMessage(), expectedErrorMsg);
        Assert.assertEquals(actual.getCause(), expectedThrowable);
    }
}