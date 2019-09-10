package com.just.rebate.entity.order;

import android.widget.Checkable;

import com.chad.library.adapter.base.entity.IExpandable;
import com.just.rebate.base.BaseEntity;

import java.util.List;

/**
 * 返利商品
 * 米拉服饰
 * 二傻专卖店
 * 三傻的店铺
 */
public class ReturnOrder extends BaseEntity implements IExpandable, Checkable {


    /**
     * 封面 图片地址
     */
    private String coverUrl;

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

    public String getOrderIntroduction() {
        return orderIntroduction;
    }

    public void setOrderIntroduction(String orderIntroduction) {
        this.orderIntroduction = orderIntroduction;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    /**
     * 商品名称
     */
    private String orderName;


    /**
     * 商品简介
     */
    private String orderIntroduction;

    /**
     * 商品价格
     */
    private String commodityPrice;


    @Override
    public boolean isExpanded() {
        return false;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }

    @Override
    public List getSubItems() {
        return null;
    }

    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public void setChecked(boolean b) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
