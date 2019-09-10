package com.just.rebate.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.BankCardAdapter;
import com.just.rebate.adapter.recycle.DetailsAdapter;
import com.just.rebate.data.Bank_Card_DataServer;
import com.just.rebate.data.detailed_DataServer;
import com.just.rebate.entity.Personal_local_Item;
import com.just.rebate.ui.MainActivity;
import com.rebate.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 银行卡
 */
public class BankCardActivity extends BaseActivity {


    @BindView(R.id.rv_list8)
    RecyclerView recyclerView;


    @BindView(R.id.rv_SwipeRefresh)
    SwipeRefreshLayout rv_SwipeRefresh;


    List<Bank_Card_DataServer> mDataServer = new ArrayList<>();


    @OnClick(R.id.title)
    public void doBack() {
        finish();
    }


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


        BaseQuickAdapter baseQuickAdapter = new BaseQuickAdapter<Bank_Card_DataServer, BaseViewHolder>(R.layout.item_bank_card, mDataServer) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Bank_Card_DataServer item) {
                helper
                        .setText(R.id.bank, item.getText())
                        .setText(R.id.bank, item.getText())
                        .setText(R.id.bank, item.getText())
                        .setText(R.id.bank, item.getText());
            }
        };
        baseQuickAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.item_head,null));
        // 分割线
        // recyclerView.addItemDecoration(new DetailedActivity.MyDecoration());
        recyclerView.setAdapter(baseQuickAdapter);


    }

    private void initData() {
        Bank_Card_DataServer bank_card_dataServer = new Bank_Card_DataServer();
        bank_card_dataServer.setText("中国银行");
        bank_card_dataServer.setNumber("**** **** **** 1234");
        bank_card_dataServer.setPic(R.mipmap.bank);


        for (int i = 0; i < 10; i++) {
            mDataServer.add(bank_card_dataServer);
        }

    }
}
