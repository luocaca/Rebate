package com.just.rebate.entity;


import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class WebSocketClientPayBean {


    @SerializedName("Order")
    public OrderBean _$Order311; // FIXME check this code
    public JsonElement Headers;
    public String CallBackUrl;
    public List<RulesBean> Rules;

    public static class OrderBean {
        /**
         * OrderNo : 107646720867
         * OrderPlatform : 0
         * OrderPlatformName : 京东
         * Amount : 199
         * Count : 0
         * OrderStatus : 0
         * OrderStatusName : 创建中
         * PaymentTime : null
         * PaymentExpried : 2020-01-03T19:13:06.0807539
         * PaymentType : 0
         * PaymentTypeName : 微信支付
         * PaymentUrl : null
         * ReceiverAddress : 湖南长沙市岳麓区梅溪湖街道咸嘉湖西路纯磨坊客家豆腐
         * ReceiverName : 牛坤坤
         * Mobile : 185****6811
         * OrderItems : [{"ProductName":"小米路由器4 双千兆路由器 无线家用穿墙 1200M双频无线速率 5G 千兆端口 光纤适用","Count":1,"Price":199,"SpecName":"【全千兆】小米路由4","Image":"http://img10.360buyimg.com/n5/jfs/t1/65694/20/7782/127342/5d5b9fdaEde110fb8/b09009988bd11f8a.jpg","OrderId":12}]
         * Id : 12
         * CreatedTime : 2020-01-03T18:44:06.0848171
         */

        public String OrderNo;
        public int OrderPlatform;
        public String OrderPlatformName;
        public int Amount;
        public int Count;
        public int OrderStatus;
        public String OrderStatusName;
        public Object PaymentTime;
        public String PaymentExpried;
        public int PaymentType;
        public String PaymentTypeName;
        public Object PaymentUrl;
        public String ReceiverAddress;
        public String ReceiverName;
        public String Mobile;
        public int Id;
        public String CreatedTime;
        public List<OrderItemsBean> OrderItems;

        public static class OrderItemsBean {
            /**
             * ProductName : 小米路由器4 双千兆路由器 无线家用穿墙 1200M双频无线速率 5G 千兆端口 光纤适用
             * Count : 1
             * Price : 199
             * SpecName : 【全千兆】小米路由4
             * Image : http://img10.360buyimg.com/n5/jfs/t1/65694/20/7782/127342/5d5b9fdaEde110fb8/b09009988bd11f8a.jpg
             * OrderId : 12
             */

            public String ProductName;
            public int Count;
            public int Price;
            public String SpecName;
            public String Image;
            public int OrderId;
        }
    }



    public static class RulesBean {
        @SerializedName("RequestPacket")
        public RequestPacketBean _$RequestPacket15; // FIXME check this code
        public List<DicsBean> Dics;

        public static class RequestPacketBean {
            @SerializedName("Version")
            public String _$Version207; // FIXME check this code
            public String Mothed;
            public String Url;
            public Map<String,String> Headers;
            public String Content;

            public static class HeadersBeanX {
                @SerializedName("Host")
                public String Host; // FIXME check this code
                public String Connection;
                @SerializedName("User-Agent")
                public String UserAgent;
                public String Accept;
                public String Referer;
                @SerializedName("Accept-Encoding")
                public String AcceptEncoding;
                @SerializedName("Accept-Language")
                public String AcceptLanguage;
                public String Cookie;
            }
        }

        public static class DicsBean {
            /**
             * Key : PayId
             * Value : newPay-index.html\?payId=(?<PayId>.*?)&
             * Method : 0
             */

            public String Key;
            public String Value;
            public int Method;
        }
    }
}
