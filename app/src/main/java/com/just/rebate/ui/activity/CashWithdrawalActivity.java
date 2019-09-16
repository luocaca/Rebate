package com.just.rebate.ui.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;

public class CashWithdrawalActivity extends AppCompatActivity {
    private Button Btn_Dialog;
    private LinearLayout pwdEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        Btn_Dialog=findViewById(R.id.dialog_cashwwithdrawal);
        Btn_Dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog();
            }
        });


    }
    private void AlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(CashWithdrawalActivity.this);
        LayoutInflater inflater=LayoutInflater.from(CashWithdrawalActivity.this);
        View view =inflater.inflate(R.layout.dialog_cash_with_drawal,null);
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setContentView(view);
        pwdEditText=view.findViewById(R.id.pwdEditText);
        pwdEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CashWithdrawalActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });






    }
}
