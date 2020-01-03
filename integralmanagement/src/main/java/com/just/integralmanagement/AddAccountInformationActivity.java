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
import java.util.Random;

import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;


/**
 * 添加账号
 */

public class AddAccountInformationActivity extends AppCompatActivity {
    private EditText mEt_phone, mEt_PassWord, mTv_SecretKey;
    private TextView mEt_Account, mBtn_Random;
    private CheckBox mCb_Buy, mCb_Sell;
    private Switch mS_ClientType;
    private Button mBtn_Send_Account;
    private int Buy, Sell, UpStatus;
    private String RandomString = "";
    private String Authorization = "";
    private ApplicationClass applicationClass;
    private List<AddAccountBean> addAccountBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account_information);
        applicationClass= (ApplicationClass) getApplication();
        getSupportActionBar().setTitle("添加账号");
        setToolbars();
        mEt_phone = findViewById(R.id.phone);
        mEt_Account = findViewById(R.id.modify_account);
        mEt_PassWord = findViewById(R.id.PassWord);
        mTv_SecretKey = findViewById(R.id.SecretKey);
        mCb_Buy = findViewById(R.id.Buy);
        mCb_Sell = findViewById(R.id.Sell);
        mBtn_Random = findViewById(R.id.Btn_Random);
        mBtn_Random.setVisibility(View.VISIBLE);
        getRandomString();
        mS_ClientType = findViewById(R.id.ClientType);
        mBtn_Send_Account = findViewById(R.id.Send_Account);
        mBtn_Send_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_phone.getText().toString().isEmpty()) {
                    Toast.makeText(AddAccountInformationActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Account.getText().toString().isEmpty()) {
                    Toast.makeText(AddAccountInformationActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if (!mCb_Buy.isChecked() && !mCb_Sell.isChecked()) {
                    Toast.makeText(AddAccountInformationActivity.this, "买家卖家必须选择一个或多个", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        initData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mBtn_Random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RandomString = "";
                mTv_SecretKey.setText(RandomString);
                getRandomString();
            }
        });
    }

    private void getRandomString() {
        int i, n = 18;
        Random random = new Random();
        for (i = 1; i <= n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                RandomString += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                RandomString += String.valueOf(random.nextInt(10));
            }
        }
        mTv_SecretKey.setText(RandomString);
        Log.i("随机字符串", "getRandomString: " + RandomString);
    }


    private void initData() {
        Intent intent = getIntent();
        Authorization = intent.getStringExtra("Authorization");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Account", "" + mEt_Account.getText().toString());
        params.put("Password", "" + mEt_PassWord.getText().toString());
        params.put("UserId", "" + intent.getIntExtra("Id", 0));
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
                        Toast.makeText(AddAccountInformationActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                            ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                            if (errorDataBean.getType() == 403) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddAccountInformationActivity.this);
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
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddAccountInformationActivity.this);
                                builder1.setMessage("" + addAccountBeans.get(0).getMessage());
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setResult(80);
                                        finish();
                                    }
                                });
                                builder1.create();
                                builder1.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddAccountInformationActivity.this);
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
