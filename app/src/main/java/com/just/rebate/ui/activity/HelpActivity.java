package com.just.rebate.ui.activity;

import android.widget.Button;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

public class HelpActivity extends BaseActivity {

    @BindView(R.id.Btn_help)
    Button mBtn_Help;


    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_help;
    }
}
