package com.just.rebate.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.MultiItemEntity;
import com.just.rebate.adapter.recycle.OrderAdapter;
import com.just.rebate.entity.OrderItem;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
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
import okhttp3.Call;

/**
 * 订单  fragment
 */
public class OrderFragment extends BaseFragment {
    private List<OrderItem> mDatas;
    private Context context;
    private Button mBtn1, mBtn2;
    private List<MultiItemEntity> list;
    private List<ReturnPlatform> returnPlatforms;
    private List<ReturnShop> returnShops;
    private List<ReturnOrder> returnOrders;


    @BindView(R.id.rv_list4)
    RecyclerView recycleView;

    @BindView(R.id.arrivaldatails)
    ImageView mImageView;

    @BindView(R.id.Arrival_account)
    ImageView mArrival_account;


    @BindView(R.id.order_header)
    TextView mTv_header;
    @BindView(R.id.order_time)
    TextView mTv_time;
    @BindView(R.id.rich)
    TextView mTv_rich;


    @BindView(R.id.order_tv)
    TextView mTv_market;


    @BindView(R.id.order_iv)
    TextView mTv_Image;
    @BindView(R.id.order_name)
    TextView mTv_name;
    @BindView(R.id.order_price)
    TextView mTv_price;




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




        OrderAdapter orderAdapter = new OrderAdapter( list) {

            @Override
            protected void convert (BaseViewHolder helper, MultiItemEntity item) {
                super.convert(helper, item);
//                View view1=layoutInflater.inflate(R.layout.def_section_head,null);
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
