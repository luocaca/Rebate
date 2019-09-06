package com.just.rebate.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.just.rebate.R;
import com.just.rebate.ui.MainActivity;
import com.just.rebate.ui.fragment.OrderFragment;

public class InvalidActivity extends AppCompatActivity {
    private ImageView mImageInavalid1,mImageInavalid2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid);
        mImageInavalid1=findViewById(R.id.Invalid_to_Order);
       /* mImageInavalid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InvalidActivity.this,MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });*/
        mImageInavalid2=findViewById(R.id.Invalid_to_ArrivalAccount);
        mImageInavalid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InvalidActivity.this,ArrivalAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
