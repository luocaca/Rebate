package com.just.integralmanagement.entity;

import java.util.List;

public class RankReceiptBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"IntegralNum":-3800,"Account":"wcn7","RewardNum":0},{"IntegralNum":-2500,"Account":"wcn7_1","RewardNum":0}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * IntegralNum : -3800
         * Account : wcn7
         * RewardNum : 0
         */

        public int IntegralNum;
        public String UserName;
        public int UserId;
        public int RewardNum;
    }
}
