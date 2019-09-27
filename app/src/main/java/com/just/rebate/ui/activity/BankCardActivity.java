package com.just.rebate.ui.activity;

import android.graphics.Color;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.BankCardAdapter;
import com.just.rebate.data.Bank_Card_DataServer;
import com.rebate.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * title 银行卡
 */
public class BankCardActivity extends BaseActivity {


    @BindView(R.id.rv_list8)
    RecyclerView recyclerView;


    @BindView(R.id.rv_SwipeRefresh)
    SwipeRefreshLayout rv_SwipeRefresh;


    List<Bank_Card_DataServer> mDataServer = new ArrayList<>();


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initData();
        initRecyclerview();
    }

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_bank_card;
    }

    private void initRecyclerview() {
        BankCardAdapter mbankCardAdapter = new BankCardAdapter(mDataServer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        R.layout.item_bank_card
//        recyclerView.setAdapter(new BaseQuickAdapter<Bank_Card_DataServer, BaseViewHolder>(R.layout.item_bank_card, mDataServer) {
//            @Override
//            protected void convert(@NonNull BaseViewHolder helper, Bank_Card_DataServer item) {
//                helper
//                        .setText(R.id.bank, item.getText())
//                        .setText(R.id.bank, item.getText())
//                        .setText(R.id.bank, item.getText())
//                        .setText(R.id.bank, item.getText());
//            }
//        });


        BaseQuickAdapter baseQuickAdapter = new BaseQuickAdapter<Bank_Card_DataServer, BaseViewHolder>(R.layout.item_bank_card1, mDataServer) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Bank_Card_DataServer item) {
                helper
                        .setText(R.id.bank, item.getText())
                        .setText(R.id.bank, item.getText())
                        .setText(R.id.bank, item.getText())
                        .setBackgroundColor(R.id.bank_card1,item.getBackgroundcolor())
                        .setText(R.id.bank, item.getText());
            }
        };
        baseQuickAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.footview_layout,null));
        // 分割线
        // recyclerView.addItemDecoration(new DetailedActivity.MyDecoration());
        recyclerView.setAdapter(baseQuickAdapter);


    }

    private void initData() {
        Bank_Card_DataServer bank_card_dataServer = new Bank_Card_DataServer();
        bank_card_dataServer.setText("中国银行");
        bank_card_dataServer.setNumber("**** **** **** 1234");
        bank_card_dataServer.setPic(R.mipmap.bank);
        bank_card_dataServer.setBackgroundcolor(Color.parseColor("#628Ef9"));
        mDataServer.add(bank_card_dataServer);

        Bank_Card_DataServer bank_card_dataServer1 = new Bank_Card_DataServer();
        bank_card_dataServer1.setText("中国银行");
        bank_card_dataServer1.setNumber("**** **** **** 1234");
        bank_card_dataServer1.setPic(R.mipmap.bank);
        bank_card_dataServer1.setBackgroundcolor(Color.parseColor("#04856F"));
        mDataServer.add(bank_card_dataServer1);

        Bank_Card_DataServer bank_card_dataServer2 = new Bank_Card_DataServer();
        bank_card_dataServer2.setText("中国银行");
        bank_card_dataServer2.setNumber("**** **** **** 1234");
        bank_card_dataServer2.setPic(R.mipmap.bank);
        bank_card_dataServer2.setBackgroundcolor(Color.parseColor("#000000"));
        mDataServer.add(bank_card_dataServer2);

        Bank_Card_DataServer bank_card_dataServer3 = new Bank_Card_DataServer();
        bank_card_dataServer3.setText("中国银行");
        bank_card_dataServer3.setNumber("**** **** **** 1234");
        bank_card_dataServer3.setPic(R.mipmap.bank);
        bank_card_dataServer3.setBackgroundcolor(Color.parseColor("#EBFBC82D"));
        mDataServer.add(bank_card_dataServer3);

        Bank_Card_DataServer bank_card_dataServer4 = new Bank_Card_DataServer();
        bank_card_dataServer4.setText("中国银行");
        bank_card_dataServer4.setNumber("**** **** **** 1234");
        bank_card_dataServer4.setPic(R.mipmap.bank);
        bank_card_dataServer4.setBackgroundcolor(Color.parseColor("#FF7791"));
        mDataServer.add(bank_card_dataServer4);


    }
}
