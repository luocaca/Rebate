package com.just.rebate.entity;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 首页数据结构
 */
public class HomeItem extends SectionEntity {

    private String itemName;
    private String logoUrl;
    private String tag;


    public int bgId;


    public HomeItem(String itemName, String logoUrl, String tag, boolean isHead) {
        super(isHead, itemName);
        this.itemName = itemName;
        this.logoUrl = logoUrl;
        this.tag = tag;
    }

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


    public boolean isHead() {
        return super.isHeader;
    }


}