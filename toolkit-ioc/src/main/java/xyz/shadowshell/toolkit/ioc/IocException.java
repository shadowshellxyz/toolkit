package xyz.shadowshell.toolkit.ioc;

import xyz.shadowshell.toolkit.standard.exception.AppException;
import xyz.shadowshell.toolkit.standard.exception.code.ErrorCode;

/**
 * 应用异常
 *
 * @author shadowwalkerxyz@qq.com<wwww.shadowshell.xyz>
 */
public class IocException extends AppException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public IocException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public IocException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public IocException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public IocException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     */
    public IocException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e       异常对象
     */
    public IocException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     * @param e       异常对象
     */
    public IocException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }
}