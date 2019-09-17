package com.just.rebate.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;

public class OrderDetailsActivity extends AppCompatActivity {
    private ImageView mIv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        mIv_back=findViewById(R.id.back_orderdetails);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();onBackPressed();
            }
        });
    }
}
