package com.just.integralmanagement.entity;

import java.util.List;

public class ActivityDataBean {


    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"ActivityPayType":2,"ActivityImg":"upload-files/timg-121711231532.jpg","ImageServerUrl":"http://192.168.1.190:7003","ActivityPayTypeName":"充值","BeginTime":"2019-12-16 11:35:51","EndTime":"2019-12-29 11:35:56","IsState":1,"Id":2,"ActivityName":"1111111","ActivityDesc":"1111111","BeginNum":2000,"GiveNum":50,"Remark":"111111111111","CreatedTime":"2019-12-17 11:23:31"},{"ActivityPayType":1,"ActivityImg":"upload-files/下载-121616040368.jpg","ImageServerUrl":"http://192.168.1.190:7003","ActivityPayTypeName":"消费","BeginTime":"2019-12-16 11:35:24","EndTime":"2019-12-29 11:35:30","IsState":1,"Id":1,"ActivityName":"123","ActivityDesc":"123","BeginNum":1000,"GiveNum":100,"Remark":"1232131","CreatedTime":"2019-12-16 14:33:59"}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * ActivityPayType : 2
         * ActivityImg : upload-files/timg-121711231532.jpg
         * ImageServerUrl : http://192.168.1.190:7003
         * ActivityPayTypeName : 充值
         * BeginTime : 2019-12-16 11:35:51
         * EndTime : 2019-12-29 11:35:56
         * IsState : 1
         * Id : 2
         * ActivityName : 1111111
         * ActivityDesc : 1111111
         * BeginNum : 2000
         * GiveNum : 50
         * Remark : 111111111111
         * CreatedTime : 2019-12-17 11:23:31
         */

        public int ActivityPayType;
        public String ActivityImg;
        public String ImageServerUrl;
        public String ActivityPayTypeName;
        public String BeginTime;
        public String EndTime;
        public int IsState;
        public int Id;
        public String ActivityName;
        public String ActivityDesc;
        public int BeginNum;
        public int GiveNum;
        public String Remark;
        public String CreatedTime;
    }
}
