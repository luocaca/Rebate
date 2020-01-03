package com.just.rebate.entity;

public class GetRouteData {

    /**
     * Type : 200
     * Content : null
     * Data : {"Scheme":"ws","Host":"192.168.1.102:8850","Path":"/ws"}
     */

    public int Type;
    public Object Content;
    public DataBean Data;

    public static class DataBean {
        /**
         * Scheme : ws
         * Host : 192.168.1.102:8850
         * Path : /ws
         */

        public String Scheme;
        public String Host;
        public String Path;
    }
}
