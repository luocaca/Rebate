package com.just.integralmanagement.entity;

public class ErrorDataBean {

    /**
     * Type : 403
     * Content : 当前用户权限不足，不能继续执行
     * Data : null
     */

    private int Type;
    private String Content;
    private Object Data;

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }
}
