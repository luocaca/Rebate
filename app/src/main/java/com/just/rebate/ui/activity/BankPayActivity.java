package com.just.rebate.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.just.rebate.data.Bank_Card_DataServer;
import com.just.rebate.entity.PaymentData;
import com.just.rebate.entity.RechargeIntegralData1;
import com.just.rebate.entity.ResponseData;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

public class BankPayActivity extends BaseActivity {

    @BindView(R.id.ReceivingBank)
    TextView mTv_ReceivingBank;

    @BindView(R.id.ReceivingAccount)
    TextView mTv_ReceivingAccount;

    @BindView(R.id.BankBranch)
    TextView mTv_BankBranch;

    @BindView(R.id.DisPlayName)
    TextView mTv_DisPlayName;

    @BindView(R.id.RechargeMoeny)
    TextView mTv_RechargeMoeny;

    @BindView(R.id.PayName)
    EditText mEt_PayName;

    @BindView(R.id.Remarks)
    EditText mEt_Remarks;

    @BindView(R.id.CopyAccount)
    TextView mTv_CopyAccount;

    @BindView(R.id.CopyDisPlayName)
    TextView mTv_Copy_DisPlayName;

    @BindView(R.id.ConfirmRecharge)
    Button mBtn_ConfirmRecharge;

    private int ReceivingType;
    private List<RechargeIntegralData1> rechargeIntegralData1s = new ArrayList<>();
    private List<Bank_Card_DataServer.DataBean> dataBeans = new ArrayList<>();
    private MyApplication application;
    private String IntegralNum = "";
    private String Authorization = "";
    private int PayMode;
    private int ReceviePayModeId;
    private List<ResponseData> responseDatas = new ArrayList<>();
    private List<PaymentData.DataBean> paymentDatas = new ArrayList<>();

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initRecevierData();
        try {
            initPayCardData();
            RecevierCardData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initOnClick();
    }

    private void initPayCardData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + ReceivingType);
        params.put("PayModeType", "" + 1);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeListByApp?receivingType=1")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Bank_Card_DataServer bankcard = GsonUtil.getGsonLower().fromJson(response, Bank_Card_DataServer.class);
                        dataBeans.clear();
                        dataBeans.addAll(bankcard.Data);
                        for (int i = 0; i <= dataBeans.size() - 1; i++) {
                            if (dataBeans.get(i).IsDefault == 1) {
                                PayMode = dataBeans.get(i).Id;
                                mTv_ReceivingBank.setText(dataBeans.get(i).ReceivingAccount);
                            }
                        }
                    }
                });
    }

    private void RecevierCardData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + ReceivingType);
        params.put("PayModeType", "" + 2);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeByApp?receivingType" + "=" + ReceivingType)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 收款方银行卡信息" + response);
                        PaymentData paymentData = GsonUtil.getGsonLower().fromJson(response, PaymentData.class);
                        if (paymentData.Data!= null) {
                            paymentDatas.clear();
                            paymentDatas.addAll(paymentData.Data);
                            if (paymentData.ResultType == 3) {
                                mTv_ReceivingAccount.setText(paymentDatas.get(0).ReceivingAccount);
                                mTv_DisPlayName.setText(paymentDatas.get(0).ReceivingName);
                                mTv_BankBranch.setText(paymentDatas.get(0).BankBranch);
                                ReceviePayModeId = paymentDatas.get(0).Id;
                            } else {
                                Toast.makeText(application, "数据加载错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(application, "未设置默认银行卡，请设置", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initRecevierData() {
        Intent intent = getIntent();
        ReceivingType = intent.getIntExtra("ReceivingType", 1);
        Authorization = intent.getStringExtra("Authorization");
        mTv_RechargeMoeny.setText(intent.getStringExtra("IntegralNum"));
        Log.i("initRecevierData", "initRecevierData: " + intent.getStringExtra("IntegralNum"));
        PayMode = intent.getIntExtra("PayMode", 1);
    }

    private void initData() {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("OrderType", "" + 1);
            jsonObject.put("Amount", "" + mTv_RechargeMoeny.getText().toString());
            jsonObject.put("RealAmount", "" + mTv_RechargeMoeny.getText().toString());
            jsonObject.put("PayModeId", "" + PayMode);
            jsonObject.put("ReceivePayModeId", "" + ReceviePayModeId);
            Log.i("ReceivePayModeId", "initData: " + ReceviePayModeId + PayMode);
            jsonObject.put("Remark", "" + mEt_Remarks.getText().toString());
            jsonArray.put(jsonObject);
            OkHttpUtils.postString()
                    .content(jsonArray.toString())
                    .addHeader("Authorization", "Bearer " + application.getAuthorization())
                    .url("http://192.168.1.190:12004/api/Admin/OrderRechargePay/Create")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i("积分信息错误日志", "onError: " + e);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("积分信息日志", "onResponse: " + response);
                            Type t = new TypeToken<ResponseData>() {
                            }.getType();
                            ResponseData responseData = GsonUtil.getGsonLower().fromJson(response, t);
                            responseDatas.clear();
                            responseDatas.add(responseData);
                            if (responseData.Type == 200) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(BankPayActivity.this);
                                builder.setTitle(responseData.Content);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(BankPayActivity.this);
                                builder1.setMessage(responseData.Content);
                                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create();
                                builder1.show();
                            }
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initOnClick() {
        mBtn_ConfirmRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_PayName.getText().toString().isEmpty()) {
                    Toast.makeText(application, "付款人姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (mEt_Remarks.getText().toString().isEmpty()) {
                    Toast.makeText(application, "备注也需要填写哦", Toast.LENGTH_SHORT).show();
                } else if (mEt_PayName.getText().toString().isEmpty() && mEt_Remarks.getText().toString().isEmpty()) {
                    Toast.makeText(application, "您还有一些信息未填写", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        initData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_bank_pay;
    }
}
