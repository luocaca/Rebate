package com.just.rebate.adapter.recycle;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.R;

import java.util.List;

public abstract class InvalidOrderAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private Context context;

    public InvalidOrderAdapter( List data) {
        super(data);
        addItemType(1, R.layout.item_invalid_order_merge);
        addItemType(2, R.layout.item_invalid_order_merge);
    }

}
