package xyz.shadowshell.toolkit.standard.util;

import xyz.shadowshell.toolkit.standard.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import xyz.shadowshell.toolkit.standard.enums.IEnum;
import xyz.shadowshell.toolkit.standard.exception.AppException;
import xyz.shadowshell.toolkit.standard.exception.code.ErrorCode;

/**
 * AssertUtilTest
 *
 * @author shadow
 */
public class AssertUtilTest extends BaseUnitTest {

    @Test
    public void test() {
        ErrorCode errorCode = new ErrorCode() {
            @Override
            public String getCode() {
                return "errorCode";
            }

            @Override
            public String getDescription() {
                return "errorDescription";
            }

            @Override
            public IEnum getEnumObject(String code) {
                return null;
            }
        };
        String message = "message";

        AssertUtil.assertTrue(true, errorCode, message);
        AssertUtil.assertTrue(true, errorCode);
        AssertUtil.assertTrue(true, message);

        boolean result = false;
        try {
            AssertUtil.assertTrue(false, errorCode, message);
        } catch (AppException e) {
            if (message.equals(e.getMessage())
                    && errorCode.getCode().equals(e.getCode().getCode())
                    && errorCode.getDescription().equals(e.getCode().getDescription())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
        result = false;

        try {
            AssertUtil.assertTrue(false, errorCode);
        } catch (AppException e) {
            if (errorCode.getDescription().equals(e.getMessage())
                    && errorCode.getCode().equals(e.getCode().getCode())
                    && errorCode.getDescription().equals(e.getCode().getDescription())) {
                result = true;
            }
        }
        Assert.assertTrue(result);
        result = false;

        try {
            AssertUtil.assertTrue(false, message);
        } catch (AppException e) {
            if (message.equals(e.getMessage())
                    && e.getCode() == null) {
                result = true;
            }
        }
        Assert.assertTrue(result);
    }
}
