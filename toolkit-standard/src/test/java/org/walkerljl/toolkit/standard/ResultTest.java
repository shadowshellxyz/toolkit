package org.walkerljl.toolkit.standard;

import org.junit.Assert;
import org.junit.Test;

/**
 * ResultTest
 *
 * @author xingxun
 */
public class ResultTest {

    /**
     * 默认成功编码
     */
    private static final String DEFAULT_SUCCESS_CODE    = "200";
    /**
     * 默认失败编码
     */
    private static final String DEFAULT_FAILURE_CODE    = "500";
    /**
     * 默认成功消息体
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    /**
     * 默认失败消息体
     */
    private static final String DEFAULT_FAILURE_MESSAGE = "FAILURE";

    private void assertDefaultFailure(Result<Integer> expected) {

        Assert.assertFalse(expected.isSuccess());
        Assert.assertEquals(expected.getMessage(), DEFAULT_FAILURE_MESSAGE);
        Assert.assertEquals(expected.getCode(), DEFAULT_FAILURE_CODE);
        Assert.assertEquals(expected.getData(), null);
    }

    private void assertDefaultSuccess(Result<Integer> expected) {

        Assert.assertTrue(expected.isSuccess());
        Assert.assertEquals(expected.getMessage(), DEFAULT_SUCCESS_MESSAGE);
        Assert.assertEquals(expected.getCode(), DEFAULT_SUCCESS_CODE);
        Assert.assertEquals(expected.getData(), null);
    }

    private void doAssert(Result<Integer> expected, boolean actualResult, String actualCode, String actualBody, Integer actualData) {

        if (actualResult) {
            Assert.assertTrue(expected.isSuccess());
        } else {
            Assert.assertFalse(expected.isSuccess());
        }
        Assert.assertEquals(expected.getMessage(), actualBody);
        Assert.assertEquals(expected.getCode(), actualCode);
        Assert.assertEquals(expected.getData(), actualData);
    }

    @Test
    public void create() {
        Result<Integer> expected = Result.create(false);
        assertDefaultFailure(expected);

        expected = Result.create(true);
        assertDefaultSuccess(expected);

        boolean actualResult = false;
        String actualCode = "1";
        String actualBody = "2";
        Integer actualData = 3;
        expected = Result.create(actualResult, actualCode, actualBody, actualData);
        doAssert(expected, actualResult, actualCode, actualBody, actualData);

        actualResult = true;
        expected = Result.create(actualResult, actualCode, actualBody, actualData);
        doAssert(expected, actualResult, actualCode, actualBody, actualData);
    }

    @Test
    public void success() {
        boolean actualResult = true;
        String actualCode = "1";
        String actualMessage = "2";
        Integer actualData = 3;
        Result<Integer> expected = null;

        expected = Result.success();
        doAssert(expected, actualResult, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);

        expected = Result.success(actualMessage);
        doAssert(expected, actualResult, DEFAULT_SUCCESS_CODE, actualMessage, null);

        expected = Result.success(actualData);
        doAssert(expected, actualResult, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, actualData);

        expected = Result.success(actualCode, actualMessage);
        doAssert(expected, actualResult, actualCode, actualMessage, null);

        expected = Result.success(actualMessage, actualData);
        doAssert(expected, actualResult, DEFAULT_SUCCESS_CODE, actualMessage, actualData);

        expected = Result.success(actualCode, actualMessage, actualData);
        doAssert(expected, actualResult, actualCode, actualMessage, actualData);
    }

    @Test
    public void failure() {

        boolean actualResult = false;
        String actualCode = "1";
        String actualMessage = "2";
        Integer actualData = 3;
        Result<Integer> expected = null;

        expected = Result.failure();
        doAssert(expected, actualResult, DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MESSAGE, null);

        expected = Result.failure(actualMessage);
        doAssert(expected, actualResult, DEFAULT_FAILURE_CODE, actualMessage, null);

        expected = Result.failure(actualData);
        doAssert(expected, actualResult, DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MESSAGE, actualData);

        expected = Result.failure(actualCode, actualMessage);
        doAssert(expected, actualResult, actualCode, actualMessage, null);

        expected = Result.failure(actualMessage, actualData);
        doAssert(expected, actualResult, DEFAULT_FAILURE_CODE, actualMessage, actualData);

        expected = Result.failure(actualCode, actualMessage, actualData);
        doAssert(expected, actualResult, actualCode, actualMessage, actualData);
    }
}