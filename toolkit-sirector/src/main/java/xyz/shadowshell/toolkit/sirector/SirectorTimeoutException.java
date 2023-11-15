package xyz.shadowshell.toolkit.sirector;

import xyz.shadowshell.toolkit.standard.exception.code.ErrorCode;

/**
 * Sirector超时异常
 *
 * @author shadow
 */
public class SirectorTimeoutException extends SirectorException {

    private static final long serialVersionUID = -5423490721470482068L;

    /**
     * 默认构造函数
     */
    public SirectorTimeoutException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public SirectorTimeoutException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public SirectorTimeoutException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public SirectorTimeoutException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     */
    public SirectorTimeoutException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e    异常对象
     */
    public SirectorTimeoutException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e       异常对象
     */
    public SirectorTimeoutException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     * @param e       异常对象
     */
    public SirectorTimeoutException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}