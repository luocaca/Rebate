package com.just.rebate.entity;

public class PersonalDataBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : {"UserName":"admin","InvitationCode":null,"TotalIntegral":100,"TotalAmount":0,"TotalRebateAmount":100,"MemberLevel":0,"Id":1}
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
         * UserName : admin
         * InvitationCode : null
         * TotalIntegral : 100
         * TotalAmount : 0
         * TotalRebateAmount : 100
         * MemberLevel : 0
         * Id : 1
         */

        private String UserName;
        private Object InvitationCode;
        private int TotalIntegral;
        private int TotalAmount;
        private int TotalRebateAmount;
        private int MemberLevel;
        private int Id;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public Object getInvitationCode() {
            return InvitationCode;
        }

        public void setInvitationCode(Object InvitationCode) {
            this.InvitationCode = InvitationCode;
        }

        public int getTotalIntegral() {
            return TotalIntegral;
        }

        public void setTotalIntegral(int TotalIntegral) {
            this.TotalIntegral = TotalIntegral;
        }

        public int getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(int TotalAmount) {
            this.TotalAmount = TotalAmount;
        }

        public int getTotalRebateAmount() {
            return TotalRebateAmount;
        }

        public void setTotalRebateAmount(int TotalRebateAmount) {
            this.TotalRebateAmount = TotalRebateAmount;
        }

        public int getMemberLevel() {
            return MemberLevel;
        }

        public void setMemberLevel(int MemberLevel) {
            this.MemberLevel = MemberLevel;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
