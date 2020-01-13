package com.just.rebate.entity;

import java.util.List;

public class PaymentData {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"ReceivingType":1,"ReceivingBank":2,"UserUserName":"admin","ImageServerUrl":"http://192.168.1.190:12004","IsDefault":1,"ReceivingBankName":"农业银行","PayModeType":2,"Id":10,"BankBranch":"shoukuannongye","ReceivingAccount":"shoukuannongye","UserId":1,"ReceivingName":"shoukuannongye","ReceivingImg":"","CreatedTime":"2020-01-07T16:27:46.304747"}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * ReceivingType : 1
         * ReceivingBank : 2
         * UserUserName : admin
         * ImageServerUrl : http://192.168.1.190:12004
         * IsDefault : 1
         * ReceivingBankName : 农业银行
         * PayModeType : 2
         * Id : 10
         * BankBranch : shoukuannongye
         * ReceivingAccount : shoukuannongye
         * UserId : 1
         * ReceivingName : shoukuannongye
         * ReceivingImg :
         * CreatedTime : 2020-01-07T16:27:46.304747
         */

        public int ReceivingType;
        public int ReceivingBank;
        public String UserUserName;
        public String ImageServerUrl;
        public int IsDefault;
        public String ReceivingBankName;
        public int PayModeType;
        public int Id;
        public String BankBranch;
        public String ReceivingAccount;
        public int UserId;
        public String ReceivingName;
        public String ReceivingImg;
        public String CreatedTime;
    }
}
