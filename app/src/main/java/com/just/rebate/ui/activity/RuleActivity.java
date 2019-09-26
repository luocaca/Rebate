package com.just.rebate.ui.activity;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;

/**
 * title 规则
 */

public class RuleActivity extends BaseActivity {
//    @BindView(R.id.back_rule)
//    ImageView mIv_back;

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initonClick();

    }
    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    private void initonClick() {
//        mIv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();onBackPressed();
//            }
//        });
    }

    @Override
    public int bindLayoutId() {
        return R.layout.rule;
    }
}
