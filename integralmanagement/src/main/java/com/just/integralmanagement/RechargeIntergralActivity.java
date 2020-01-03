package com.just.integralmanagement;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.just.integralmanagement.entity.RechargeIntegralData1;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.List;

import application.ApplicationClass;

/**
 * 积分充值界面
 */

public class RechargeIntergralActivity extends AppCompatActivity {

    private EditText mEt_user, mEt_integral;
    private Button mBtn_recharge;
    private TagFlowLayout tagFlowLayout;
    private Context context;
    private View currentSelectView;
    private int currentSelectPosition = -1;
    private EditText et;
    private TextView tv;
    private int moeny = 0;
    private TagAdapter tagAdapter;
    private RadioButton mRb_YHK, mRb_ZFB, mRb_VX;
    private boolean isVisible = true;
    private List<RechargeIntegralData1> rechargeIntegralData1s = new ArrayList<>();
    private String Authorization = "";
    private int ReceivingType;
    private String IntegralNum;
    private ApplicationClass applicationClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_inergral);
        applicationClass = (ApplicationClass) getApplication();
        mBtn_recharge = findViewById(R.id.rechargeIntegral);
        mRb_YHK = findViewById(R.id.radio1);
        mRb_ZFB = findViewById(R.id.radio2);
        mRb_VX = findViewById(R.id.radio3);
        mRb_YHK.setChecked(true);
//        mEt_integral = findViewById(R.id.Integral_Num);
        mEt_user = findViewById(R.id.User_ID);
        tagFlowLayout = findViewById(R.id.Tagflowlayout);
        initReceiveData();
        initTagFlowLayout();
        mBtn_recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEt_user.getText().toString().isEmpty()) {
                    Toast.makeText(RechargeIntergralActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    initData();
                }
            }
        });
        getSupportActionBar().setTitle("充值");
        setToolbars();
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
                            et = (EditText) View.inflate(RechargeIntergralActivity.this, R.layout.et, null);
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
                            TextView tv = (TextView) View.inflate(RechargeIntergralActivity.this, R.layout.tv, null);
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
                        moeny=0;

                    }

                };

                tagAdapter.setSelectedList(0);
                tagFlowLayout.setAdapter(tagAdapter);
                tagAdapter.notifyDataChanged();
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

    private void initReceiveData() {
        Intent intent = getIntent();
        mEt_user.setText("" + intent.getIntExtra("Id", 0));
        Authorization = intent.getStringExtra("Authorization");
    }


    private void initData() {
        if (moeny == 0 && et != null) {
            IntegralNum = et.getText().toString();
        } else {
            IntegralNum = moeny + "";
        }
        Log.i("IntegralNum", "initData: " + IntegralNum);
        if (mRb_YHK.isChecked()) {
            ReceivingType = 1;
            Intent intent = new Intent(this, BankPayActivity.class);
            intent.putExtra("Authorization", Authorization);
            intent.putExtra("IntegralNum", IntegralNum + "");
            intent.putExtra("ReceivingType", ReceivingType);
            startActivity(intent);
        } else if (mRb_VX.isChecked()) {
            ReceivingType = 0;
            Intent intent = new Intent(this, VxOrZfbPayActivity.class);
            intent.putExtra("Authorization", Authorization);
            intent.putExtra("IntegralNum", IntegralNum + "");
            intent.putExtra("ReceivingType", ReceivingType);
            startActivity(intent);
        } else if (mRb_ZFB.isChecked()) {
            ReceivingType = 2;
            Intent intent = new Intent(this, VxOrZfbPayActivity.class);
            intent.putExtra("Authorization", Authorization);
            intent.putExtra("IntegralNum", IntegralNum + "");
            intent.putExtra("ReceivingType", ReceivingType);
            startActivity(intent);
        } else {
            Toast.makeText(context, "请选择正确的支付方式", Toast.LENGTH_SHORT).show();
        }
    }
}
