package com.just.rebate.entity.order;

import android.widget.Checkable;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.base.BaseEntity;

import java.util.List;

/**
 * 返利商店
 * 米拉服饰
 * 二傻专卖店
 * 三傻的店铺
 */
public class ReturnShop extends BaseEntity implements IExpandable , MultiItemEntity, Checkable
{

    private boolean mChecked;

    /**
     * 店铺名称
     * 米拉服饰
     */
    private String shopName;


    /**
     * 当前店铺 代付款的订单
     * 牛仔裤
     * 九分裤
     * 内裤
     * ...
     */
    private List<ReturnOrder> returnOrders;


    @Override
    public boolean isExpanded() {
        return false;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }

    @Override
    public List getSubItems() {
        return returnOrders;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<ReturnOrder> getReturnOrders() {
        return returnOrders;
    }

    public void setReturnOrders(List<ReturnOrder> returnOrders) {
        this.returnOrders = returnOrders;
    }

    @Override
    public int getItemType() {
        return 1;
    }

    @Override
    public void setChecked(boolean checked) {

            mChecked = checked;

    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);

    }
}
