package com.just.rebate.ui.fragment.FragmentHome_list;


import android.app.Activity;
import android.content.DialogInterface;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.just.integralmanagement.entity.DeviceBean;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.FragmentOrderAdapter;
import com.just.rebate.adapter.recycle.PaymentAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.OrderItem;
import com.just.rebate.entity.OrderListData;
import com.just.rebate.entity.PaymentBean;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.just.rebate.ui.activity.ArrivalDetailsActivity;
import com.just.rebate.ui.activity.PaymentActivity;
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
public class OrderFragment extends BaseFragment {
    private MyApplication application;
    private List<OrderListData.RowsBean> rowsBeans = new ArrayList<>();
    private double count;
    private JSONObject jsonObject = new JSONObject();
    private JSONArray jsonArray = new JSONArray();

    @BindView(R.id.rv_list4)
    RecyclerView recycleView;

    @BindView(R.id.order_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.Order_to_Payment)
    TextView mTextOrder_to_Payment;

    @BindView(R.id.checkbox_all)
    TextView checkbox_all;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViewsAndEvents(View view) {

    }

    @Override
    protected void initData() {
        application = (MyApplication) getActivity().getApplication();
        try {
            initRecyclerview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initOnClick();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    initRecyclerViewData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jsonArray = new JSONArray();
                jsonObject = new JSONObject();
                count = 0;
                /**
                 *
                 */
                checkbox_all.setText("" + count);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        try {
            initRecyclerViewData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initRecyclerview() {
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        PaymentAdapter paymentAdapter = new PaymentAdapter(new ArrayList()) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                super.convert(helper, item);
                if (item instanceof OrderListData.RowsBean) {
                    DecimalFormat df = new DecimalFormat("0.00");
                    if (((OrderListData.RowsBean) item).OrderItems.size() == 0) {
                        helper.setText(R.id.OrderPlatformName, "");
                        helper.setText(R.id.PaymentExpried, "");
                        helper.setText(R.id.order_name, "");
                        helper.setText(R.id.SpecName, "");
                        helper.setText(R.id.Amount, "");
                        helper.setText(R.id.count, "");
                        Glide.with(helper.itemView.getContext()).load("").into((ImageView) helper.getView(R.id.order_iv));
                    } else {
                        String time = ((OrderListData.RowsBean) item).PaymentExpried.replaceAll("T", " ");
                        String timeers = time.replaceAll("T", " ");
                        String[] times = timeers.split("\\.", 2);
                        helper.setText(R.id.OrderPlatformName, ((OrderListData.RowsBean) item).OrderPlatformName);
                        helper.setText(R.id.PaymentExpried, times[0]);
                        helper.setText(R.id.order_name, ((OrderListData.RowsBean) item).OrderItems.get(0).ProductName);
                        helper.setText(R.id.SpecName, ((OrderListData.RowsBean) item).OrderItems.get(0).SpecName);
                        helper.setText(R.id.Amount, df.format(((OrderListData.RowsBean) item).Amount) + "");
                        helper.setText(R.id.count, ((OrderListData.RowsBean) item).OrderItems.get(0).Count + "");
                        Glide.with(helper.itemView.getContext()).load(((OrderListData.RowsBean) item).OrderItems.get(0).Image).into((ImageView) helper.getView(R.id.order_iv));
                        helper.setChecked(R.id.CheckBtn, ((OrderListData.RowsBean) item).isChecked());
                        helper.addOnClickListener(R.id.CheckBtn);
                    }
                }

            }
        };
        paymentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DecimalFormat df = new DecimalFormat("0.00");
                if (paymentAdapter.getData().get(position) instanceof OrderListData.RowsBean) {
                    OrderListData.RowsBean rowsBean = (OrderListData.RowsBean) paymentAdapter.getData().get(position);
                    rowsBean.toggle();
                    if (rowsBean.isChecked()) {
                        rowsBean.setChecked(true);
                        count += rowsBean.Amount;
                        jsonArray.put(rowsBean.OrderNo);
                        try {
                            jsonObject.put("OrderNos", jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        checkbox_all.setText("总价：" + df.format(count));
                    } else {
                        rowsBean.setChecked(false);
                        for (int s = 0; s < jsonArray.length(); s++) {
                            try {
                                String OrderNo = jsonArray.getString(s);
                                if (OrderNo.contains(rowsBean.OrderNo)) {
                                    jsonArray.remove(s);
                                    jsonObject.put("OrderNos", jsonArray);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        count -= rowsBean.Amount;
                        checkbox_all.setText("总价：" + df.format(count));
                    }

                } else {

                }
                adapter.notifyDataSetChanged();
            }
        });
        recycleView.setAdapter(paymentAdapter);
    }

    private void initOnClick() {
        mTextOrder_to_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_all.getText().toString().isEmpty()) {
                    Toast.makeText(application, "您还未选择支付商品", Toast.LENGTH_SHORT).show();
                } else {
                    SendPayData();
                }
//                Intent intent = new Intent(mActivity, PaymentActivity.class);
//                startActivity(intent);
            }
        });
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
                            Toast.makeText(application, "当前无法支付，请前往购物车付款", Toast.LENGTH_SHORT).show();
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
        TextView mTv_consume = view.findViewById(R.id.consumeIntegral);
        mTv_consume.setText("预计消耗" + checkbox_all.getText().toString() + "点积分");
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
                try {
                    initSendPaymentData(dialog);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }

        });
    }

    private void initSendPaymentData(AlertDialog dialog) throws JSONException {
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
                        PaymentBean paymentBean = GsonUtil.getGsonLower().fromJson(response, PaymentBean.class);
                        if (paymentBean.getType() == 200) {
                            dialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                            builder.setMessage(paymentBean.getContent());
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.create();
                            builder.show();
                        } else {
                            dialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                            builder.setMessage(paymentBean.getContent());
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.create();
                            builder.show();
                        }
                    }
                });
    }

    private void initRecyclerViewData() {
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONObject jsonObject5 = new JSONObject();
        JSONObject jsonObject6 = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        try {
            jsonObject2.put("PageIndex", 1);
            jsonObject2.put("PageSize", 1000);

            jsonObject6.put("SortField", "CreatedTime");
            jsonObject6.put("ListSortDirection", 2);

            jsonArray2.put(jsonObject6);

            jsonObject2.put("SortConditions", jsonArray2);

            jsonObject3.put("Field", "OrderStatus");
            jsonObject3.put("Value", 1);
            jsonObject3.put("Operate", 3);

            jsonObject5.put("Field", "IsUserPayment");
            jsonObject5.put("Value", "true");
            jsonObject5.put("Operate", 3);

            jsonArray1.put(jsonObject3);
            jsonArray1.put(jsonObject5);

            jsonObject4.put("Rules", jsonArray1);

            jsonObject1.put("PageCondition", jsonObject2);
            jsonObject1.put("FilterGroup", jsonObject4);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("initRecyclerViewData", "initRecyclerViewData: 看看登录信息是否有值" + application.getAuthorization());
        OkHttpUtils
                .postString()
                .content(jsonObject1.toString())
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
                        rowsBeans.clear();
                        if (orderListData.Rows.size() != 0) {
                            rowsBeans.addAll(orderListData.Rows);
                            BaseQuickAdapter adapter = (BaseQuickAdapter) recycleView.getAdapter();
                            adapter.getData().clear();
                            adapter.addData(rowsBeans);
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }
}
