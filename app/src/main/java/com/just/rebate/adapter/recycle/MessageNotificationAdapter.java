package com.just.rebate.adapter.recycle;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.MessageNotificationItem;

import java.util.List;

public class MessageNotificationAdapter extends BaseQuickAdapter<MessageNotificationItem, BaseViewHolder> {

    public MessageNotificationAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MessageNotificationItem item) {
        helper.addOnClickListener(R.id.onclick);

    }
}
