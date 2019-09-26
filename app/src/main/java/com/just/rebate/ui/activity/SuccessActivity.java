package com.just.rebate.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * title 领取成功
 */

public class SuccessActivity extends BaseActivity {



    @BindView(R.id.img_back)
    ImageView mIv_back;



    @Override
    public int bindLayoutId() {
        return R.layout.activity_success;
    }


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initOnClick();

    }

    private void initOnClick() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();onBackPressed();
            }
        });
    }


}
