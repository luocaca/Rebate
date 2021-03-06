package com.just.rebate.entity;


import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 首页数据结构
 */
public class HomeItem implements IExpandable<PlatformsBean>, MultiItemEntity {
//AbstractExpandableItem

    /**
     * Name : 购物返利
     * Platforms : [{"Logo":"http://192.168.1.137:7001/upload-files/07461d655b88115a08e528d0c1d0e0f1-123114220253.jpg","Name":"京东","Url":"m.jd.com","PlatformGroupId":1,"PlatformGroupName":"购物返利","Priority":1,"Id":3,"CreatedTime":"2019-12-31T14:22:12.9175911"},{"Logo":"http://192.168.1.137:7001/upload-files/jinkela-122616183144.jpg","Name":"淘宝","Url":"m.taobao.com","PlatformGroupId":1,"PlatformGroupName":"购物返利","Priority":20,"Id":1,"CreatedTime":"2019-12-26T16:18:47.5089261"}]
     * Id : 1
     * CreatedTime : 2019-12-26T14:23:11.211685
     */

    public String Name;
    public int Id;
    public String CreatedTime;
    public List<PlatformsBean> Platforms;


    public int level =0;
    public int itemType = 0;

    public boolean isExpanded;


    @Override
    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @Override
    public List<PlatformsBean> getSubItems() {
        return Platforms;
    }


    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getItemType() {
        return itemType;
    }


}