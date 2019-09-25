package com.just.rebate.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.PaymentAdapter;
import com.just.rebate.entity.OrderItem;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.rebate.commom.util.bitmap.GlideRoundTransform;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

public class PaymentActivity extends BaseActivity {
    private List<OrderItem> mDatas;


    @BindView(R.id.rv_list4)
    RecyclerView recyclerView;

    @BindView(R.id.AlertDiglog)
    TextView mAlertDiglog;

    @BindView(R.id.back_order)
    ImageView mIv_back;

    @BindView(R.id.payment_refresh)
    SwipeRefreshLayout swipeRefreshLayout;





    @Override
    protected void initView() {
        initRecyclerView();
        initData();
        initOnClick();

    }

    private void initOnClick() {
        mAlertDiglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDiglog();
            }
        });
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestData();
                    }
                }, 3000);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void initData() {

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(PaymentActivity.this, 1));
        PaymentAdapter paymentAdapter = new PaymentAdapter(new ArrayList()) {

            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                if (item.getItemType() == 1) {
                } else {
                    doConvert(mActivity, helper, item, getData());
                }
            }
        };
        recyclerView.setAdapter(paymentAdapter);

    }
    public static void doConvert(Activity mActivity, BaseViewHolder helper, MultiItemEntity item, List<MultiItemEntity> data) {

        if (item instanceof ReturnPlatform) {
            //do conevrt 0
            helper.setText(R.id.order_header, ((ReturnPlatform) item).getPlatformName());
            helper.setText(R.id.order_time, ((ReturnPlatform) item).getOrderTime());
            helper.setText(R.id.rich, ((ReturnPlatform) item).getEstimatedRebate());
        } else if (item instanceof ReturnShop) {
            //do conevert 1
            helper.setText(R.id.payment_tv, ((ReturnShop) item).getShopName());

            boolean isFirstShop = isFirstShop(mActivity, helper, item, helper, data);
//            helper.setVisible(R.id.topline, isFirstShop);
//            helper.getView(R.id.topline).setVisibility(isFirstShop ? View.GONE : View.VISIBLE);

            if (isFirstShop) {
                helper.getView(R.id.payment_topline).setVisibility(View.VISIBLE);
                helper.getView(R.id.payment_topline).setBackgroundResource(R.drawable.shape_corner_left_top_right_top);
            } else {
                helper.getView(R.id.payment_topline).setVisibility(View.GONE);
            }


        } else if (item instanceof ReturnOrder) {


//            RequestOptions options = RequestOptions.circleCropTransform();//圆形图片  好多的图片形式都是这么设置的
//设置图片圆角角度
            RoundedCorners roundedCorners = new RoundedCorners(6);
//通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).centerCrop();

            //do conevert 2
//            Glide.with(mActivity).load(((ReturnOrder) item).getCoverUrl())
//                    .apply(options).into((ImageView) helper.getView(R.id.order_iv));
//            Glide.with(context).load(historys.get(position).getHeadImg()).apply(RequestOptions.bitmapTransform(new RoundedCorners(22))).into(holder.logo);

            RequestOptions myOptions = new RequestOptions()
//                        .transform(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                    .transform(new GlideRoundTransform(helper.itemView.getContext(), 6))
//                      .centerCrop()
                    ;

            DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(100).setCrossFadeEnabled(true).build();
//                Glide.with(ZZSearch2UpActivity.this)
//                        .load("")
//                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
//                ;

            Glide.with(helper.itemView.getContext()).load(((ReturnOrder) item).getCoverUrl()).transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).apply(myOptions).into((ImageView) helper.getView(R.id.order_iv));
//              Glide.with(helper.itemView.getContext()).load("https://pic1.zhuanstatic.com/zhuanzh/" + item.getPics()).apply(RequestOptions.bitmapTransform(new RoundedCorners(22))).into((ImageView) helper.getView(R.id.logo));



            helper.setText(R.id.order_name, ((ReturnOrder) item).getOrderName());
            helper.setText(R.id.order_price, ((ReturnOrder) item).getCommodityPrice());


            boolean islast = isLastOrder(mActivity, helper, item, helper, data);
            if (islast) {
                helper.itemView.setBackgroundResource(R.drawable.shape_corner_left_bottom_right_bottom);
            }
        }

    }


    private static boolean isLastOrder(Activity mActivity, BaseViewHolder helper, MultiItemEntity item, BaseViewHolder helper1, List<MultiItemEntity> data) {
        try {
            Object obj = data.get(helper.getAdapterPosition() + 1);
            if (obj instanceof ReturnPlatform) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }


    private static boolean isFirstShop(Activity mActivity, BaseViewHolder helper, MultiItemEntity item, BaseViewHolder helper1, List<MultiItemEntity> data) {
        try {
            Object obj1;
            obj1 = data.get(helper.getAdapterPosition() - 1);

            if (obj1 instanceof ReturnOrder) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }


    @Override
    public int bindLayoutId() {
        return R.layout.activity_payment;
    }

    private void AlertDiglog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(PaymentActivity.this);
        LayoutInflater inflater=LayoutInflater.from(PaymentActivity.this);
        View view=inflater.inflate(R.layout.alert_diglog_layout,null);
        Button mBtn1=view.findViewById(R.id.button1);
        Button mBtn2=view.findViewById(R.id.button2);
        final AlertDialog dialog=builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this, "付款布局", Toast.LENGTH_SHORT).show();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
    @Override
    protected void requestData() {
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

                        BaseQuickAdapter adapter = (BaseQuickAdapter) recyclerView.getAdapter();

                        adapter.getData().clear();
                        adapter.addData(list);
                        adapter.expandAll();
                        adapter.notifyDataSetChanged();
                        Log.i("result", "onResponse: " + list);

                    }
                });
    }

}
