package com.just.rebate.ui.fragment.FragmentHome_list;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.integralmanagement.entity.DeviceBean;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.FragmentOrderAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.OrderListData;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.rebate.base.fragment.BaseFragment;
import com.rebate.base.fragment.BaseLazyFragment;
import com.rebate.commom.util.GsonUtil;
import com.rebate.commom.util.bitmap.GlideRoundTransform;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 订单  fragment
 */
public class OrderFragment extends BaseFragment implements View.OnClickListener {
    private static int acount = 1;
    private MyApplication application;
    private List<OrderListData.RowsBean> orderListDatas = new ArrayList<>();
    private boolean CheckAll;
    private JSONObject jsonObjects;

    @BindView(R.id.rv_list4)
    RecyclerView recycleView;

    @BindView(R.id.order_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.Order_to_Payment)
    TextView mTextOrder_to_Payment;
    @BindView(R.id.checkbox_all)
    TextView checkbox_all;


    boolean isCheckAll = false;

    @OnClick({R.id.Order_to_Payment, R.id.checkbox_all})
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.Order_to_Payment:
                try {
                    SendPayData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void SendPayData() {
        OkHttpUtils.get()
                .url("http://192.168.1.137:7001/api/Open/OpenSetting/IsInterceptGoPay")
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 支付请求" + e);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 支付请求" + response);
                        if (response.equals("false")) {

                        } else if (response.equals("true")) {
                            AlertDiglog();
                        }
                    }
                });

    }

    private void AlertDiglog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View view = inflater.inflate(R.layout.alert_diglog_layout, null);
        Button mBtn1 = view.findViewById(R.id.button1);
        Button mBtn2 = view.findViewById(R.id.button2);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用 支付密码键盘
//                Bundle bundle = new Bundle();
//                PayFragment fragment = new PayFragment();
//                fragment.setArguments(bundle);
//                fragment.show(getSupportFragmentManager(), "Pay");
                initSendPaymentData();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRecyclerviewData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void initSendPaymentData() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < orderListDatas.size(); i++) {
                jsonArray.put(orderListDatas.get(i).OrderNo);
                Log.i("onClick", "onClick: " + orderListDatas.get(i).OrderNo);
            }
            jsonObject.put("OrderNos", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("onClick", "onClick: 选中返回的值" + jsonObject.toString());
        OkHttpUtils.postString()
                .content(jsonObject.toString())
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .url("http://192.168.1.137:7001/api/Open/OpenOrder/Payment")//平台数据列表
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 发起支付" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 发起支付" + response);
                    }
                });
    }

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViewsAndEvents(View view) {
//        CheckAll = initAllCheck();

    }

    private void initRecyclerviewData() {
        Map<String, String> params = new HashMap<>();
        params.put("PageIndex", 1 + "");
        params.put("PageSize", 30 + "");
        OkHttpUtils
                .postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .url("http://192.168.1.137:7001/api/Open/OpenOrder/Read")//平台数据列表
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.i("onResponse", "onResponse: 订单列表" + response);
                        OrderListData orderListData = GsonUtil.getGsonLower().fromJson(response, OrderListData.class);
                        orderListDatas.clear();
                        if (orderListData.Rows.size() != 0) {
                            orderListDatas.addAll(orderListData.Rows);
                            swipeRefreshLayout.setRefreshing(false);
                            initRecyclerview();
                        }
                    }
                });

    }

    private void initRecyclerview() {
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        FragmentOrderAdapter fragmentOrderAdapter = new FragmentOrderAdapter(orderListDatas, mActivity);
        recycleView.setAdapter(fragmentOrderAdapter);
    }

    @Override
    protected void initData() {
        application = (MyApplication) getActivity().getApplication();
        initRecyclerviewData();
    }

}
