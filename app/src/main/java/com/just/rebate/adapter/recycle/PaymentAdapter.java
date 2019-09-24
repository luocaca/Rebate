package com.just.rebate.adapter.recycle;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.R;

import java.util.List;

public class PaymentAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PaymentAdapter(List data) {
        super(data);
        addItemType(0, R.layout.item_payment_head);
        addItemType(1,R.layout.item_payment_header2);
        addItemType(2,R.layout.item_payment_content);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {

    }
}
