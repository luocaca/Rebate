package com.just.rebate.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.BankCardAdapter;
import com.just.rebate.adapter.recycle.DetailsAdapter;
import com.just.rebate.data.Bank_Card_DataServer;
import com.just.rebate.data.detailed_DataServer;

import java.util.ArrayList;
import java.util.List;

public class BankCardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Bank_Card_DataServer> mDataServer=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        initRecyclerview();
        initData();
    }

    private void initRecyclerview() {
        recyclerView=findViewById(R.id.rv_list8);
        BankCardAdapter mbankCardAdapter=new BankCardAdapter(mDataServer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 分割线
        // recyclerView.addItemDecoration(new DetailedActivity.MyDecoration());
        recyclerView.setAdapter(mbankCardAdapter);
    }

    private void initData() {
        Bank_Card_DataServer bank_card_dataServer=new Bank_Card_DataServer();
        bank_card_dataServer.setText("中国银行");
        bank_card_dataServer.setNumber("**** **** **** 1234");
        bank_card_dataServer.setPic(R.mipmap.bank);
        mDataServer.add(bank_card_dataServer);
    }
}
