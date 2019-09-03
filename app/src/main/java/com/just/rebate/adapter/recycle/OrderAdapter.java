package com.just.rebate.adapter.recycle;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.OrderItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class OrderAdapter extends BaseSectionQuickAdapter<OrderItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public OrderAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    protected void convertHead(BaseViewHolder helper, final OrderItem item) {
        helper.setText(R.id.order_header, item.itemName);
        helper.setText(R.id.order_tv,item.itemName_tv);
        /*
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
        */
    }


    protected void conver( BaseViewHolder helper, OrderItem item) {
        OrderItem orderItem = (OrderItem) item.t;
        switch (helper.getLayoutPosition() % 12) {
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.taobao);
                break;
            case 2:
                helper.setImageResource(R.id.iv, R.mipmap.jingdong);
                break;
            case 3:
                helper.setImageResource(R.id.iv, R.mipmap.suning);
                break;
            case 4:
                helper.setImageResource(R.id.iv, R.mipmap.pinduod);
                break;
            case 5:
                helper.setImageResource(R.id.iv, R.mipmap.weipinh);
                break;
            case 6:
                helper.setImageResource(R.id.iv, R.mipmap.gengduo);
                break;
            case 7:
                helper.setImageResource(R.id.iv, R.mipmap.meituan);
                break;
            case 8:
                helper.setImageResource(R.id.iv, R.mipmap.eleme);
                break;
            case 9:
                helper.setImageResource(R.id.iv, R.mipmap.gengduo);
                break;
            case 10:
                helper.setImageResource(R.id.iv, R.mipmap.didi);
                break;
            case 11:
                helper.setImageResource(R.id.iv, R.mipmap.chongzhi);
                break;
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.gengduo);
                break;
            default:
                break;

        }
    }
}
