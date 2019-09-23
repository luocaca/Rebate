package com.just.rebate.adapter.recycle;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.ArrivalAccountItem;

import java.util.List;

public abstract class InvalidOrderAdapter extends BaseQuickAdapter<ArrivalAccountItem, BaseViewHolder> {
    private Context context;

    public InvalidOrderAdapter(int layoutResId, @Nullable List<ArrivalAccountItem> data) {
        super(layoutResId, data);
    }

    protected void convertHead(BaseViewHolder helper, final ArrivalAccountItem arrivalAccountItem) {
        helper.setText(R.id.arrival_account_name, arrivalAccountItem.itemName_tv);
        helper.setText(R.id.arrival_account_time, arrivalAccountItem.itemTime_tv);

    }


        protected void convert(BaseViewHolder helper, ArrivalAccountItem item) {
        helper.addOnClickListener(R.id.arrival_account_to_order_details);



    }
}
