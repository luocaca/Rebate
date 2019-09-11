package com.just.rebate.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.OrderAdapter;
import com.just.rebate.entity.OrderItem;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.ui.activity.ArrivalAccountActivity;
import com.just.rebate.ui.activity.InvalidActivity;
import com.just.rebate.ui.activity.PaymentActivity;
import com.just.rebate.ui.activity.TrackingProcessingActivity;
import com.rebate.base.fragment.BaseFragment;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 订单  fragment
 */
public class OrderFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rv_list4)
    RecyclerView recycleView;

    @BindView(R.id.arrivaldatails)
    ImageView mImageView;
    //
    @BindView(R.id.Arrival_account)
    ImageView mArrival_account;


    @BindView(R.id.invalid)
    ImageView mInvalid;

    @BindView(R.id.Order_to_Payment)
    TextView mTextOrder_to_Payment;


    @OnClick({R.id.Order_to_Payment, R.id.arrivaldatails, R.id.invalid, R.id.Arrival_account})
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.Order_to_Payment:
                intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.arrivaldatails:
                intent = new Intent(getActivity(), TrackingProcessingActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.Arrival_account:
                intent = new Intent(getActivity(), ArrivalAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.invalid:
                intent = new Intent(getActivity(), InvalidActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        OrderAdapter orderAdapter = new OrderAdapter(new ArrayList()) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                Log.i(TAG, "convert: " + item);
                doConvert(mActivity, helper, item);
            }
        };
        recycleView.setAdapter(orderAdapter);
    }

    /**
     * @param mActivity
     * @param helper
     * @param item
     */
    public static void doConvert(Activity mActivity, BaseViewHolder helper, MultiItemEntity item) {




    }

    @Override
    protected void initData() {
        requestData();
    }


    public void requestData() {
        OkHttpUtils
                .get()
                .url("http://192.168.1.171:8080/download/platform.txt")//平台数据列表
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Type t = new TypeToken<List<ReturnPlatform>>() {
                        }.getType();

                        List<ReturnPlatform> list = GsonUtil.getGson().fromJson(response, t);

                        BaseQuickAdapter adapter = (BaseQuickAdapter) recycleView.getAdapter();

                        adapter.addData(list);
                        adapter.expandAll();
                        adapter.notifyDataSetChanged();
                        Log.i("result", "onResponse: " + list);


                    }
                });


    }


}
