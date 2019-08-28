//package me.luocaca.rebate.ui.fragment;
//
//
//import android.app.ProgressDialog;
//import android.graphics.Color;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//
//import com.google.android.material.internal.FlowLayout;
//import com.rebate.base.fragment.BaseFragment;
//import com.zhy.view.flowlayout.TagAdapter;
//import com.zhy.view.flowlayout.TagFlowLayout;
//import com.zhy.view.flowlayout.TagView;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import me.luocaca.rebate.R;
//
//
//public class WalletsFragment extends BaseFragment {
//
//
//    //    public static final String APP_PACKAGE_NAME = "com.tencent.mm";
//    private String packageName;
//
//
////    ConstraintLayout check_group;
//
//
//    List<CheckBox> checkBoxes;
//
//
//    //商家id
////    private EditText storeId;
//    //会员账号
//
//    //userId
//    private EditText storeNum;
//
//    //确认账号
//    private EditText storeNumConfirm;
//
//    private Button submit;
//
//
//    private EditText other;
//
//
//    TagFlowLayout tagFlowLayout;
//    private TagAdapter tagAdapter;
//    private EditText et;
//
//    private TextView store_name;
//
//
//    private View currentSelectView;
//    private int currentSelectPosition = -1;
//    private List<String> list;
//
//
//    private String getPriceTag() {
//        int money = 0;
//
//        try {
//            money = Integer.parseInt(list.get(currentSelectPosition));
//
//        } catch (Exception e) {
//            money = 0;
//        }
//
//        if (money == 0 && et != null)
//            return et.getText().toString();
//
//        return money + "";
//    }
//
//    public WalletsFragment() {
//    }
//
//
//
//
//    private void radioChange(CompoundButton compoundButton) {
////        if (compoundButton.isChecked())
////        {
////            LogUtil.d("--------已经选择。不能点击了-----");
////            return;
////        }
//
//        for (CheckBox checkBox : checkBoxes) {
//            if (compoundButton == checkBox) {
//                LogUtil.d("" + checkBox.getText());
//                checkBox.setChecked(true);
//
//                if (checkBox == $findViewById(R.id.h)) {
//                    other.requestFocus();
//                }
//
//
//            } else {
//                checkBox.setChecked(false);
//            }
//        }
//    }
//
//    @Override
//    protected void setListener() {
//
//    }
//
//    @Override
//    protected void initData() {
////        http://192.168.1.102:4246/AppInfo/GetAppPrice
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ConstantPool.HOST_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
//
//
//        Call<ResponseBody> order = retrofitService.getAppPrice(PropertiesUtils.getValue("mchId"));
//
//
//        ProgressDialog dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("加载中...");
//        dialog.show();
//
//        order.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
//
//                dialog.dismiss();
//
//                try {
//                    String str = response.body().string();
//
//
////                    List<String> strings = new ArrayList<>();
//
////                    Type type = new TypeToken<List<String>>() {
////                    }.getType();
//
////                    List<String> list = new Gson().fromJson(str, type);
//
//
//                    String priceArray = new JSONObject(str).getString("priceArray");
//
//
//                    list = parseString2List(priceArray);
//
//                    tagAdapter = new TagAdapter(list) {
//                        @Override
//                        public View getView(FlowLayout parent, int position, Object o) {
//
//
//                            if (TextUtils.equals(o.toString(), "0")) {
//                                et = (EditText) $getView().inflate(getContext(), R.layout.et, null);
//                                //getResources().getDisplayMetrics().widthPixels
//                                int pad = (int) (parent.getWidth() * 0.06);
//                                et.setWidth((parent.getWidth() / 4));
//
//                                et.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
////                                        ToastUtils.showToastLong("被点击了");
//                                        currentSelectPosition = position;
//                                        tagAdapter.unSelected(currentSelectPosition, (TagView) currentSelectView.getParent());
//                                        et.setBackground(getResources().getDrawable(R.drawable.tv_un_nomarl));
//                                        et.setTextColor(Color.WHITE);
//                                        et.setHintTextColor(Color.WHITE);
//
//                                    }
//                                });
//                                et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                                    @Override
//                                    public void onFocusChange(View v, boolean hasFocus) {
//                                        if (hasFocus) {
//
//                                            try {
//                                                currentSelectPosition = position;
//                                                et.setBackground(getResources().getDrawable(R.drawable.tv_un_nomarl));
//                                                tagAdapter.unSelected(currentSelectPosition, (TagView) currentSelectView.getParent());
//
//                                                et.setTextColor(Color.WHITE);
//                                                et.setHintTextColor(Color.WHITE);
//
//                                            } catch (Exception e) {
//
//                                            }
//
//                                        }
//                                    }
//                                });
//                                return et;
//                            } else {
//                                TextView tv = (TextView) $getView().inflate(getContext(), R.layout.tv, null);
//                                tv.setText(o.toString() + "元");
////                              tv.setTextColor(R.drawable.pay_text_selector);
//                                //getResources().getDisplayMetrics().widthPixels
//                                int pad = (int) (parent.getWidth() * 0.06);
//                                tv.setWidth((parent.getWidth() / 4));
//                                return tv;
//                            }
//
//                        }
//
//
//                        @Override
//                        public void onSelected(int position, View view) {
//                            super.onSelected(position, view);
//                            Log.i("onSelected", "position: " + position);
//                            if (et != null) {
//                                et.setBackground(getResources().getDrawable(R.drawable.tv_nomarl));
//                                et.setTextColor(getResources().getColor(R.color.text_color_999));
//                                et.setHintTextColor(getResources().getColor(R.color.text_color_999));
//                            }
//
//
//                            currentSelectView = view;
//                            currentSelectPosition = position;
//
//
//                            if (view instanceof TextView) {
//                                view.setSelected(true);
//                            }
//
//
//                        }
//
//                        @Override
//                        public void unSelected(int position, View view) {
//                            super.unSelected(position, view);
//                            Log.i("unSelected", "position: " + position);
////                          view.setSelected(false);
////                          view.setBackground(getResources().getDrawable(R.drawable.tv_nomarl));
//
//                            if (view instanceof TagView) {
//                                TagView tagView = ((TagView) view);
//                                tagView.setChecked(false);
//                            }
//
//
//                            if (view instanceof TextView) {
//                                view.setSelected(false);
//                            }
//
//
//                        }
//
//                    };
//
//
//                    tagFlowLayout.setAdapter(tagAdapter);
//                    tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//                        @Override
//                        public boolean onTagClick(View view, int position, FlowLayout parent) {
////                            ToastUtils.showToastLong(position + "");
//                            return true;
//                        }
//                    });
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                dialog.dismiss();
//
//            }
//
//        });
//    }
//
//
//    public <T> List<T> parseString2List(String json) {
//        Type type = new TypeToken<List<T>>() {
//        }.getType();
//        List<T> list = new Gson().fromJson(json, type);
//        return list;
//    }
//
//    @Override
//    public void widgetClick(View v) {
//        //确定填写 是否为空
//
//        if (!TextUtils.equals(getEditTextById(storeNum), getEditTextById(storeNumConfirm))) {
//            //加入相等
//
//            ToastUtils.showToastLong("会员账号不相等");
//            return;
//        }
//
//
//        if (TextUtils.isEmpty(getEditTextById(storeNum))) {
//            ToastUtils.showToastLong("请填写会员账号");
//            return;
//        }
//
//
////        if (TextUtils.isEmpty(getSelectPrice())) {
//        if (TextUtils.isEmpty(getPriceTag()) || TextUtils.equals(getPriceTag(), "0")) {
//            ToastUtils.showToastLong("请选择支付金额");
//            return;
//        }
//
////        ToastUtils.showToastLong(getPriceTag());
//
//
////        String str = String.format("商家id:%s\n会员账号%s\n确认账号%s\n支付金额%s\n",
////                machId,
////                getEditTextById(storeNum),
////                getEditTextById(storeNumConfirm),
////                getSelectPrice()
////        );
//
////        ToastUtils.showToastLong(str);
//        PrePayDetailActivity.start(getActivity(), getEditTextById(storeNum), getPriceTag(), "");
//
//
//    }
//
//
//    public String getEditTextById(EditText et) {
//        return et.getText().toString();
//    }
//
//
//    public String getSelectPrice() {
//        for (CheckBox checkBox : checkBoxes) {
//            if (checkBox.isChecked()) {
//                return TextUtils.isEmpty(checkBox.getText().toString()) ? getEditTextById(other) : checkBox.getText().toString();
//            }
//        }
//        return getEditTextById(other);
//    }
//
//
//    @Override
//    protected int bindFragmentLayoutId() {
//        return R.layout.fragment_wallets;
//    }
//
//    @Override
//    protected void initViewsAndEvents(View view) {
//        store_name = $findViewById(R.id.store_name);
//
//        String MchName = (String) SharedPreUtils.obtain("MchName", "");
//
//        if (TextUtils.isEmpty(MchName)) {
//            ((ViewGroup) store_name.getParent()).setVisibility(View.GONE);
//        } else {
//            ((ViewGroup) store_name.getParent()).setVisibility(View.VISIBLE);
//            store_name.setText(MchName);
//        }
//
//        tagFlowLayout = $findViewById(R.id.id_flowlayout);
//        tagFlowLayout.setMaxSelectCount(1);
//
//        List list = new ArrayList();
//        list.add("10");
//        list.add("20");
//        list.add("30");
//        list.add("40");
//        list.add("50");
//        list.add("60");
//        list.add("70");
//
//
////预先设置选中
//
//
////        check_group = $findViewById(R.id.check_group);
//
//
////        storeId = $findViewById(R.id.store_id);
//        storeNum = $findViewById(R.id.store_num);
//        storeNumConfirm = $findViewById(R.id.store_num_confirm);
//        other = $findViewById(R.id.other);
//        //确认按钮
//        submit = $findViewById(R.id.submit);
//        submit.setOnClickListener(this::widgetClick);
//
////        if (checkBoxes == null) checkBoxes = new ArrayList<>();
//
////        for (int i = 0; i < check_group.getChildCount(); i++) {
////            View view = check_group.getChildAt(i);
////
////            if (view instanceof CheckBox) {
////                checkBoxes.add(((CheckBox) view));
////                view.setOnClickListener(view1 -> radioChange(((CheckBox) view1)));
////            } else {
////                //last edit view
////                view.setOnClickListener(view12 -> {
////                    radioChange($findViewById(R.id.h));
////
////                });
////
////            }
////        }
//
//
////        LogUtil.d("-------------" + checkBoxes.size());
//
//    }
//}
