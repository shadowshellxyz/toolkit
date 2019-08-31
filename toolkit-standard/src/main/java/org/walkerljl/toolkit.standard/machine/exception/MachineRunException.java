package org.walkerljl.toolkit.standard.machine.exception;

import org.walkerljl.toolkit.standard.exception.code.ErrorCode;

/**
 * Exception of machine run
 *
 * @author xingxun
 */
public class MachineRunException extends MachineException {

    private static final long serialVersionUID = -6786549876849535944L;

    /**
     * 默认构造函数
     */
    public MachineRunException() {
        super();
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     */
    public MachineRunException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param e 异常对象
     */
    public MachineRunException(Throwable e) {
        super(e);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public MachineRunException(ErrorCode code) {
        super(code.getDescription());
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     */
    public MachineRunException(ErrorCode code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     * @param e    异常对象
     */
    public MachineRunException(ErrorCode code, Throwable e) {
        super(code.getDescription(), e);
        this.code = code;
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param e       异常对象
     */
    public MachineRunException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * 构造函数
     *
     * @param code    异常码
     * @param message 异常消息
     * @param e       异常对象
     */
    public MachineRunException(ErrorCode code, String message, Throwable e) {
        super(code, message, e);
    }

}