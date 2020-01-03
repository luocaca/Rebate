package com.just.rebate.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;
import com.just.rebate.entity.ResgsterDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

public class PassWordRegisterActivity extends AppCompatActivity {
    private ImageView mIv_yzm;
    private Button mBtn_register;
    private String YZMCODE, YZMIMG;
    private TextView mTv_RefershYZM;
    private EditText mEt_Account, mEt_PassWord, mEt_PassWordF, mEt_Phone, mEt_Email, mEt_YZM;
    private List<ResgsterDataBean> registerDataBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_register);
        mBtn_register = findViewById(R.id.password_register);
        mEt_Account = findViewById(R.id.Account_Register);
        mEt_PassWord = findViewById(R.id.PassWord_Register);
        mEt_PassWordF = findViewById(R.id.PassWordF_Register);
        mEt_Email = findViewById(R.id.Email_Register);
        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    initData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("UserName", mEt_Account.getText().toString());
        params.put("Email", mEt_Email.getText().toString());
        params.put("Password", mEt_PassWord.getText().toString());
        params.put("ConfirmPassword", mEt_PassWordF.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://192.168.1.137:7001/api/Identity/RegisterNoVerifyCode")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: " + response);
                        ResgsterDataBean registerDataBean = GsonUtil.getGsonLower().fromJson(response, ResgsterDataBean.class);
                        registerDataBeans.clear();
                        registerDataBeans.add(registerDataBean);
                        if (registerDataBeans.get(0).Type == 200) {
                            Intent intent = new Intent(PassWordRegisterActivity.this, PassWordSignActivity.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PassWordRegisterActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage(registerDataBeans.get(0).Content);
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            builder.create();
                            builder.show();
                        }
                    }
                });
    }


}
