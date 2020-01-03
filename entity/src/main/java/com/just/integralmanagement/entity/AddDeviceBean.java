package com.just.integralmanagement.entity;

public class AddDeviceBean {
    /**
     * Message : 添加成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : null
     */

    private String Message;
    private boolean Succeeded;
    private boolean Error;
    private int ResultType;
    private Object Data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean isSucceeded() {
        return Succeeded;
    }

    public void setSucceeded(boolean Succeeded) {
        this.Succeeded = Succeeded;
    }

    public boolean isError() {
        return Error;
    }

    public void setError(boolean Error) {
        this.Error = Error;
    }

    public int getResultType() {
        return ResultType;
    }

    public void setResultType(int ResultType) {
        this.ResultType = ResultType;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }
}
