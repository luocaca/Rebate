package com.rebate.base.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 基础activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(bindLayoutId());

        mActivity = this;

        ButterKnife.bind(this);



        initView();



        requestData();


    }

    protected abstract void requestData();

    protected abstract void initView();

    public abstract int bindLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivity = null;
    }
}

