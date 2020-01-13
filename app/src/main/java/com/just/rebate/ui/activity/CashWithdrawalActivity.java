package com.just.rebate.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.AlertDigLog_Card_ChooseAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.data.Bank_Card_DataServer;
import com.just.rebate.entity.PaymentData;
import com.just.rebate.entity.ResponseData;
import com.just.rebate.wedget.MyEditText;
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
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * title 提现操作
 */


public class CashWithdrawalActivity extends BaseActivity implements View.OnClickListener {

    private MyApplication application;
    private String BlanceMoeny;
    private List<Bank_Card_DataServer.DataBean> dataBeans = new ArrayList<>();
    private List<ResponseData> responseDatas=new ArrayList<>();
    private  List<PaymentData.DataBean> paymentDatas=new ArrayList<>();
    private int PayMode;
    private int ReceviePayModeId;

    @BindView(R.id.text_bank)
    TextView mTv_BankData;

    @BindView(R.id.NowBlanceMoeny)
    TextView mTv_NowBlanceMoeny;

    @BindView(R.id.Cash_Moeny)
    MyEditText mEt_Cash_Moeny;


    @OnClick({R.id.dialog_cashwwithdrawal, R.id.ALLCash,R.id.text_bank})
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_cashwwithdrawal) {
            SendCashData();
        } else if (view.getId() == R.id.ALLCash) {
            mEt_Cash_Moeny.setText(BlanceMoeny);
        } else if (view.getId() == R.id.text_bank) {
            initDiglog();
        }

    }

    private void SendCashData() {
        /**
         * 这是密码输入键盘
         */
//            Bundle bundle = new Bundle();
//            PayFragment fragment = new PayFragment();
//            fragment.setArguments(bundle);
//            fragment.show(getSupportFragmentManager(), "Pay");
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("OrderType", "" + 3);
            jsonObject.put("Amount", "" + mEt_Cash_Moeny.getMoneyText().toString());
            jsonObject.put("RealAmount", "" + mEt_Cash_Moeny.getMoneyText().toString());
            jsonObject.put("PayModeId", "" + ReceviePayModeId);
            jsonObject.put("ReceivePayModeId", "" + PayMode);
            jsonObject.put("Remark", "" );
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
                                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CashWithdrawalActivity.this);
                                builder.setTitle(responseData.Content);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setResult(175);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                androidx.appcompat.app.AlertDialog.Builder builder1 = new androidx.appcompat.app.AlertDialog.Builder(CashWithdrawalActivity.this);
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

    private void initDiglog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(CashWithdrawalActivity.this);
        LayoutInflater inflater = LayoutInflater.from(CashWithdrawalActivity.this);
        View view1 = inflater.inflate(R.layout.alertdiglog_bankcaard_choose, null);
        RecyclerView mRv = view1.findViewById(R.id.mRV_Card_Choose);
        final AlertDialog dialog = builder1.create();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CashWithdrawalActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);
        AlertDigLog_Card_ChooseAdapter adapter = new AlertDigLog_Card_ChooseAdapter(dataBeans, CashWithdrawalActivity.this);
        dialog.show();
        dialog.getWindow().setContentView(view1);
        adapter.setOnItemClickListener(new AlertDigLog_Card_ChooseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PayMode = dataBeans.get(position).Id;
                mTv_BankData.setText(dataBeans.get(position).ReceivingAccount + "  (" + dataBeans.get(position).ReceivingBankName + ")");
                dialog.dismiss();
            }
        });
        mRv.setAdapter(adapter);
    }


    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void requestData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + 1);
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
                        Log.i("onError", "onError: 银行卡列表" + e);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 银行卡列表" + response);
                        Bank_Card_DataServer bankcard = GsonUtil.getGsonLower().fromJson(response, Bank_Card_DataServer.class);
                        dataBeans.clear();
                        dataBeans.addAll(bankcard.Data);
                        for (int i = 0; i <= dataBeans.size() - 1; i++) {
                            if (dataBeans.get(i).IsDefault == 1) {
                                PayMode = dataBeans.get(i).Id;
                                mTv_BankData.setText(dataBeans.get(i).ReceivingAccount + "  (" + dataBeans.get(i).ReceivingBankName + ")");
                            }
                        }
                    }
                });
    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initReceiveData();
        initReceiveBankCardData();
    }

    private void initReceiveBankCardData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + 1);
        params.put("PayModeType", "" + 2);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeByApp?receivingType=1")
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
                        if (paymentData != null) {
                            paymentDatas.clear();
                            paymentDatas.addAll(paymentData.Data);
                            if (paymentData.ResultType == 3) {

                                ReceviePayModeId = paymentDatas.get(0).Id;
                            } else {
                                Toast.makeText(application, "数据加载错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(application, "数据请求错误", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void initReceiveData() {
        Intent intent = getIntent();
        BlanceMoeny = intent.getStringExtra("BlanceMoeny");
        mTv_NowBlanceMoeny.setText("当前可返利余额为" + BlanceMoeny + "元");
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_cash_withdrawal;
    }

}
