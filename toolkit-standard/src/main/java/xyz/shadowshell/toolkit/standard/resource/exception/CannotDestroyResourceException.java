package xyz.shadowshell.toolkit.standard.resource.exception;

import xyz.shadowshell.toolkit.standard.exception.code.ErrorCode;

/**
 * Exception of resource can not destroy
 *
 * @author shadow
 */
public class CannotDestroyResourceException extends ResourceException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public CannotDestroyResourceException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public CannotDestroyResourceException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public CannotDestroyResourceException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public CannotDestroyResourceException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     */
    public CannotDestroyResourceException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e    异常对象
     */
    public CannotDestroyResourceException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e       异常对象
     */
    public CannotDestroyResourceException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     * @param e       异常对象
     */
    public CannotDestroyResourceException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}