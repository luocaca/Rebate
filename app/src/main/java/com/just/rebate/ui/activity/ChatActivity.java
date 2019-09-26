package com.just.rebate.ui.activity;

import android.view.WindowManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.ChatAdapter;
import com.just.rebate.data.Chat_Msg_DataServer;
import com.rebate.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * title 客服
 */

public class ChatActivity extends BaseActivity {

    @BindView(R.id.rv_list9)
    RecyclerView recyclerView;

//    @BindView(R.id.back_Chat)
//    ImageView mIv_back;

    List<Chat_Msg_DataServer> msg = new ArrayList<>();

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initData();
        initRecyclerview();
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
        return R.layout.chat;
    }

    private void initRecyclerview() {
        ChatAdapter chatAdapter=new ChatAdapter(msg);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        //键盘不遮挡EditText
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
        Chat_Msg_DataServer chat_msg_dataServer2=new Chat_Msg_DataServer();
        chat_msg_dataServer2.setType(1);
        chat_msg_dataServer2.setContent("哪位？");
        chat_msg_dataServer2.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer2);
        Chat_Msg_DataServer chat_msg_dataServer3=new Chat_Msg_DataServer();
        chat_msg_dataServer3.setType(0);
        chat_msg_dataServer3.setContent("我是你爸爸");
        chat_msg_dataServer3.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer3);
        Chat_Msg_DataServer chat_msg_dataServer4=new Chat_Msg_DataServer();
        chat_msg_dataServer4.setType(1);
        chat_msg_dataServer4.setContent("狗屎");
        chat_msg_dataServer4.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer4);
        Chat_Msg_DataServer chat_msg_dataServer5=new Chat_Msg_DataServer();
        chat_msg_dataServer5.setType(0);
        chat_msg_dataServer5.setContent("你才是");
        chat_msg_dataServer5.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer5);
        Chat_Msg_DataServer chat_msg_dataServer6=new Chat_Msg_DataServer();
        chat_msg_dataServer6.setType(1);
        chat_msg_dataServer6.setContent("你才是");
        chat_msg_dataServer6.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer6);
        Chat_Msg_DataServer chat_msg_dataServer7=new Chat_Msg_DataServer();
        chat_msg_dataServer7.setType(1);
        chat_msg_dataServer7.setContent("狗屎");
        chat_msg_dataServer7.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer7);
        Chat_Msg_DataServer chat_msg_dataServer8=new Chat_Msg_DataServer();
        chat_msg_dataServer8.setType(0);
        chat_msg_dataServer8.setContent("狗屎");
        chat_msg_dataServer8.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer8);
        Chat_Msg_DataServer chat_msg_dataServer9=new Chat_Msg_DataServer();
        chat_msg_dataServer9.setType(1);
        chat_msg_dataServer9.setContent("狗屎");
        chat_msg_dataServer9.setImage(R.mipmap.toux);
        msg.add(chat_msg_dataServer9);

    }
}
