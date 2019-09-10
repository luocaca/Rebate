package com.just.rebate.ui.activity.help;

import com.just.rebate.R;
import com.just.rebate.wedget.MyTitleBar;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * 帮助 界面
 */
public class HelpCenterActivity extends BaseActivity {


    @BindView(R.id.title)
    MyTitleBar title;

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {

        title.setClickCallback(new MyTitleBar.ClickCallback() {
            @Override
            public void itemLeftonClick() {
                finish();
            }

            @Override
            public void itemRightonClick() {
                //ignore
            }
        });

    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_help_center;
    }


}
