package com.just.integralmanagement.entity;

import java.util.List;

public class RankApprenticeBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"Id":1,"UserName":"admin","TotalCount":11},{"Id":10,"UserName":"wcn7","TotalCount":3},{"Id":11,"UserName":"wcn7_1","TotalCount":0},{"Id":12,"UserName":"wcn7_2","TotalCount":0},{"Id":13,"UserName":"wcn7_3","TotalCount":0},{"Id":14,"UserName":"justsky","TotalCount":0},{"Id":15,"UserName":"18584802545","TotalCount":0},{"Id":16,"UserName":"wang123","TotalCount":0},{"Id":17,"UserName":"just","TotalCount":0},{"Id":2,"UserName":"osharp","TotalCount":0},{"Id":3,"UserName":"adminapp","TotalCount":0},{"Id":4,"UserName":"wcn1","TotalCount":0},{"Id":5,"UserName":"wcn2","TotalCount":0},{"Id":6,"UserName":"wcn3","TotalCount":0},{"Id":7,"UserName":"wcn4","TotalCount":0},{"Id":8,"UserName":"wcn5","TotalCount":0},{"Id":9,"UserName":"wcn6","TotalCount":0}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * Id : 1
         * UserName : admin
         * TotalCount : 11
         */

        public int Id;
        public String UserName;
        public int TotalCount;
        public int RewardNum;
    }
}
