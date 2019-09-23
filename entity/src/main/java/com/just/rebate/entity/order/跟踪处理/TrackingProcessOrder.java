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
public class TrackingProcessOrder extends BaseEntity implements IExpandable, Checkable,MultiItemEntity {

    private List<TrackingProcess>trackingProcess;

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

    @Override
    public int getItemType() {
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
        return 0;
    }

    public List<TrackingProcess> getTrackingProcess() {
        return trackingProcess;
    }

    public void setTrackingProcess(List<TrackingProcess> trackingProcess) {
        this.trackingProcess = trackingProcess;
    }
}
