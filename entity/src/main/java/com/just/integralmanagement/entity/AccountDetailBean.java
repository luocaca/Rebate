package com.just.integralmanagement.entity;

import java.util.List;

public class AccountDetailBean {


    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"Account":"qqq","Password":"","AppType":0,"UpStatus":1,"Id":78,"BuyOrSeller":"1","Secret":null,"ClientType":0,"BuyLastSynTimeName":"已下线","SellorLastSynTimeName":"已下线","Phone":"qqq"}]
     */

    public String Message;
    public boolean Succeeded;
    public boolean Error;
    public int ResultType;
    public List<DataBean> Data;

    public static class DataBean {
        /**
         * Account : qqq
         * Password :
         * AppType : 0
         * UpStatus : 1
         * Id : 78
         * BuyOrSeller : 1
         * Secret : null
         * ClientType : 0
         * BuyLastSynTimeName : 已下线
         * SellorLastSynTimeName : 已下线
         * Phone : qqq
         */

        public String Account;
        public String Password;
        public int AppType;
        public int UpStatus;
        public int Id;
        public String BuyOrSeller;
        public String Secret;
        public int ClientType;
        public String BuyLastSynTimeName;
        public String SellorLastSynTimeName;
        public String Phone;
    }
}
