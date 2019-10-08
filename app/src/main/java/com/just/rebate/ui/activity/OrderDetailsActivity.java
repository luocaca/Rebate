package com.just.rebate.ui.activity;

import android.widget.TextView;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * title 订单详情
 */

public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.copy)
    TextView mCopy;

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {

    }
    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_order_details;
    }
}
