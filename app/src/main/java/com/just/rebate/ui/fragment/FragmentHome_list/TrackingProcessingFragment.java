package com.just.rebate.ui.fragment.FragmentHome_list;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.just.rebate.entity.order.跟踪处理.TrackingProcess;
import com.just.rebate.entity.order.跟踪处理.TrackingProcessOrder;
import com.just.rebate.ui.activity.OrderDetailsActivity;
import com.just.rebate.ui.activity.SuccessActivity;
import com.rebate.base.fragment.BaseLazyFragment;
import com.rebate.commom.util.GsonUtil;
import com.rebate.commom.util.bitmap.GlideRoundTransform;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * title跟踪处理
 */

public class TrackingProcessingFragment extends BaseLazyFragment implements View.OnClickListener {


    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.rv_list6)
    RecyclerView mrecyclerView;


    @Override
    public void onClick(View view) {

    }

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.activity_tracking_processing;
    }

    @Override
    protected void initViewsAndEvents(View view) {


    }

    private void initRecyclerview() {
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TrackingProcessingAdapter trackingProcessingAdapter = new TrackingProcessingAdapter(new ArrayList()) {

            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                if (item.getItemType() == 1) {
                } else {
                    doConvert(getActivity(), helper, item);
                }
            }
        };
        trackingProcessingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view == view.findViewById(R.id.order_details)) {
                    Intent intent1 = new Intent(getActivity(), SuccessActivity.class);
                    startActivity(intent1);
                } else if (view == view.findViewById(R.id.transition_position_to_success)) {
                    Intent intent2 = new Intent(getActivity(), OrderDetailsActivity.class);
                    startActivity(intent2);
                }
            }
        });
        mrecyclerView.setAdapter(trackingProcessingAdapter);
    }

    public static void doConvert(Activity mActivity, BaseViewHolder helper, MultiItemEntity item) {

        if (item instanceof TrackingProcessOrder) {

            String string1;
            string1 = ((TrackingProcessOrder) item).getTrackingProcess().getReciverTime() + "接单成功";
//            doConvert =1
            helper.setText(R.id.Track_name, ((TrackingProcessOrder) item).getTrackingProcess().getPlatformName());
            helper.setText(R.id.Track_time, string1);
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

            helper.addOnClickListener(R.id.transition_position_to_success);
            helper.addOnClickListener(R.id.order_details);
        }
    }


    @Override
    protected void initData() {
    }
    /**
     * 为子类设置 父类对象
     *
     * @param integer
     * @param data
     */
    private void setChilds(TrackingProcess integer, List<TrackingProcessOrder> data) {
        for (TrackingProcessOrder datum : data) {
            datum.setTrackingProcess(integer);
        }
//        int startIndex = -1;
//        int nextHeadIndex = -1;
//        for (int i = 0; i < data.size(); i++) {
//
//            if (data.get(i).equals(integer)) {
//                startIndex = i;
//            }
//            if (i > startIndex)
//            {
//                if (data.get(i) instanceof TrackingProcess)
//                {
//
//                }
//            }
//
//        }
    }


    @Override
    protected void onFirstUserVisible() {
        initRecyclerview();
        OkHttpUtils
                .get()
                .url("https://www.luocaca.cn/download/rebate/api/trac.txt")//跟踪信息
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

//                        for (Object datum : adapter.getData()) {
//                            if (datum instanceof TrackingProcess) {
//                                adapter.getData().remove(datum);
//                            }
//                        }
                        Iterator iterator = adapter.getData().iterator();
                        while (iterator.hasNext()) {
                            Object integer = iterator.next();
                            if (integer instanceof TrackingProcess) {
                                setChilds((TrackingProcess) integer, ((TrackingProcess) integer).getSubItems());
                                iterator.remove();
                            }
                        }


                        adapter.notifyDataSetChanged();
                        Log.i("result", "onResponse: " + list.getData());

                    }
                });
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void destroyViewAndThing() {

    }
}
