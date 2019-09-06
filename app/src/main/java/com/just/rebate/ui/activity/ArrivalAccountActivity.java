package com.just.rebate.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.ArrivalAccountAdapter;
import com.just.rebate.entity.ArrivalAccountItem;
import com.just.rebate.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArrivalAccountActivity extends AppCompatActivity {
    private List<ArrivalAccountItem> mDatas;
    private Context context;
    private RecyclerView mRecyclerview;
    private TextView mOrder_details;
    private ImageView mImageViewArrivalAccoount1, mImageViewArrivalAccoount2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_arrival_account);
        mRecyclerview = findViewById(R.id.rv_list5);
        mRecyclerview.setLayoutManager(new GridLayoutManager(this, 1));
        List<ArrivalAccountItem> arrivalAccountItems = new ArrayList<>();
        arrivalAccountItems.add(new ArrivalAccountItem(true, "淘宝"));
        arrivalAccountItems.add(new ArrivalAccountItem(false, "name"));
        arrivalAccountItems.add(new ArrivalAccountItem(true, "淘宝"));
        arrivalAccountItems.add(new ArrivalAccountItem(false, "name"));
        mDatas = arrivalAccountItems;
        mImageViewArrivalAccoount1 = findViewById(R.id.ArrivalAccount_to_Ivalid);
        mImageViewArrivalAccoount1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArrivalAccountActivity.this, InvalidActivity.class);
                startActivity(intent);
            }
        });
        mImageViewArrivalAccoount2 = findViewById(R.id.ArrivalAccount_to_Order);
       /* mImageViewArrivalAccoount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ArrivalAccountActivity.this, MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });*/
        ArrivalAccountAdapter arrivalAccountAdapter = new ArrivalAccountAdapter(R.layout.item_arrival_account_content, R.layout.item_arrival_account_head, mDatas) {

            @Override
            protected void convert(@NonNull BaseViewHolder helper, ArrivalAccountItem item) {
                helper.addOnClickListener(R.id.order_details);
            }

            @Override
            protected void convertHead(BaseViewHolder helper, ArrivalAccountItem arrivalAccountItem) {
                super.convertHead(helper, arrivalAccountItem);
                helper.setText(R.id.arrival_account_name, "淘宝");
                helper.setText(R.id.arrival_account_time, "2019-07-31接单成功");

            }
        };


        //bug
        arrivalAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ArrivalAccountActivity.this, OrderDetailsActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(arrivalAccountAdapter);

    }


}




