package com.just.integralmanagement.entity;

public class RegisterDataBean {

    /**
     * Message : 用户注册成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : {"Id":19,"UserName":"w1234567899","NickName":"w1234567899","Email":null}
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
         * Id : 19
         * UserName : w1234567899
         * NickName : w1234567899
         * Email : null
         */

        private int Id;
        private String UserName;
        private String NickName;
        private Object Email;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public Object getEmail() {
            return Email;
        }

        public void setEmail(Object Email) {
            this.Email = Email;
        }
    }
}
