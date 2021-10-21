package net.shadowdefender.toolkit.sirector;

import net.shadowdefender.toolkit.standard.exception.AppException;
import net.shadowdefender.toolkit.standard.exception.code.ErrorCode;

/**
 * Sirector异常
 *
 * @author shadow
 */
public class SirectorException extends AppException {

	private static final long serialVersionUID = -7364023527337612587L;

	/**
	 * 默认构造函数
	 */
	public SirectorException() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param message 异常消息
	 */
	public SirectorException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 *
	 * @param e 异常对象
	 */
	public SirectorException(Throwable e) {
		super(e);
	}

	/**
	 * 构造函数
	 *
	 * @param code 异常码
	 */
	public SirectorException(ErrorCode code) {
		super(code.getDescription());
		this.code = code;
	}

	/**
	 * 构造函数
	 *
	 * @param code 异常码
	 * @param message 异常消息
	 */
	public SirectorException(ErrorCode code, String message) {
		super(code, message);
	}

	/**
	 * 构造函数
	 *
	 * @param code 异常码
	 * @param e 异常对象
	 */
	public SirectorException(ErrorCode code, Throwable e) {
		super(code.getDescription(), e);
		this.code = code;
	}

	/**
	 * 构造函数
	 *
	 * @param message 异常消息
	 * @param e 异常对象
	 */
	public SirectorException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * 构造函数
	 *
	 * @param code 异常码
	 * @param message 异常消息
	 * @param e 异常对象
	 */
	public SirectorException(ErrorCode code, String message, Throwable e) {
		super(code, message, e);
	}
}