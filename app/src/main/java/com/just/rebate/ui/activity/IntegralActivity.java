package com.just.rebate.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;


/**
 * title 可用积分
 */

public class IntegralActivity extends BaseActivity {
    private MyApplication application;

    @BindView(R.id.AllIntegral)
    TextView mTv_AllIntegral;

    @BindView(R.id.RebateIntegaral)
    TextView mTv_RebateIntegarl;

    @BindView(R.id.RechargeIntegral)
    TextView mTv_RechargeIntegaral;

    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mRv_Refresh;


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initReceviceData();
        initOnClick();

    }

    private void initOnClick() {
        mRv_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initReceviceData();
                mRv_Refresh.setRefreshing(false);
            }
        });
    }

    private void initReceviceData() {
        Intent intent = getIntent();
        mTv_AllIntegral.setText(intent.getStringExtra("AllIntegral"));
        mTv_RebateIntegarl.setText(intent.getStringExtra("RebateIntegral"));
        mTv_RechargeIntegaral.setText(intent.getStringExtra("RechargeIntegral"));
    }

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_integral;
    }
}
