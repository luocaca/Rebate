package com.just.rebate.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;
import com.just.rebate.ui.fragment.PayFragment;
import com.just.rebate.wedget.webview.PwdEditText;

public class CashWithdrawalActivity extends AppCompatActivity {
    private Button Btn_Dialog;
    private LinearLayout linearLayout_dialog;
    private PwdEditText pwdEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        Btn_Dialog=findViewById(R.id.dialog_cashwwithdrawal);
        Btn_Dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                PayFragment fragment=new PayFragment();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "Pay");
            }
        });


    }

}
