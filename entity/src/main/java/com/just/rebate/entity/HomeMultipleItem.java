package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionMultiEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeMultipleItem extends SectionMultiEntity<HomeItem> implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    private int itemType;
    private boolean isMore;
    private HomeItem homeItem;

    public HomeMultipleItem(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }



    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public HomeItem getVideo() {
        return homeItem;
    }

    public void setVideo(HomeItem video) {
        this.homeItem= video;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
