package com.just.integralmanagement;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.PaymentData;
import com.just.integralmanagement.entity.RechargeIntegralData1;
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

public class BankPayActivity extends AppCompatActivity {

    private TextView mTv_ReceivingBank, mTv_ReceivingAccount, mTv_BankBranch, mTv_DisPlayName, mTv_RechargeMoeny;
    private EditText mEt_PayName, mEt_Remarks;
    private TextView mTv_CopyAccount, mTv_Copy_DisPlayName;
    private Button mBtn_ConfirmRecharge;
    private int ReceivingType;
    private List<RechargeIntegralData1> rechargeIntegralData1s = new ArrayList<>();
    private String IntegralNum = "";
    private String Authorization = "";
    private ApplicationClass applicationClass;
    private List<PaymentData> paymentDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_pay);
        applicationClass = (ApplicationClass) getApplication();
        mTv_ReceivingBank = findViewById(R.id.ReceivingBank);
        mTv_ReceivingAccount = findViewById(R.id.ReceivingAccount);
        mTv_BankBranch = findViewById(R.id.BankBranch);
        mTv_DisPlayName = findViewById(R.id.DisPlayName);
        mTv_RechargeMoeny = findViewById(R.id.RechargeMoeny);
        mEt_PayName = findViewById(R.id.PayName);
        mEt_Remarks = findViewById(R.id.Remarks);
        mTv_CopyAccount = findViewById(R.id.CopyAccount);
        mTv_Copy_DisPlayName = findViewById(R.id.CopyDisPlayName);
        mBtn_ConfirmRecharge = findViewById(R.id.ConfirmRecharge);
        initReceiveData();
        getSupportActionBar().setTitle("银行卡充值");
        setToolbars();
        initData();
        mBtn_ConfirmRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_PayName.getText().toString().isEmpty()) {
                    Toast.makeText(applicationClass, "付款人姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Remarks.getText().toString().isEmpty()) {
                    Toast.makeText(applicationClass, "备注也需要填写哦", Toast.LENGTH_SHORT).show();
                } else if (mEt_PayName.getText().toString().isEmpty() && mEt_Remarks.getText().toString().isEmpty()) {
                    Toast.makeText(applicationClass, "您还有一些信息未填写", Toast.LENGTH_SHORT).show();
                } else {
                    initSendRechargeData();
                }

            }
        });
        mTv_CopyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) BankPayActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, "" + mTv_ReceivingAccount.getText().toString()));
                if (clipboardManager.hasPrimaryClip()) {
                    clipboardManager.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(BankPayActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        mTv_Copy_DisPlayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) BankPayActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, "" + mTv_DisPlayName.getText().toString()));
                if (clipboardManager.hasPrimaryClip()) {
                    clipboardManager.getPrimaryClip().getItemAt(0).getText();
                }
                Toast.makeText(BankPayActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSendRechargeData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("AppType", "" + 0);
        params.put("IntegralNum", "" + mTv_RechargeMoeny.getText().toString());
        params.put("TaskPayModeId", "" + paymentDatas.get(0).Data.get(0).Id);
        params.put("IsState", "" + 0);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskIntegralDetail/SetTaskIntegralDetail")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("积分信息错误日志", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                        if (errorDataBean.getType() == 403) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(BankPayActivity.this);
                            builder1.setMessage("" + errorDataBean.getContent());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.create();
                            builder1.show();
                        } else {
                            Log.i("积分信息日志", "onResponse: " + response);
                            Type t = new TypeToken<RechargeIntegralData1>() {
                            }.getType();
                            RechargeIntegralData1 rechargeIntegralData1 = GsonUtil.getGsonLower().fromJson(response, t);
                            rechargeIntegralData1s.clear();
                            rechargeIntegralData1s.add(rechargeIntegralData1);
                            if (rechargeIntegralData1s.get(0).getResultType() == 3) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BankPayActivity.this);
                                builder.setTitle("提交成功,充值结果请咨询上级");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setResult(150);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(BankPayActivity.this);
                                builder1.setMessage("提交成功,请稍后重试");
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

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskPayMode/GetTaskPayModeByApp?ReceivingType" + "=" + ReceivingType)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<PaymentData>() {
                        }.getType();
                        PaymentData paymentData = GsonUtil.getGsonLower().fromJson(response, t);
                        if (paymentData != null) {
                            paymentDatas.clear();
                            paymentDatas.add(paymentData);
                            if (paymentDatas.get(0).ResultType == 3) {
                                mTv_ReceivingBank.setText(paymentData.Data.get(0).ReceivingBankName);
                                mTv_ReceivingAccount.setText(paymentData.Data.get(0).ReceivingAccount);
                                mTv_DisPlayName.setText(paymentData.Data.get(0).ReceivingName);
                                mTv_BankBranch.setText(paymentData.Data.get(0).BankBranch);
                            } else {
                                Toast.makeText(applicationClass, "数据加载错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(applicationClass, "数据请求错误", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initReceiveData() {
        Intent intent = getIntent();
        ReceivingType = intent.getIntExtra("ReceivingType", 1);
        Authorization = intent.getStringExtra("Authorization");
        IntegralNum = intent.getStringExtra("IntegralNum");
        mTv_RechargeMoeny.setText(IntegralNum);
    }
}
