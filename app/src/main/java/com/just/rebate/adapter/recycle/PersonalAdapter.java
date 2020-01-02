package com.just.rebate.adapter.recycle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.entity.PersonalItem;

import java.util.List;



/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class PersonalAdapter extends BaseQuickAdapter<PersonalItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public PersonalAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, data);
    }

    protected void convertHead(BaseViewHolder helper, final PersonalItem item) {
        //helper.setText(R.id.header, item.getItemName());
        /*
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
        */
    }


}
