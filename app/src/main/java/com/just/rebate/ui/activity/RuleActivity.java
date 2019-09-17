package com.just.rebate.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

public class RuleActivity extends BaseActivity {
    @BindView(R.id.back_rule)
    ImageView mIv_back;

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initonClick();

    }

    private void initonClick() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();onBackPressed();
            }
        });
    }

    @Override
    public int bindLayoutId() {
        return R.layout.rule;
    }
}
