package com.just.rebate.base;

import com.google.gson.JsonElement;

/**
 * 基础响应 数据结构
 */
public class BaseResponse<T> {


    /**
     * 返回信息
     * 成功
     * 或者
     * 失败原因
     */
    private String message;
    /**
     * 返回码 200 代表成功
     */
    private int code;

    /**
     * 返回的数据体
     */
    private T data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
