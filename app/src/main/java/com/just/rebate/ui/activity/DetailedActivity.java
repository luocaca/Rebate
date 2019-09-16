package com.just.rebate.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.DetailsAdapter;
import com.just.rebate.data.detailed_DataServer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailedActivity extends AppCompatActivity {

    @BindView(R.id.rv_list7)
    RecyclerView mrecyclerView;

    @BindView(R.id.back_Detaile)
    ImageView mIv_back;

    private List<detailed_DataServer> mDataServer=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        initRecyclerview();
        initData();
        initonClick();
    }

    private void initonClick() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();onBackPressed();
            }
        });
    }


    private void initRecyclerview() {
        mrecyclerView=findViewById(R.id.rv_list7);
        DetailsAdapter mDetailsAdapter=new DetailsAdapter(mDataServer);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecyclerView.addItemDecoration(new DetailedActivity.MyDecoration());
        mrecyclerView.setAdapter(mDetailsAdapter);
    }

    private void initData() {
        detailed_DataServer detailed_dataServer=new detailed_DataServer();
        detailed_dataServer.setText("1.2018年7月31日邀请手机尾号3215的用户下单成功，赚取奖励10元。");
        mDataServer.add(detailed_dataServer);

        detailed_DataServer detailed_dataServer1=new detailed_DataServer();
        detailed_dataServer1.setText("1.2018年8月1日邀请手机尾号6624的用户下单成功，赚取奖励10元。");
        mDataServer.add(detailed_dataServer1);

        detailed_DataServer detailed_dataServer2=new detailed_DataServer();
        detailed_dataServer2.setText("1.2018年8月2日手机尾号为3215的邀请手机尾号1152的用户下单成功，赚取奖励2元。");
        mDataServer.add(detailed_dataServer2);


    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dibufengex));

        }
    }
}
