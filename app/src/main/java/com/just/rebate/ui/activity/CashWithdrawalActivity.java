package com.just.rebate.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.just.rebate.R;
import com.just.rebate.ui.fragment.PayFragment;
import com.rebate.base.activity.BaseActivity;

import butterknife.OnClick;

public class CashWithdrawalActivity extends BaseActivity implements View.OnClickListener {


    @OnClick(R.id.dialog_cashwwithdrawal)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_cashwwithdrawal) {
            Bundle bundle = new Bundle();
            PayFragment fragment = new PayFragment();
            fragment.setArguments(bundle);
            fragment.show(getSupportFragmentManager(), "Pay");
        }
    }


    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {

    }


    @Override
    public int bindLayoutId() {
        return R.layout.activity_cash_withdrawal;
    }

}
