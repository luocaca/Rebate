package com.just.rebate.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.just.rebate.adapter.recycle.TrackingProcessingAdapter;
import com.just.rebate.base.BaseResponse;
import com.just.rebate.entity.TrackingProcessingItem;
import com.just.rebate.entity.order.跟踪处理.TrackingProcess;
import com.just.rebate.entity.order.跟踪处理.TrackingProcessOrder;
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

public class TrackingProcessingActivity extends BaseActivity {


    @BindView(R.id.rv_list6)
    RecyclerView mrecyclerView;

    @BindView(R.id.back_trck)
    ImageView mIv_back;

    @BindView(R.id.Track_to_ArrivalAccount)
    ImageView mIv_Account;

    @BindView(R.id.Track_to_Ivalid)
    ImageView mIv_inValid;


    private List<TrackingProcessingItem> mDatas;

    @Override
    protected void initView() {
        initRecyclerview();
        initonClick();

    }

    public static void doConvert(Activity mActivity, BaseViewHolder helper, MultiItemEntity item) {

        if (item instanceof TrackingProcess) {

            String string1;
            string1 = ((TrackingProcess) item).getReciverTime() + "接单成功";
//            doConvert =1
            helper.setText(R.id.Track_name, ((TrackingProcess) item).getPlatformName());
            helper.setText(R.id.Track_time, string1);
        } else if (item instanceof TrackingProcessOrder) {

            String string2, string3;
            string2 = "返利" + ((TrackingProcessOrder) item).getBackPrice() + "元";
            string3 = "订单编号:" + ((TrackingProcessOrder) item).getOrderNo();
//            doConvert =2
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
            Glide.with(helper.itemView.getContext()).load(((TrackingProcessOrder) item).getCoverUrl()).transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).apply(myOptions).into((ImageView) helper.getView(R.id.track_img));
            helper.setText(R.id.Track_fanli, string2);
            helper.setText(R.id.Track_dingdan, string3);
        }
    }

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void requestData() {
        OkHttpUtils
                .get()
                .url("http://192.168.1.171:8080/download/rebate/api/trac.txt")//跟踪信息
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Type t = new TypeToken<BaseResponse<List<TrackingProcess>>>() {
                        }.getType();

                        BaseResponse<List<TrackingProcess>> list = GsonUtil.getGson().fromJson(response, t);

                        BaseQuickAdapter adapter = (BaseQuickAdapter) mrecyclerView.getAdapter();

                        adapter.getData().clear();
                        adapter.addData(list.getData());
                        adapter.expandAll();
                        adapter.notifyDataSetChanged();
                        Log.i("result", "onResponse: " + list.getData());

                    }
                });
    }


    private void initonClick() {
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                onBackPressed();
            }
        });

        mIv_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrackingProcessingActivity.this, ArrivalAccountActivity.class);
                startActivity(intent);
            }
        });
        mIv_inValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrackingProcessingActivity.this, InvalidActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerview() {
        mrecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        TrackingProcessingAdapter trackingProcessingAdapter = new TrackingProcessingAdapter(new ArrayList()) {

            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                if (item.getItemType() == 1) {
                } else {
                    doConvert(mActivity, helper, item);
                }

            }
        };
        trackingProcessingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view == findViewById(R.id.order_details)) {
                    Intent intent1 = new Intent(TrackingProcessingActivity.this, OrderDetailsActivity.class);
                    startActivity(intent1);
                } else if (view == findViewById(R.id.transition_position_to_success)) {
                    Intent intent2 = new Intent(TrackingProcessingActivity.this, SuccessActivity.class);
                    startActivity(intent2);
                }
            }
        });

        mrecyclerView.setAdapter(trackingProcessingAdapter);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_tracking_processing;
    }


}
