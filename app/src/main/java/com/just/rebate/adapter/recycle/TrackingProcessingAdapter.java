package com.just.rebate.adapter.recycle;

import android.content.Context;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.ArrivalAccountItem;
import com.just.rebate.entity.TrackingProcessingItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class TrackingProcessingAdapter extends BaseSectionQuickAdapter<TrackingProcessingItem, BaseViewHolder> {
    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public TrackingProcessingAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }


    protected void convertHead(BaseViewHolder helper, final TrackingProcessingItem arrivalAccountItem) {
        helper.setText(R.id.arrival_account_name, arrivalAccountItem.itemName_tv);
        helper.setText(R.id.arrival_account_time, arrivalAccountItem.itemTime_tv);

//        helper.addOnClickListener();

/*        helper.getView(R.id.order_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,OrderDetailsActivity.class);
                context.startActivity(intent);
            }
        });
        */


        /*

        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
        */

    }







    protected void convert(BaseViewHolder helper, TrackingProcessingItem item) {
        TrackingProcessingItem trackingProcessingItem = (TrackingProcessingItem) item.t;




    }

}
