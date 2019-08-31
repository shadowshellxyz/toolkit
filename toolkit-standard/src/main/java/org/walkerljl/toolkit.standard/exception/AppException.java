package org.walkerljl.toolkit.standard.exception;

import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * 应用异常
 *
 * @author xingxun
 */
public class AppException extends UncheckedException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 异常码
     */
    protected ErrorCode code;

    /**
     * 默认构造函数
     */
    public AppException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public AppException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public AppException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public AppException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 消息
     */
    public AppException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e 异常对象
     */
    public AppException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e 异常对象
     */
    public AppException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param message 异常消息
     * @param e 异常对象
     */
    public AppException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    /**
     * 获取异常码
     *
     * @return
     */
    public ErrorCode getCode() {
        return code;
    }
}