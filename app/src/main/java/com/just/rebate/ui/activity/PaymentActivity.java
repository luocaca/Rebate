package com.just.rebate.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.OrderAdapter;
import com.just.rebate.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private List<OrderItem> mDatas;
    private RecyclerView recycleView;
    private TextView mAlertDiglog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        recycleView=findViewById(R.id.rv_list4);
        mAlertDiglog=findViewById(R.id.AlertDiglog);
        recycleView.setLayoutManager(new GridLayoutManager(PaymentActivity.this, 1));
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(true, "淘宝"));
        orderItems.add(new OrderItem(false, "name2"));
        mDatas=orderItems;


        mAlertDiglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDiglog();
            }
        });


        OrderAdapter orderAdapter = new OrderAdapter(R.layout.item_order_content, R.layout.item_order_head, mDatas) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, OrderItem item) {

            }

            @Override
            protected void convertHead(BaseViewHolder helper, OrderItem item) {
                super.convertHead(helper, item);
//                View view1=layoutInflater.inflate(R.layout.def_section_head,null);
                helper.setText(R.id.order_header, "淘宝");
                helper.setText(R.id.order_tv, "米拉服饰");

            }
        };
        recycleView.setAdapter(orderAdapter);
    }
    private void AlertDiglog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(PaymentActivity.this);
        LayoutInflater inflater=LayoutInflater.from(PaymentActivity.this);
        View view=inflater.inflate(R.layout.alert_diglog_layout,null);
        Button mBtn1=view.findViewById(R.id.button1);
        Button mBtn2=view.findViewById(R.id.button2);
        final AlertDialog dialog=builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this, "付款布局", Toast.LENGTH_SHORT).show();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}
