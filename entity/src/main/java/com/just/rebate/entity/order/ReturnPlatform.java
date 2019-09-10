package com.just.rebate.entity.order;

import com.chad.library.adapter.base.entity.IExpandable;
import com.just.rebate.base.BaseEntity;

import java.util.List;

/**
 * 返利平台
 * 京东
 * 淘宝
 * 拼多多
 */
public class ReturnPlatform extends BaseEntity implements IExpandable {




    /**
     * 平台名称
     */
    private String platformName;
    /**
     * 下单时间
     */
    private String orderTime;

    /**
     * 预估返利的钱财
     */
    private String estimatedRebate;

    /**
     * 平台下边  所属的店铺列表
     */
    private List<ReturnShop> returnShops;

    @Override
    public boolean isExpanded() {
        //永远展开 不折叠
        return true;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }

    @Override
    public List getSubItems() {
        return returnShops;
    }

    @Override
    public int getLevel() {
        return 0;
    }


    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getEstimatedRebate() {
        return estimatedRebate;
    }

    public void setEstimatedRebate(String estimatedRebate) {
        this.estimatedRebate = estimatedRebate;
    }

    public List<ReturnShop> getReturnShops() {
        return returnShops;
    }

    public void setReturnShops(List<ReturnShop> returnShops) {
        this.returnShops = returnShops;
    }
}
