package com.just.rebate.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.ChatAdapter;
import com.just.rebate.data.Chat_Msg_DataServer;
import com.rebate.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatActivity extends BaseActivity {

    @BindView(R.id.rv_list9)
    RecyclerView recyclerView;

    List<Chat_Msg_DataServer> msg = new ArrayList<>();

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initData();
        initRecyclerview();

    }



    @Override
    public int bindLayoutId() {
        return R.layout.chat;
    }

    private void initRecyclerview() {
        ChatAdapter chatAdapter=new ChatAdapter(msg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

    }

    private void initData() {
        Chat_Msg_DataServer chat_msg_dataServer=new Chat_Msg_DataServer();
        chat_msg_dataServer.setType(0);
        chat_msg_dataServer.setContent("你好");
        chat_msg_dataServer.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer);
        Chat_Msg_DataServer chat_msg_dataServer1=new Chat_Msg_DataServer();
        chat_msg_dataServer1.setType(1);
        chat_msg_dataServer1.setContent("你好");
        chat_msg_dataServer1.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer1);

    }
}
