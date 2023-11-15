package xyz.shadowshell.toolkit.standard.exception;

import xyz.shadowshell.toolkit.standard.exception.code.ErrorCode;

/**
 * SwallowedAppException
 *
 * @author shadow
 */
public class SwallowedAppException extends AppException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public SwallowedAppException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 消息
     */
    public SwallowedAppException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public SwallowedAppException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public SwallowedAppException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 消息
     */
    public SwallowedAppException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e    异常对象
     */
    public SwallowedAppException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e       异常对象
     */
    public SwallowedAppException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     * @param e       异常对象
     */
    public SwallowedAppException(ErrorCode code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
}