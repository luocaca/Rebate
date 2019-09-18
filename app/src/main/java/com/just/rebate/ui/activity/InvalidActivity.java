package com.just.rebate.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;

public class InvalidActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImageInavalid1,mImageInavalid2,mImageInavalid3,mIv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid);
//        mImageInavalid1=findViewById(R.id.Invalid_to_Order);
//        mImageInavalid1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(InvalidActivity.this, MainActivity.class);
//                intent.putExtra("id",1);
//                startActivity(intent);
//            }
//        });
        mImageInavalid2=findViewById(R.id.Invalid_to_ArrivalAccount);
        mImageInavalid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InvalidActivity.this,ArrivalAccountActivity.class);
                startActivity(intent);
            }
        });

        mImageInavalid3=findViewById(R.id.Invalid_to_Track);
        mImageInavalid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InvalidActivity.this,TrackingProcessingActivity.class);
                startActivity(intent);
            }
        });


        mIv_back=findViewById(R.id.back_invalid);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()) {
//            case R.id.back_invalid:
//                finish();onBackPressed();
//                break;
            case R.id.Invalid_to_Track:
                intent = new Intent(this, TrackingProcessingActivity.class);
                startActivity(intent);
                break;
            case R.id.Invalid_to_ArrivalAccount:
                intent = new Intent(this, ArrivalAccountActivity.class);
                startActivity(intent);
                break;
//            case R.id.ArrivalAccount_to_Ivalid:
//                intent = new Intent(this, InvalidActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}
