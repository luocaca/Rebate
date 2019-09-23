package com.just.rebate.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.InvalidOrderAdapter;
import com.just.rebate.entity.ArrivalAccountItem;
import com.rebate.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InvalidOrderActivity extends BaseActivity {

    private List<ArrivalAccountItem> mDatas;

    @BindView(R.id.img_back)
    ImageView mIv_back;

    @BindView(R.id.InvalidOrder_to_ArrivalAccount)
    ImageView mInvalidOrder_to_ArrivalAccount;

    @BindView(R.id.InvalidOrder_to_Order)
    ImageView mInvalidOrder_to_Order;

    @BindView(R.id.InvalidOrder_to_Track)
    ImageView mInvalidOrder_to_Track;

    @BindView(R.id.rv_list11)
    RecyclerView mRecyclerView;


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initRecyclerView();
        initData();
        initOnClick();

    }

    private void initOnClick() {

    }

    private void initData() {

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        List<ArrivalAccountItem> arrivalAccountItems = new ArrayList<>();
        arrivalAccountItems.add(new ArrivalAccountItem(true, "淘宝"));
        arrivalAccountItems.add(new ArrivalAccountItem(false, "name"));
        mDatas = arrivalAccountItems;

        InvalidOrderAdapter invalidOrderAdapter = new InvalidOrderAdapter(R.layout.item_arrival_account_merge, mDatas) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, ArrivalAccountItem item) {
                helper.addOnClickListener(R.id.arrival_account_to_order_details);
            }

            @Override
            protected void convertHead(BaseViewHolder helper, ArrivalAccountItem arrivalAccountItem) {
                super.convertHead(helper, arrivalAccountItem);
                helper.setText(R.id.arrival_account_name, "淘宝");
                helper.setText(R.id.arrival_account_time, "2019-07-31接单成功");

            }
        };
        invalidOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(InvalidOrderActivity.this, OrderDetailsActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(invalidOrderAdapter);

    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_invalid_order;
    }
}
