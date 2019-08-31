package org.walkerljl.toolkit.standard;

import java.io.Serializable;

/**
 * 结果对象
 *
 * <p>
 * 使用说明：<br/>
 * Result result = ....<br/>
 * if (result.isSuccess()) {//判断成功<br/>
 * T data = result.getData();//成功之后获取数据<br/>
 * <p>
 * } else {//失败<br/>
 * <br/>
 * }<br/>
 * result.getMessage();//获取消息体,如：操作成功，操作失败<br/>
 * result.getCode();//获取消息响应码,如：200,404,500等，默认成功：200，失败：500<br/>
 *
 * @author xingxun
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 默认成功编码*/
    public static final String DEFAULT_SUCCESS_CODE    = "200";
    /** 默认失败编码*/
    public static final String DEFAULT_FAILURE_CODE    = "500";
    /** 默认成功消息*/
    public static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    /** 默认失败消息*/
    public static final String DEFAULT_FAILURE_MESSAGE = "FAILURE";

    /** 字段Key：编码*/
    public static final String CODE_KEY    = "code";
    /** 字段Key：消息*/
    public static final String MESSAGE_KEY = "message";
    /** 字段Key：结果*/
    public static final String RESULT_KEY  = "success";
    /** 字段Key：数据*/
    public static final String DATA_KEY    = "data";

    /** 是否成功*/
    private boolean success = true;
    /** 结果码*/
    private String code;
    /** 消息*/
    private String message;
    /** 数据*/
    private T      data;
    /** 备注*/
    private String remark;

    /**
     * 默认构造函数
     */
    public Result() {}

    /**
     * 私有构造函数
     *
     * @param isSuccess 是否成功，true：成功,false：失败
     * @param code   结果码
     * @param message 消息
     * @param data   数据
     */
    private Result(boolean isSuccess, String code, String message, T data) {
        this.success = isSuccess;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //自定义方法

    /**
     * 创建结构
     *
     * @param isSuccess 是否成功，true：成功，false：失败
     * @return
     */
    public static <T> Result<T> create(boolean isSuccess) {
        return Result.create(isSuccess,
                (isSuccess ? DEFAULT_SUCCESS_CODE : DEFAULT_FAILURE_CODE),
                (isSuccess ? DEFAULT_SUCCESS_MESSAGE : DEFAULT_FAILURE_MESSAGE), null);
    }

    /**
     * 创建消息
     *
     * @param isSuccess 是否成功，true：成功,false：失败
     * @param code 消息编码
     * @param message 消息
     * @param data 数据
     * @return
     */
    public static <T> Result<T> create(boolean isSuccess, String code, String message, T data) {
        return isSuccess ? Result.success(code, message, data) : Result.failure(code, message, data);
    }

    /**
     * 创建成功消息
     *
     * @return
     */
    public static <T> Result<T> success() {
        return Result.success(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }

    /**
     * 创建成功消息
     *
     * @param message 消息
     * @return
     */
    public static <T> Result<T> success(String message) {
        return Result.success(DEFAULT_SUCCESS_CODE, message, null);
    }

    /**
     * 创建成功消息
     *
     * @param data 数据
     * @return
     */
    public static <T> Result<T> success(T data) {
        return Result.success(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, data);
    }

    /**
     * 创建成功消息
     *
     * @param message 消息
     * @param data 数据
     * @return
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(true, DEFAULT_SUCCESS_CODE, message, data);
    }

    /**
     * 创建成功消息
     *
     * @param code 结果编码
     * @param message 消息
     * @return
     */
    public static <T> Result<T> success(String code, String message) {
        return new Result<T>(true, code, message, null);
    }

    /**
     * 创建成功消息
     *
     * @param code 结果编码
     * @param message 消息
     * @param data 数据
     * @return
     */
    public static <T> Result<T> success(String code, String message, T data) {
        return new Result<T>(true, code, message, data);
    }

    /**
     * 创建失败消息
     *
     * @return
     */
    public static <T> Result<T> failure() {
        return Result.failure(DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MESSAGE, null);
    }

    /**
     * 创建失败消息
     *
     * @param message 消息
     * @return
     */
    public static <T> Result<T> failure(String message) {
        return Result.failure(DEFAULT_FAILURE_CODE, message, null);
    }

    /**
     * 创建失败消息
     *
     * @param message 消息
     * @param data 数据
     * @return
     */
    public static <T> Result<T> failure(String message, T data) {
        return new Result<T>(false, DEFAULT_FAILURE_CODE, message, data);
    }

    /**
     * 创建失败消息
     *
     * @param data 数据
     * @return
     */
    public static <T> Result<T> failure(T data) {
        return new Result<T>(false, DEFAULT_FAILURE_CODE, DEFAULT_FAILURE_MESSAGE, data);
    }

    /**
     * 创建失败消息
     *
     * @param code 结果编码
     * @param message 消息
     * @param data 数据
     * @return
     */
    public static <T> Result<T> failure(String code, String message, T data) {
        return new Result<T>(false, code, message, data);
    }

    /**
     * 创建失败消息
     *
     * @param code 结果编码
     * @param message 消息
     * @return
     */
    public static <T> Result<T> failure(String code, String message) {
        return new Result<T>(false, code, message, null);
    }

    //setters and getters

    /**
     * 是否成功
     *
     * @return true：成功，false：失败
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 设置是否成功
     *
     * @param success 是否成功，true：成功，false：失败
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取结果编码
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置结果编码
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取消息
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息
     *
     * @param message 消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * 设置数据
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取备注
     *
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", remark='" + remark + '\'' +
                '}';
    }
}