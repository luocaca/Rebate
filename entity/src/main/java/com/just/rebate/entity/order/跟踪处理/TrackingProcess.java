package com.just.rebate.entity.order.跟踪处理;

import android.widget.Checkable;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.base.BaseEntity;

import java.util.List;

/**
 * 跟踪订单
 * 类型
 */
public class TrackingProcess extends BaseEntity implements IExpandable, Checkable, MultiItemEntity {


    private String platformName;
    private String reciverTime;
    private String status;


    private List<TrackingProcessOrder> orders;

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getReciverTime() {
        return reciverTime;
    }

    public void setReciverTime(String reciverTime) {
        this.reciverTime = reciverTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<TrackingProcessOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<TrackingProcessOrder> orders) {
        this.orders = orders;
    }

    @Override
    public int getItemType() {
        return 1;
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

    @Override
    public boolean isExpanded() {
        return false;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }

    @Override
    public List getSubItems() {
        return orders;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
