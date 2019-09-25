package com.just.rebate.ui.activity;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.MessageNotificationAdapter;
import com.just.rebate.entity.MessageNotificationItem;
import com.rebate.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MessageNotificationActivity extends BaseActivity {

    private List<MessageNotificationItem> mDatas;

    @BindView(R.id.rv_list10)
    RecyclerView mRecyclerView;


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initRecyclerview();
        initData();
        initOnClick();

    }
    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    private void initOnClick() {

    }


    @Override
    public int bindLayoutId() {
        return R.layout.activity_message_notification;
    }

    private void initData() {

    }

    private void initRecyclerview() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        List<MessageNotificationItem> messageNotificationItems = new ArrayList<>();
        messageNotificationItems.add(new MessageNotificationItem(true, "淘宝"));
        messageNotificationItems.add(new MessageNotificationItem(false, "name"));
        mDatas=messageNotificationItems;
        MessageNotificationAdapter messageNotificationAdapter=new MessageNotificationAdapter(R.layout.item_message_notification,mDatas);
        messageNotificationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "嘀嘀嘀", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(messageNotificationAdapter);
    }
}
