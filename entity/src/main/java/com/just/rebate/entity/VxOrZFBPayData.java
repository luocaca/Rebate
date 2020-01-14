package com.just.rebate.entity;

import java.util.List;

public class VxOrZFBPayData {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"ReceivingType":2,"ReceivingBank":1,"UserUserName":"admin","ImageServerUrl":"http://192.168.1.190:12004","IsDefault":1,"ReceivingBankName":"工商银行","PayModeType":1,"Id":13,"BankBranch":"付款支付宝","ReceivingAccount":"付款支付宝","UserId":1,"ReceivingName":"付款支付宝","ReceivingImg":"upload-files/timg-011316043004.jpg","CreatedTime":"2020-01-13T16:04:34.1342135"}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * ReceivingType : 2
         * ReceivingBank : 1
         * UserUserName : admin
         * ImageServerUrl : http://192.168.1.190:12004
         * IsDefault : 1
         * ReceivingBankName : 工商银行
         * PayModeType : 1
         * Id : 13
         * BankBranch : 付款支付宝
         * ReceivingAccount : 付款支付宝
         * UserId : 1
         * ReceivingName : 付款支付宝
         * ReceivingImg : upload-files/timg-011316043004.jpg
         * CreatedTime : 2020-01-13T16:04:34.1342135
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
