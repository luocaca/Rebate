package com.just.rebate.adapter.recycle;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;

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
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public OrderAdapter( List data) {
        super(data);
        addItemType(MultiItemEntity.FIRST_TYPE,R.layout.item_order_head);
        addItemType(MultiItemEntity.SECOND_TYPE,R.layout.item_order_header2);
        addItemType(MultiItemEntity.NORMAL_TYPE,R.layout.item_order_content);
    }

        /* protected void convertHead(BaseViewHolder helper, final OrderItem item) {
        helper.setText(R.id.order_header, item.itemName);
        helper.setText(R.id.order_tv,item.itemName_tv);

        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);


    } */


    protected void convert (BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case MultiItemEntity.FIRST_TYPE:
                helper.getLayoutPosition();
                //helper.setText(R.id.order_header,item.getItemType());
                //helper.setText(R.id.order_time,item.getItemType());
                //helper.setText(R.id.rich,item.getItemType());
                break;
            case MultiItemEntity.SECOND_TYPE:
                //helper.getLayoutPosition();
                //helper.setText(R.id.order_tv,item.getItemType());
                break;
            case MultiItemEntity.NORMAL_TYPE:u:
                //helper.getLayoutPosition();
                //helper.setImageResource(R.id.order_iv,item.getItemType());
                //helper.setText(R.id.order_name,item.getItemType());
                //helper.setText(R.id.order_price,item.getItemType());
                break;

        }
    }
}
