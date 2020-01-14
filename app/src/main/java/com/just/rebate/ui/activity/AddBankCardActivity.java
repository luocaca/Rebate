package com.just.rebate.ui.activity;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.BankNameAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.AddCardBean;
import com.just.rebate.entity.ResponseData;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

public class AddBankCardActivity extends BaseActivity {
    private List<AddCardBean> cardBeanList = new ArrayList<>();
    private List<ResponseData> responseDatas = new ArrayList<>();
    private int IsDefault;
    private int ReceivingBank;
    private MyApplication application;

    @BindView(R.id.Receiving_Account)
    EditText mEt_Receiviing_Account;

    @BindView(R.id.Receiving_Name)
    EditText mEt_Receiviing_Name;

    @BindView(R.id.Bank_Branch)
    EditText mEt_Bank_Branch;

    @BindView(R.id.Receiving_Bank)
    TextView mEt_Receiving_Bank;

    @BindView(R.id.IsDefault)
    Switch mWt_IsDefault;

    @BindView(R.id.send_Add_Card)
    Button mBtn_Send_Add_Card;

    @Override
    protected void requestData() {


    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initMakeBankData();
        initOnClick();

    }

    private void initMakeBankData() {
        AddCardBean cardBean = new AddCardBean();
        cardBean.setBankName("工商银行");
        cardBeanList.add(cardBean);
        AddCardBean cardBean1 = new AddCardBean();
        cardBean1.setBankName("农业银行");
        cardBeanList.add(cardBean1);
        AddCardBean cardBean2 = new AddCardBean();
        cardBean2.setBankName("招商银行");
        cardBeanList.add(cardBean2);
        AddCardBean cardBean3 = new AddCardBean();
        cardBean3.setBankName("建设银行");
        cardBeanList.add(cardBean3);

    }

    private void initOnClick() {
        mEt_Receiving_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDigLog();
            }

            private void initDigLog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddBankCardActivity.this);
                LayoutInflater inflater = LayoutInflater.from(AddBankCardActivity.this);
                View view = inflater.inflate(R.layout.diglog_bank_name, null);
                RecyclerView recyclerView = view.findViewById(R.id.rv_diglog);
                final AlertDialog dialog = builder.create();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddBankCardActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                BankNameAdapter adapter = new BankNameAdapter(AddBankCardActivity.this, cardBeanList);
                dialog.show();
                dialog.getWindow().setContentView(view);
                adapter.setOnItemClickListener(new BankNameAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mEt_Receiving_Bank.setText(cardBeanList.get(position).BankName);
                        dialog.dismiss();
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
        mWt_IsDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    IsDefault = 1;
                } else {
                    IsDefault = 0;
                }
            }
        });
        mBtn_Send_Add_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEt_Receiviing_Account.getText().toString().isEmpty()){
                    Toast.makeText(application, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_Receiviing_Name.getText().toString().isEmpty()){
                    Toast.makeText(application, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_Bank_Branch.getText().toString().isEmpty()){
                    Toast.makeText(application, "不能为空", Toast.LENGTH_SHORT).show();
                }else if(mEt_Receiving_Bank.getText().toString().isEmpty()){
                    Toast.makeText(application, "不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    SendBankCardData();
                }
            }
        });
    }

    private void SendBankCardData() {
        if (mEt_Receiving_Bank.getText().toString().equals("工商银行")) {
            ReceivingBank = 1;
        } else if (mEt_Receiving_Bank.getText().toString().equals("农业银行")) {
            ReceivingBank = 2;
        } else if (mEt_Receiving_Bank.getText().toString().equals("招商银行")) {
            ReceivingBank = 3;
        } else if (mEt_Receiving_Bank.getText().toString().equals("建设银行")) {
            ReceivingBank = 4;
        }

        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ReceivingType", 1);
            jsonObject.put("ReceivingBank", "" + ReceivingBank);
            jsonObject.put("IsDefault", "" + IsDefault);
            jsonObject.put("PayModeType", 1);
            jsonObject.put("BankBranch", mEt_Bank_Branch.getText().toString());
            jsonObject.put("ReceivingAccount", mEt_Receiviing_Account.getText().toString());
            jsonObject.put("ReceivingName", mEt_Receiviing_Name.getText().toString());
            jsonObject.put("ReceivingImg", "");
            jsonArray.put(jsonObject);
            OkHttpUtils.postString()
                    .content(jsonArray.toString())
                    .addHeader("Authorization", "Bearer " + application.getAuthorization())
                    .url("http://192.168.1.190:12004/api/Admin/PayMode/Create")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.i("onError", "onError: 添加银行卡" + e);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.i("onResponse", "onResponse: 添加银行卡" + response);
                            ResponseData responseData = GsonUtil.getGsonLower().fromJson(response, ResponseData.class);
                            responseDatas.clear();
                            responseDatas.add(responseData);
                            if (responseData.Type == 200) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddBankCardActivity.this);
                                builder.setMessage(responseData.Content);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        setResult(100);
                                        finish();
                                    }
                                });
                                builder.create();
                                builder.show();
                            } else {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddBankCardActivity.this);
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

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_add_bankcard;
    }
}
