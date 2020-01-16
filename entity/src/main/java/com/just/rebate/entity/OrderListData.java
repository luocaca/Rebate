package com.just.rebate.entity;

import android.widget.Checkable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderListData {

    /**
     * Rows : [{"OrderNo":"107646720867","OrderPlatform":0,"OrderPlatformName":"京东","Amount":199,"Count":0,"OrderStatus":0,"OrderStatusName":"创建中","PaymentTime":null,"PaymentExpried":"2020-01-03T19:13:06.0807539","PaymentType":0,"PaymentTypeName":"微信支付","PaymentUrl":null,"ReceiverAddress":"湖南长沙市岳麓区梅溪湖街道咸嘉湖西路纯磨坊客家豆腐","ReceiverName":"牛坤坤","Mobile":"185****6811","OrderItems":[{"ProductName":"小米路由器4 双千兆路由器 无线家用穿墙 1200M双频无线速率 5G 千兆端口 光纤适用","Count":1,"Price":199,"SpecName":"【全千兆】小米路由4","Image":"http://img10.360buyimg.com/n5/jfs/t1/65694/20/7782/127342/5d5b9fdaEde110fb8/b09009988bd11f8a.jpg","OrderId":12}],"Id":12,"CreatedTime":"2020-01-03T18:44:06.0848171"}]
     * Total : 1
     */

    public int Total;
    public List<RowsBean> Rows;


    public static class RowsBean implements MultiItemEntity, Checkable {
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
        public double Amount;
        public int Count;
        private boolean mChecked;
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

        @Override
        public int getItemType() {
            return 0;
        }

        @Override
        public void setChecked(boolean checked) {
            if(mChecked != checked){
                mChecked = checked;
            }
        }
        @Override
        public boolean isChecked() {
            return mChecked;
        }

        @Override
        public void toggle() {
            setChecked(!mChecked);
        }

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
}
