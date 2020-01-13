package com.just.rebate.entity;

public class RechargeIntegralData1 {
    /**
     * Message : Could not convert string to integer: admin. Path 'UserId', line 3, position 19.
     * Succeeded : false
     * Error : true
     * ResultType : 4
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
