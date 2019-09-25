package com.just.rebate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;

/**
 * title提现
 */
public class BalaceActivity extends AppCompatActivity {
    private Button mBtn_Cash;
    private ImageView mIv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);
        mIv_back=findViewById(R.id.back_Balace);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();onBackPressed();
            }
        });

        mBtn_Cash = findViewById(R.id.blance_to_cash);
        mBtn_Cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(BalaceActivity.this,CashWithdrawalActivity.class);
                startActivity(intent);
            }
        });
    }
}
