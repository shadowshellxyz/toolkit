package net.shadowdefender.toolkit.template.handle.service;

import net.shadowdefender.toolkit.standard.exception.AppServiceException;
import net.shadowdefender.toolkit.standard.exception.code.ErrorCode;

/**
 * ServiceAssertUtil
 *
 * @author shadow
 */
public class ServiceAssertUtil {

    /**
     * 断言为真
     *
     * @param expression 表达式
     * @param message 消息
     */
    public static void assertTrue(boolean expression, String message) {
        if (!expression) {
            throw new AppServiceException(message);
        }
    }

    /**
     * 断言为真
     *
     * @param expression 表达式
     * @param errorCode 错误码
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode) {
        if (!expression) {
            throw new AppServiceException(errorCode);
        }
    }

    /**
     * 断言为真
     *
     * @param expression 表达式
     * @param errorCode 错误码
     * @param message 消息
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode, String message) {
        if (!expression) {
            throw new AppServiceException(errorCode, message);
        }
    }

    /**
     * 断言参数
     *
     * @param expression 表达式
     * @param paramName 参数名称
     */
    public static void assertParam(boolean expression, String paramName) {
        assertParam(expression, ServiceErrorCode.INVALID_PARAM, paramName);
    }

    /**
     * 断言参数
     *
     * @param expression 表达式
     * @param errorCode 错误码
     * @param paramName 参数名称
     */
    public static void assertParam(boolean expression, ErrorCode errorCode, String paramName) {
        if (!expression) {
            String message = String.format("%s:%s", errorCode.getDescription(), paramName);
            throw new AppServiceException(errorCode, message);
        }
    }

    /**
     * 断言参数长度超限制
     *
     * @param expression 表达式
     * @param paramName 参数名称
     * @param paramLengthLimit 参数长度限制
     */
    public static void assertParamLengthExceedLimit(boolean expression, String paramName, int paramLengthLimit) {
        assertParamLengthExceedLimit(expression, ServiceErrorCode.PARAM_LENGTH_EXCEED_LIMIT, paramName, paramLengthLimit);
    }

    /**
     * 断言参数长度超限制
     *
     * @param expression 表达式
     * @param errorCode 错误码
     * @param paramName 参数名称
     * @param paramLengthLimit 参数长度限制
     */
    public static void assertParamLengthExceedLimit(boolean expression, ErrorCode errorCode, String paramName, int paramLengthLimit) {
        if (!expression) {
            String message = String.format("%s:%s:%s", errorCode.getDescription(), paramName, paramLengthLimit);
            throw new AppServiceException(errorCode, message);
        }
    }
}