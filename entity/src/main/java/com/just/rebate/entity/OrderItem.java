package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class OrderItem extends SectionEntity {
    public String  itemName ;
    public String  itemName_tv;
    public int iteResId ;

    public OrderItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    //

    public String getItemName() {
        return itemName;
    }
    public void setItemName(){
        this.itemName=itemName;
    }
    public String getItemName_tv() {
        return itemName_tv;
    }
    public void setItemName_tv(){
        this.itemName_tv=itemName_tv;
    }

    public int getIteResId() {
        return iteResId;
    }
    public void setIteResId(){
        this.iteResId=iteResId;
    }
}