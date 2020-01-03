package com.just.integralmanagement.entity;

import java.util.List;

public class NoticeDataBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"IsState":1,"Id":2,"NoticeName":"111111","content":"1111111111","ReleaseTime":"2019-12-16 11:04:46","BeginTime":"2019-12-16 11:04:53","EndTime":"2019-12-19 11:05:03","CreatedTime":"2019-12-17 11:05:08"}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * IsState : 1
         * Id : 2
         * NoticeName : 111111
         * content : 1111111111
         * ReleaseTime : 2019-12-16 11:04:46
         * BeginTime : 2019-12-16 11:04:53
         * EndTime : 2019-12-19 11:05:03
         * CreatedTime : 2019-12-17 11:05:08
         */

        public int IsState;
        public int Id;
        public String NoticeName;
        public String content;
        public String ReleaseTime;
        public String BeginTime;
        public String EndTime;
        public String CreatedTime;
    }
}
