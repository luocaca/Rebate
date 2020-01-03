package com.just.integralmanagement.entity;

import java.util.List;

public class PaymentData {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"ReceivingType":1,"ReceivingBank":123,"ReceivingName":null,"ReceivingImg":null,"User":null,"BankBranch":"123","ReceivingAccount":"123","CreatedTime":"2019-12-09 16:25:23","UserId":1,"Id":1}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * ReceivingType : 1
         * ReceivingBank : 123
         * ReceivingName : null
         * ReceivingImg : null
         * User : null
         * BankBranch : 123
         * ReceivingAccount : 123
         * CreatedTime : 2019-12-09 16:25:23
         * UserId : 1
         * Id : 1
         */

        public int ReceivingType;
        public int ReceivingBank;
        public String ReceivingName;
        public String ReceivingImg;
        public String BankBranch;
        public String ImageServerUrl;
        public String ReceivingBankName;
        public String ReceivingAccount;
        public String CreatedTime;
        public int UserId;
        public int Id;
    }
}
