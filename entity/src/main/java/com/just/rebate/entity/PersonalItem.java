package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class PersonalItem extends SectionEntity {
    public String  itemName ;
    public int iteResId ;

    public PersonalItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    //

    public String getItemName() {
        return itemName;
    }
    public void setItemName(){
        this.itemName=itemName;
    }

    public int getIteResId() {
        return iteResId;
    }
    public void setIteResId(){
        this.iteResId=iteResId;
    }
}