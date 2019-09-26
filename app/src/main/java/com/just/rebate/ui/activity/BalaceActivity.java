package com.just.rebate.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * title 提现
 */
public class BalaceActivity extends BaseActivity {
//    private ImageView mIv_back;

    @BindView(R.id.blance_to_cash)
    Button mBtn_Cash;


    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initOnClick();

    }

    private void initOnClick() {
        mBtn_Cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(BalaceActivity.this,CashWithdrawalActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public int bindLayoutId() {
        return R.layout.balance;
    }
}
