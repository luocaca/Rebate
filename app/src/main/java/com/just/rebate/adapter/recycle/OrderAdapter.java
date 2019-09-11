package com.just.rebate.adapter.recycle;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class OrderAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderAdapter(List data) {
        super(data);
        addItemType(0, R.layout.item_order_head);
        addItemType(1, R.layout.item_order_header2);
        addItemType(2, R.layout.item_order_content);
    }

        /* protected void convertHead(BaseViewHolder helper, final OrderItem item) {
        helper.setText(R.id.order_header, item.itemName);
        helper.setText(R.id.order_tv,item.itemName_tv);

        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);


    } */



}
