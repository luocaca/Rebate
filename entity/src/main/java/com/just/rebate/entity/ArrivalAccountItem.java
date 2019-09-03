package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ArrivalAccountItem extends SectionEntity {
    public String  itemName_tv ;
    public String  itemTime_tv;
    public int iteResId ;

    public ArrivalAccountItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    //


    public String getItemName_tv() {
        return itemName_tv;
    }

    public void setItemName_tv(String itemName_tv) {
        this.itemName_tv = itemName_tv;
    }

    public String getItemTime_tv() {
        return itemTime_tv;
    }

    public void setItemTime_tv(String itemTime_tv) {
        this.itemTime_tv = itemTime_tv;
    }

    public int getIteResId() {
        return iteResId;
    }

    public void setIteResId(int iteResId) {
        this.iteResId = iteResId;
    }
}