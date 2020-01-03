package com.just.integralmanagement.entity;

public class ApprenticeDataBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : {"Id":1,"TotalBonusIntegral":1200,"InvitationCode":"5300","TodayCount":2,"TotalCount":10,"TodayIntegral":0}
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
         * Id : 1
         * TotalBonusIntegral : 1200
         * InvitationCode : 5300
         * TodayCount : 2
         * TotalCount : 10
         * TodayIntegral : 0
         */

        private int Id;
        private int TotalBonusIntegral;
        private String InvitationCode;
        private int TodayCount;
        private int TotalCount;
        private int TodayIntegral;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getTotalBonusIntegral() {
            return TotalBonusIntegral;
        }

        public void setTotalBonusIntegral(int TotalBonusIntegral) {
            this.TotalBonusIntegral = TotalBonusIntegral;
        }

        public String getInvitationCode() {
            return InvitationCode;
        }

        public void setInvitationCode(String InvitationCode) {
            this.InvitationCode = InvitationCode;
        }

        public int getTodayCount() {
            return TodayCount;
        }

        public void setTodayCount(int TodayCount) {
            this.TodayCount = TodayCount;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getTodayIntegral() {
            return TodayIntegral;
        }

        public void setTodayIntegral(int TodayIntegral) {
            this.TodayIntegral = TodayIntegral;
        }
    }
}
