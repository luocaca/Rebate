package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class PlatformsBean implements MultiItemEntity {

    /**
     * Logo : upload-files/jinkela-122616183144.jpg
     * Name : 淘宝
     * Url : m.taobao.com
     * PlatformGroupId : 1
     * PlatformGroupName : 返利
     * Priority : 20
     * Id : 1
     * CreatedTime : 2019-12-26T16:18:47.5089261
     */

    public String Logo;
    public String Name;
    public String Url;
    public int PlatformGroupId;
    public String PlatformGroupName;
    public int Priority;
    public int Id;
    public String CreatedTime;


    public int itemType = 1 ;

    @Override
    public int getItemType() {
        return itemType;
    }
}
