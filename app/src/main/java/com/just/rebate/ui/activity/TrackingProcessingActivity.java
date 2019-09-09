package com.just.rebate.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.TrackingProcessingAdapter;
import com.just.rebate.entity.ArrivalAccountItem;
import com.just.rebate.entity.TrackingProcessingItem;

import java.util.ArrayList;
import java.util.List;

public class TrackingProcessingActivity extends AppCompatActivity {
    private RecyclerView mrecyclerView;
    private List<TrackingProcessingItem> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_processing);
        mrecyclerView = findViewById(R.id.rv_list6);
        mrecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        List<TrackingProcessingItem> trackingProcessingItems = new ArrayList<>();
        trackingProcessingItems.add(new TrackingProcessingItem(true, "淘宝"));
        trackingProcessingItems.add(new TrackingProcessingItem(false, "name"));
        trackingProcessingItems.add(new TrackingProcessingItem(true, "淘宝"));
        trackingProcessingItems.add(new TrackingProcessingItem(false, "name"));
        mDatas = trackingProcessingItems;
        TrackingProcessingAdapter trackingProcessingAdapter = new TrackingProcessingAdapter(R.layout.item_tracking_processing_content, R.layout.item_tracking_processing_head, mDatas) {
            @Override
            protected void convertHead(BaseViewHolder helper, TrackingProcessingItem arrivalAccountItem) {
                super.convertHead(helper, arrivalAccountItem);
                helper.setText(R.id.arrival_account_name, "淘宝");
                helper.setText(R.id.arrival_account_time, "2019-07-31接单成功");
            }

            @Override
            protected void convert(BaseViewHolder helper, TrackingProcessingItem item) {
                super.convert(helper, item);
                helper.addOnClickListener(R.id.transition_position_to_success);

            }

        };
        trackingProcessingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent =new Intent(TrackingProcessingActivity.this,SuccessActivity.class);
                startActivity(intent);
            }
        });
        mrecyclerView.setAdapter(trackingProcessingAdapter);
    }
}