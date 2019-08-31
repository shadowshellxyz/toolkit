package org.walkerljl.toolkit.standard.util;

import org.walkerljl.toolkit.standard.exception.AppException;
import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * AssertUtils
 *
 * @author xingxun
 * @Date 2017/10/22
 */
public class AssertUtils {

    /**
     * 断言表达式为真
     *
     * @param expression 表达式
     * @param message 错误消息
     */
    public static void assertTrue(boolean expression, String message) {
        AssertUtil.assertTrue(expression, message);
    }

    /**
     * 断言表达式为真
     *
     * @param expression 表达式
     * @param errorCode 错误码
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode) {
        AssertUtil.assertTrue(expression, errorCode);
    }

    /**
     * 断言表达式为真
     *
     * @param expression 表达式
     * @param errorCode 错误码
     * @param message 异常消息
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode, String message) {
        AssertUtil.assertTrue(expression, errorCode, message);
    }
}
