package com.just.rebate.adapter.recycle;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.R;
import com.just.rebate.entity.TrackingProcessingItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class TrackingProcessingAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *

     * @param data             A new list is created out of this one to avoid mutable list
     */
    public TrackingProcessingAdapter(List data) {
        super(data);
        addItemType(1, R.layout.item_tracking_processing_head);
        addItemType(2, R.layout.item_tracking_processing_content);
    }


    protected void convertHead(BaseViewHolder helper, final TrackingProcessingItem trackingProcessingItem) {
        helper.setText(R.id.Track_name, trackingProcessingItem.itemName_tv);
        helper.setText(R.id.Track_time, trackingProcessingItem.itemTime_tv);

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
}
