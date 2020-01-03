package com.just.integralmanagement.entity;

import java.util.List;

public class AwardRecordDataBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"Id":134,"ClientId":"wcn7_1","UserId":11,"User":null,"Account":"wcn7_1","OrderNo":"1111111111","IntegralNum":1000,"AppType":0,"CreatedTime":"2019-12-12 13:50:25","OneIntegral":200,"TwoIntegral":100,"TotalIntegral":2000,"UseIntegral":0,"Remark":"","TaskPayModeId":null,"IsState":1,"UserName":"wcn7","BonusIntegral":200,"IsOneBonus":0,"Updatable":false,"Deletable":false,"TaskPayModeReceivingName":null}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * Id : 134
         * ClientId : wcn7_1
         * UserId : 11
         * User : null
         * Account : wcn7_1
         * OrderNo : 1111111111
         * IntegralNum : 1000
         * AppType : 0
         * CreatedTime : 2019-12-12 13:50:25
         * OneIntegral : 200
         * TwoIntegral : 100
         * TotalIntegral : 2000
         * UseIntegral : 0
         * Remark :
         * TaskPayModeId : null
         * IsState : 1
         * UserName : wcn7
         * BonusIntegral : 200
         * IsOneBonus : 0
         * Updatable : false
         * Deletable : false
         * TaskPayModeReceivingName : null
         */

        public int Id;
        public String ClientId;
        public int UserId;
        public String User;
        public String Account;
        public String OrderNo;
        public int IntegralNum;
        public int AppType;
        public String CreatedTime;
        public int OneIntegral;
        public int TwoIntegral;
        public int TotalIntegral;
        public int UseIntegral;
        public String Remark;
        public String TaskPayModeId;
        public int IsState;
        public String UserName;
        public int BonusIntegral;
        public int IsOneBonus;
        public boolean Updatable;
        public boolean Deletable;
        public String TaskPayModeReceivingName;
    }
}
