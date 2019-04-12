package com.zkzz.module.http.model;

import com.zkzz.module.http.net.ErrorCodes;

/**
 * 基础请求模型
 * <p>
 * 2019-02-18
 *
 * @author WuMeng
 * @version 1.0
 */
public class BaseRespondModel<T> {
    /**
     * 请求结果码
     */
    public int code = ErrorCodes.FAILURE;
    /**
     * 提示消息(服务器未能统一返回提示的字段，有两个)
     */
    public String msg;
    /**
     * 提示消息(服务器未能统一返回提示的字段，有两个)
     */
    public String message;
    /**
     * 数据
     */
    public T data;

}
