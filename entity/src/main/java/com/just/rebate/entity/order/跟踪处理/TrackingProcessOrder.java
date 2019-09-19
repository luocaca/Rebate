package com.just.rebate.entity.order.跟踪处理;

import com.just.rebate.base.BaseEntity;

/**
 * 跟踪订单
 * 类型
 */
public class TrackingProcessOrder extends BaseEntity {


    /**
     * 封面 图片地址
     */
    private String coverUrl;

    /**
     * 商品名称
     */
    private String orderName;


    /**
     * 返利价格
     */
    private String backPrice;


    /**
     * 订单号
     */
    private String orderNo;


    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(String backPrice) {
        this.backPrice = backPrice;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
