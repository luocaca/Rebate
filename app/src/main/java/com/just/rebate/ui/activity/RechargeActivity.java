package com.just.rebate.ui.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.just.rebate.R;
import com.rebate.base.activity.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    @BindView(R.id.radio)
    RadioGroup radioGroup;

//    @BindView(R.id.bakc_Recharge)
//    ImageView mIv_back;


    private View currentSelectView;
    private int currentSelectPosition = -1;
    private EditText et;
    private TagAdapter tagAdapter;
    private boolean isVisible = true;


    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        initViewsAndEvents();

        initTagFlowLayout();
        initonClick();

    }
    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    private void initonClick() {
//        mIv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();onBackPressed();
//            }
//        });

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


                    }

                };
                tagAdapter.setSelectedList(0);
                tagFlowLayout.setAdapter(tagAdapter);
                tagAdapter.notifyDataChanged();
            }
        });


    }
}
