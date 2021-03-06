package com.just.rebate.data;


import java.util.List;

public class Bank_Card_DataServer {
    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"ReceivingType":1,"ReceivingBank":2,"UserUserName":"admin","ImageServerUrl":"http://192.168.1.190:12004","IsDefault":0,"ReceivingBankName":"农业银行","PayModeType":1,"Id":6,"BankBranch":"集美支行","ReceivingAccount":"134616131561613616","UserId":1,"ReceivingName":"嘎嘎嘎","ReceivingImg":"","CreatedTime":"2020-01-06T16:18:50.8703067"},{"ReceivingType":1,"ReceivingBank":1,"UserUserName":"admin","ImageServerUrl":"http://192.168.1.190:12004","IsDefault":1,"ReceivingBankName":"工商银行","PayModeType":1,"Id":4,"BankBranch":"软三支行","ReceivingAccount":"622425124221255222","UserId":1,"ReceivingName":"哇哈哈","ReceivingImg":"","CreatedTime":"2020-01-06T15:27:08.6899176"}]
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
         * IsDefault : 0
         * ReceivingBankName : 农业银行
         * PayModeType : 1
         * Id : 6
         * BankBranch : 集美支行
         * ReceivingAccount : 134616131561613616
         * UserId : 1
         * ReceivingName : 嘎嘎嘎
         * ReceivingImg :
         * CreatedTime : 2020-01-06T16:18:50.8703067
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
