package com.just.rebate.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.AlertDigLog_Card_ChooseAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.data.Bank_Card_DataServer;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * title 充值界面
 */

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.id_flowlayout)
    TagFlowLayout tagFlowLayout;

    @BindView(R.id.jiantou)
    ImageView imageView;

    @BindView(R.id.linear)
    LinearLayout linear;

    @BindView(R.id.radio_group)
    LinearLayout linearLayout;

    @BindView(R.id.Recharge)
    Button mBtn_Recharge;

    @BindView(R.id.radio)
    RadioGroup radioGroup;

    @BindView(R.id.radio1)
    RadioButton mRb_YHK;

    @BindView(R.id.radio2)
    RadioButton mRb_ZFB;

    @BindView(R.id.radio3)
    RadioButton mRb_VX;

    @BindView(R.id.CardChoose)
    LinearLayout mCardChoose;

    @BindView(R.id.Text_for_Bankcard)
    TextView mTv_BankCard;


    private View currentSelectView;
    private int currentSelectPosition = -1;
    private EditText et;
    private MyApplication application;
    private int moeny;
    private int ReceivingType;
    private String Integral;
    private String Account;
    private int PayMode;
    private String AtineNum;
    private TagAdapter tagAdapter;
    private boolean isVisible = true;
    private List<Bank_Card_DataServer.DataBean> dataBeans=new ArrayList<>();


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initBankCardData();
        initViewsAndEvents();
        initTagFlowLayout();
        if (mRb_YHK.isChecked()) {
            mCardChoose.setVisibility(View.VISIBLE);
            mCardChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RechargeActivity.this);
                    LayoutInflater inflater = LayoutInflater.from(RechargeActivity.this);
                    View view1 = inflater.inflate(R.layout.alertdiglog_bankcaard_choose, null);
                    RecyclerView mRv = view1.findViewById(R.id.mRV_Card_Choose);
                    final AlertDialog dialog = builder1.create();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RechargeActivity.this);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    mRv.setLayoutManager(linearLayoutManager);
                    AlertDigLog_Card_ChooseAdapter adapter = new AlertDigLog_Card_ChooseAdapter(dataBeans, RechargeActivity.this);
                    dialog.show();
                    dialog.getWindow().setContentView(view1);
                    adapter.setOnItemClickListener(new AlertDigLog_Card_ChooseAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            PayMode = dataBeans.get(position).Id;
                            mTv_BankCard.setText(dataBeans.get(position).ReceivingAccount + "  (" + dataBeans.get(position).ReceivingBankName + ")");
                            dialog.dismiss();
                        }
                    });
                    mRv.setAdapter(adapter);
                }
            });
        } else {
            mCardChoose.setVisibility(View.GONE);
        }
        initonClick();
    }

    private void initBankCardData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType",""+1);
        params.put("PayModeType",""+1);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " +application.getAuthorization())
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeListByApp?receivingType=1")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 银行卡列表"+e);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 银行卡列表"+response);
                        Bank_Card_DataServer bankcard= GsonUtil.getGsonLower().fromJson(response,Bank_Card_DataServer.class);
                        dataBeans.clear();
                        dataBeans.addAll(bankcard.Data);
                        for(int i=0;i<=dataBeans.size()-1;i++){
                            if(dataBeans.get(i).IsDefault==1){
                                PayMode = dataBeans.get(i).Id;
                                mTv_BankCard.setText(dataBeans.get(i).ReceivingAccount + "  (" + dataBeans.get(i).ReceivingBankName + ")");
                            }
                        }
                    }
                });
    }

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    private void initonClick() {

        mBtn_Recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moeny == 0 && et != null) {
                    Integral = et.getText().toString();
                } else {
                    Integral = moeny + "";
                }
                Log.i("IntegralNum", "initData: " + Integral);
                AlertDialog.Builder builder = new AlertDialog.Builder(RechargeActivity.this);
                builder.setMessage("请确认充值账号");
                builder.setPositiveButton("确认无误", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mRb_YHK.isChecked()) {
                            ReceivingType = 1;
                            Intent intent = new Intent(RechargeActivity.this, BankPayActivity.class);
                            intent.putExtra("Authorization", application.getAuthorization());
                            intent.putExtra("IntegralNum", Integral + "");
                            intent.putExtra("ReceivingType", ReceivingType);
                            intent.putExtra("PayMode", PayMode);
                            startActivity(intent);
                        } else if (mRb_VX.isChecked()) {
                            ReceivingType = 0;
                            Intent intent = new Intent(RechargeActivity.this, VxOrZfbPayActivity.class);
                            intent.putExtra("Authorization", application.getAuthorization());
                            intent.putExtra("IntegralNum", Integral + "");
                            intent.putExtra("ReceivingType", ReceivingType);
                            startActivity(intent);
                        } else if (mRb_ZFB.isChecked()) {
                            ReceivingType = 2;
                            Intent intent = new Intent(RechargeActivity.this, VxOrZfbPayActivity.class);
                            intent.putExtra("Authorization", application.getAuthorization());
                            intent.putExtra("IntegralNum", Integral + "");
                            intent.putExtra("ReceivingType", ReceivingType);
                            startActivity(intent);
                        } else {
                            Toast.makeText(mActivity, "请选择正确的支付方式", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });

    }


    protected void initViewsAndEvents() {

        //linearLayout.setVerticalGravity();
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible) {
                    //显示布局
                    isVisible = false;
                    linearLayout.setVisibility(view.VISIBLE);
                } else {
                    linearLayout.setVisibility(view.GONE);
                    isVisible = true;
                }
            }
        });
        radioGroup.check(R.id.radio1);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_recharge;
    }


    private void initTagFlowLayout() {


        List<String> list = new ArrayList();
        list.add("100");
        list.add("200");
        list.add("300");
        list.add("500");
        list.add("1000");
        list.add("0");


        tagFlowLayout.post(new Runnable() {
            @Override
            public void run() {

                //set adapter

                tagAdapter = new TagAdapter(list) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {


                        if (TextUtils.equals(o.toString(), "0")) {
                            et = (EditText) View.inflate(RechargeActivity.this, R.layout.et, null);
                            //getResources().getDisplayMetrics().widthPixels
                            int pad = (int) (parent.getWidth() * 0.06);
                            et.setWidth((parent.getWidth() / 4));

                            et.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                        ToastUtils.showToastLong("被点击了");
                                    currentSelectPosition = position;
                                    try {
                                        tagAdapter.unSelected(currentSelectPosition, (TagView) currentSelectView.getParent());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    et.setBackground(getResources().getDrawable(R.drawable.tv_un_nomarl));
                                    et.setTextColor(Color.WHITE);
                                    et.setHintTextColor(Color.WHITE);

                                }
                            });
                            et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                @Override
                                public void onFocusChange(View v, boolean hasFocus) {
                                    if (hasFocus) {

                                        try {
                                            currentSelectPosition = position;
                                            et.setBackground(getResources().getDrawable(R.drawable.tv_un_nomarl));
                                            try {
                                                tagAdapter.unSelected(currentSelectPosition, (TagView) currentSelectView.getParent());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            et.setTextColor(Color.WHITE);
                                            et.setHintTextColor(Color.WHITE);

                                        } catch (Exception e) {

                                        }

                                    }
                                }
                            });
                            return et;
                        } else {
                            TextView tv = (TextView) View.inflate(RechargeActivity.this, R.layout.tv, null);
                            tv.setText(o.toString() + "元");
//                              tv.setTextColor(R.drawable.pay_text_selector);
                            //getResources().getDisplayMetrics().widthPixels
                            int pad = (int) (parent.getWidth() * 0.06);
                            tv.setWidth((parent.getWidth() / 4));
                            return tv;
                        }

                    }


                    @Override
                    public void onSelected(int position, View view) {
                        super.onSelected(position, view);
                        Log.i("onSelected", "position: " + position);
                        if (et != null) {
                            et.setBackground(getResources().getDrawable(R.drawable.tv_nomarl));
                            et.setTextColor(getResources().getColor(R.color.text999));
                            et.setHintTextColor(getResources().getColor(R.color.text999));
                        }


                        currentSelectView = view;
                        currentSelectPosition = position;


                        if (view instanceof TextView) {
                            view.setSelected(true);
                            moeny = Integer.parseInt(list.get(currentSelectPosition));
                        }


                    }

                    @Override
                    public void unSelected(int position, View view) {
                        super.unSelected(position, view);
                        Log.i("unSelected", "position: " + position);
//                          view.setSelected(false);
//                          view.setBackground(getResources().getDrawable(R.drawable.tv_nomarl));

                        if (view instanceof TagView) {
                            TagView tagView = ((TagView) view);
                            tagView.setChecked(false);
                        }


                        if (view instanceof TextView) {
                            view.setSelected(false);
                        }
                        moeny = 0;

                    }

                };
                tagAdapter.setSelectedList(0);
                tagFlowLayout.setAdapter(tagAdapter);
                tagAdapter.notifyDataChanged();
            }
        });


    }
}
