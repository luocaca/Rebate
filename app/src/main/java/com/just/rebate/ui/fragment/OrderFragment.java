package com.just.rebate.ui.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.OrderAdapter;
import com.just.rebate.adapter.recycle.SectionAdapter;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.OrderItem;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.ui.MainActivity;
import com.just.rebate.ui.activity.ArrivalAccountActivity;
import com.just.rebate.ui.activity.ArrivalDetailsActivity;
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
import okhttp3.Call;

/**
 * 订单  fragment
 */
public class OrderFragment extends BaseFragment {
    private List<OrderItem> mDatas;
    private Context context;
    private Button mBtn1, mBtn2;


    @BindView(R.id.rv_list4)
    RecyclerView recycleView;

    @BindView(R.id.arrivaldatails)
    ImageView mImageView;

    @BindView(R.id.Arrival_account)
    ImageView mArrival_account;

    @BindView(R.id.invalid)
    ImageView mInvalid;

    @BindView(R.id.Order_to_Payment)
    TextView mTextOrder_to_Payment;


    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 1));
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(true, "淘宝"));
        orderItems.add(new OrderItem(false, "name2"));
        orderItems.add(new OrderItem(false, "name2"));
        orderItems.add(new OrderItem(true, "淘宝"));
        orderItems.add(new OrderItem(false, "name2"));
        mDatas = orderItems;


        mTextOrder_to_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
            }
        });


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrackingProcessingActivity.class);
                getActivity().startActivity(intent);
            }
        });
        mArrival_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ArrivalAccountActivity.class);
                startActivity(intent);
            }
        });
        mInvalid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InvalidActivity.class);
                startActivity(intent);
            }
        });


        OrderAdapter orderAdapter = new OrderAdapter(R.layout.item_order_content, R.layout.item_order_head, mDatas) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, OrderItem item) {

            }

            @Override
            protected void convertHead(BaseViewHolder helper, OrderItem item) {
                super.convertHead(helper, item);
//                View view1=layoutInflater.inflate(R.layout.def_section_head,null);
                helper.setText(R.id.order_header, "淘宝");
                helper.setText(R.id.order_tv, "米拉服饰");

            }


        };
        recycleView.setAdapter(orderAdapter);

    }

    @Override
    protected void initData() {
        requestData();
    }


    public void requestData() {
        //http://192.168.1.171:8080/download/platform.txt


        //                .url("http://192.168.1.171:8080/download/personaljson.txt")

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


                        Log.i("result", "onResponse: "+list);

                        recycleView.getAdapter().notifyDataSetChanged();


                    }
                });


    }


}
