package com.just.rebate.adapter.recycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ViewUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.ArrivalAccountItem;
import com.just.rebate.entity.OrderItem;
import com.just.rebate.ui.activity.ArrivalAccountActivity;
import com.just.rebate.ui.activity.OrderDetailsActivity;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class ArrivalAccountAdapter extends BaseSectionQuickAdapter<ArrivalAccountItem, BaseViewHolder> {
    private Context context;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ArrivalAccountAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }


    protected void convertHead(BaseViewHolder helper, final ArrivalAccountItem arrivalAccountItem) {
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







    protected void convert(BaseViewHolder helper, ArrivalAccountItem item) {
        ArrivalAccountItem arrivalAccountItem = (ArrivalAccountItem) item.t;
        helper.addOnClickListener(R.id.order_details);



    }

}
