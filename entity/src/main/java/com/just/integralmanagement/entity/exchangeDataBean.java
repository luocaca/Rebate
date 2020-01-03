package com.just.integralmanagement.entity;

public class exchangeDataBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : {"DayTotal":0,"AllTotal":-10}
     */

    private String Message;
    private boolean Succeeded;
    private boolean Error;
    private int ResultType;
    private DataBean Data;

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

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * DayTotal : 0
         * AllTotal : -10
         */

        private float DayTotal;
        private float AllTotal;
        private float TotalIntegral;

        public float getDayTotal() {
            return DayTotal;
        }

        public void setDayTotal(float dayTotal) {
            DayTotal = dayTotal;
        }

        public float getAllTotal() {
            return AllTotal;
        }

        public void setAllTotal(float allTotal) {
            AllTotal = allTotal;
        }

        public float getTotalIntegral() {
            return TotalIntegral;
        }

        public void setTotalIntegral(float totalIntegral) {
            TotalIntegral = totalIntegral;
        }
    }
}
