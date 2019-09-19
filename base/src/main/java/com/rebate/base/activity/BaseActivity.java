package com.rebate.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.wedget.MyTitleBar;

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


        View view = bindTitleView();

        if (view == null) {
            view = bindTitleView(bindTitleViewId());
        }

        if (view != null) {
            if (view instanceof MyTitleBar) {
                ((MyTitleBar) view).setClickCallback(new MyTitleBar.ClickCallback() {
                    @Override
                    public void itemLeftonClick() {
                        doLeftClick();
                    }

                    @Override
                    public void itemRightonClick() {

                        doRightClick();
                    }
                });
            }
        }


        requestData();


    }

    protected int bindTitleViewId() {
        return 0;
    }

    protected void doRightClick() {

    }

    protected void doLeftClick() {
        finish();
    }

    protected View bindTitleView() {
        return null;
    }


    protected View bindTitleView(@IdRes int viewId) {
        return findViewById(viewId);
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

