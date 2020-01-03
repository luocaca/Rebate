package com.just.rebate.entity;

public class ResgsterDataBean {

    /**
     * Type : 200
     * Content : string
     * Data : {}
     */

    public int Type;
    public String Content;
    public DataBean Data;

    public static class DataBean {
        public String UserName;
        public String NickName;
        public String Email;
    }
}
