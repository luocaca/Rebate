package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 首页数据结构
 */
public class HomeItem extends SectionEntity {

    public String itemName;
    public String logoUrl;
    public String tag ;

    public String getItemName() {
        return itemName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }



    public HomeItem(boolean isHeader, String header) {
        super(isHeader, header);
    }



}