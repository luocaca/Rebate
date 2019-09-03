package com.just.rebate.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.just.rebate.R;
import com.just.rebate.ui.MainActivity;

public class SignActivity extends AppCompatActivity {
    private Button mBtn_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        mBtn_sign=findViewById(R.id.btn_sign);
        mBtn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
