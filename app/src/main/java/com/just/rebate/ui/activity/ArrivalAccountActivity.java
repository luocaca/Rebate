package com.just.rebate.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.ArrivalAccountAdapter;
import com.just.rebate.entity.ArrivalAccountItem;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArrivalAccountActivity extends AppCompatActivity {
    private List<ArrivalAccountItem> mDatas;
    private Context context;
    private RecyclerView mRecyclerview;
    private TextView mOrder_details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_account);
        mRecyclerview=findViewById(R.id.rv_list5);

        mRecyclerview.setLayoutManager(new GridLayoutManager(this,1));
        List<ArrivalAccountItem> arrivalAccountItems=new ArrayList<>();
        arrivalAccountItems.add(new ArrivalAccountItem(true,"淘宝"));
        arrivalAccountItems.add(new ArrivalAccountItem(false,"name"));
        arrivalAccountItems.add(new ArrivalAccountItem(true,"淘宝"));
        arrivalAccountItems.add(new ArrivalAccountItem(false,"name"));
        mDatas=arrivalAccountItems;
        ArrivalAccountAdapter arrivalAccountAdapter=new ArrivalAccountAdapter(R.layout.item_arrival_account_content,R.layout.item_arrival_account_head,mDatas) {

            @Override
            protected void convert(@NonNull BaseViewHolder helper, ArrivalAccountItem item) {
                helper.addOnClickListener(R.id.order_details);
            }

            @Override
            protected void convertHead(BaseViewHolder helper, ArrivalAccountItem arrivalAccountItem) {
                super.convertHead(helper, arrivalAccountItem);
                helper.setText(R.id.arrival_account_name,"淘宝");
                helper.setText(R.id.arrival_account_time,"2019-07-31接单成功");

            }
        };
        //bug
        arrivalAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent =new Intent(ArrivalAccountActivity.this,OrderDetailsActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(arrivalAccountAdapter);

    }


}




