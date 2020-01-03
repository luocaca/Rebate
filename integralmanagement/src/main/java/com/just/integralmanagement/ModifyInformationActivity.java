package com.just.integralmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.AddAccountBean;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * 修改账号信息
 */

public class ModifyInformationActivity extends AppCompatActivity {
    private EditText mEt_phone, mEt_PassWord;
    private TextView mTv_SecretKey, mTv_ClickRandom,mEt_Account;
    private CheckBox mCb_Buy, mCb_Sell;
    private Switch mS_ClientType;
    private Button mBtn_Send_Account;
    private int Buy, Sell, UpStatus;
    private List<AddAccountBean> addAccountBeans = new ArrayList<>();
    private String Authorization = "";
    private ApplicationClass applicationClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_account);
        applicationClass= (ApplicationClass) getApplication();
        getSupportActionBar().setTitle("修改账号");
        setToolbars();
        mEt_phone = findViewById(R.id.phone);
        mEt_Account = findViewById(R.id.account);
        mEt_PassWord = findViewById(R.id.PassWord);
        mTv_SecretKey = findViewById(R.id.SecretKey);
        mCb_Buy = findViewById(R.id.Buy);
        mCb_Sell = findViewById(R.id.Sell);
        mS_ClientType = findViewById(R.id.ClientType);
        mBtn_Send_Account = findViewById(R.id.Send_Account);
        mTv_ClickRandom = findViewById(R.id.Btn_Random);
        mTv_ClickRandom.setVisibility(View.GONE);
        initReceiveData();
        mBtn_Send_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_phone.getText().toString().isEmpty()) {
                    Toast.makeText(ModifyInformationActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Account.getText().toString().isEmpty()) {
                    Toast.makeText(ModifyInformationActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if (!mCb_Buy.isChecked() && !mCb_Sell.isChecked()) {
                    Toast.makeText(ModifyInformationActivity.this, "买家卖家必须选择一个或多个", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        initData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initReceiveData() {
        Intent intent = getIntent();
        mEt_Account.setText(intent.getStringExtra("Account"));
        mEt_phone.setText(intent.getStringExtra("Phone"));
        mEt_PassWord.setText(intent.getStringExtra("PassWord"));
        mTv_SecretKey.setText(intent.getStringExtra("Secret"));
        Authorization = intent.getStringExtra("authorization");
        if(intent.getStringExtra("BuyOrSeller").equals("0")){
            mCb_Buy.setChecked(true);
            mCb_Sell.setChecked(false);
        }else if(intent.getStringExtra("BuyOrSeller").equals("1")){
            mCb_Sell.setChecked(true);
            mCb_Buy.setChecked(false);
        }else if(intent.getStringExtra("BuyOrSeller").equals("0,1")){
            mCb_Sell.setChecked(true);
            mCb_Buy.setChecked(true);
        }
        if (intent.getIntExtra("UpStatus", 0) == 0) {
            mS_ClientType.setChecked(true);
        } else {
            mS_ClientType.setChecked(false);
        }

    }

    private void initData() {
        Intent intent = getIntent();
        Authorization = intent.getStringExtra("Authorization");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Account", "" + mEt_Account.getText().toString());
        params.put("Password", "" + mEt_PassWord.getText().toString());
        params.put("UserId", "" + intent.getIntExtra("UserId", 0));
        params.put("Id", "" + intent.getIntExtra("Id", 0));
        params.put("Secret", "" + mTv_SecretKey.getText().toString());
        params.put("Phone", "" + mEt_phone.getText().toString());
        params.put("AppType", "" + 0);
        params.put("ClientType", "" + 0);
        if (mCb_Buy.isChecked() && !mCb_Sell.isChecked()) {
            Buy = 0;
            params.put("BuyOrSeller", Buy + "");
        } else if (!mCb_Buy.isChecked() && mCb_Sell.isChecked()) {
            Sell = 1;
            params.put("BuyOrSeller", Sell + "");
        } else if (mCb_Buy.isChecked() && mCb_Sell.isChecked()) {
            Buy = 0;
            Sell = 1;
            params.put("BuyOrSeller", Buy + "," + Sell);
        }
        if (mS_ClientType.isChecked()) {
            UpStatus = 0;
        } else {
            UpStatus = 1;
        }
        params.put("UpStatus", "" + UpStatus);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://"+applicationClass.getHost()+"/api/Admin/TaskUserAccount/AddTaskUserAccountByApp")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
                        Toast.makeText(ModifyInformationActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                            ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                            if (errorDataBean.getType() == 403) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ModifyInformationActivity.this);
                                builder1.setMessage("" + errorDataBean.getContent());
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create();
                                builder1.show();
                        } else {
                            Type t = new TypeToken<AddAccountBean>() {
                            }.getType();
                            AddAccountBean addAccountBean = GsonUtil.getGsonLower().fromJson(response, t);
                            addAccountBeans.clear();
                            addAccountBeans.add(addAccountBean);
                            if (addAccountBeans.get(0).getResultType() == 3) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ModifyInformationActivity.this);
                                builder1.setMessage("" + addAccountBeans.get(0).getMessage());
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setResult(100);
                                        finish();
                                    }
                                });
                                builder1.create();
                                builder1.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(ModifyInformationActivity.this);
                                builder1.setMessage("" + addAccountBeans.get(0).getMessage());
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create();
                                builder1.show();
                            }
                        }
                    }
                });

    }


    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);//这两句就可以让actionBar的图标可以响应点击事件
        mActionBar.setDisplayHomeAsUpEnabled(true);//这一句主要用于后面返回效果，后面会讲
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //id不要写错，前面要加android
                onBackPressed();
                break;
        }
        return true;
    }
}
