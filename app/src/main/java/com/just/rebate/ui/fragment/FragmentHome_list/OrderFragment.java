package com.just.rebate.ui.fragment.FragmentHome_list;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.OrderAdapter;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.just.rebate.ui.activity.PaymentActivity;
import com.rebate.base.fragment.BaseLazyFragment;
import com.rebate.commom.util.GsonUtil;
import com.rebate.commom.util.bitmap.GlideRoundTransform;
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
public class OrderFragment extends BaseLazyFragment implements View.OnClickListener {
    private static int acount=1;

    @BindView(R.id.rv_list4)
    RecyclerView recycleView;


    @BindView(R.id.order_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.Order_to_Payment)
    TextView mTextOrder_to_Payment;
    @BindView(R.id.checkbox_all)
    CheckBox checkbox_all;



    boolean isCheckAll = false;

    @OnClick({R.id.Order_to_Payment, R.id.checkbox_all})
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.Order_to_Payment:
                intent = new Intent(getActivity(), PaymentActivity.class);
                startActivity(intent);
                break;
            case R.id.checkbox_all:
                Toast.makeText(mActivity, "" + checkbox_all.isChecked(), Toast.LENGTH_SHORT).show();
                //全选
                BaseQuickAdapter adapter = (BaseQuickAdapter) recycleView.getAdapter();
                if (adapter != null) {
                    for (Object datum : adapter.getData()) {
                        if (datum instanceof Checkable) {
                            ((Checkable) datum).setChecked(checkbox_all.isChecked());

                        }
                    }
                }
//                checkbox_all.toggle();
                adapter.notifyDataSetChanged();

                break;
//            case R.id.checkbox_context:
//                if (change){
//                    mIv_CheckBox_context.setImageResource(R.drawable.checkbox_checked);
//                }else {
//                    mIv_CheckBox_context.setImageResource(R.drawable.checkbox_unchecked);
//                }
//                change = !change;
//                break;
//            case R.id.checkbox_head:
//                if (change){
//                    mIv_CheckBox_head.setImageResource(R.drawable.checkbox_checked);
//                }else {
//                    mIv_CheckBox_head.setImageResource(R.drawable.checkbox_unchecked);
//                }
//                change = !change;
//                break;
        }
    }

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initViewsAndEvents(View view) {

    }


    /**
     * @param mActivity
     * @param helper
     * @param item
     * @param data
     */
    public static void doConvert(Activity mActivity, BaseViewHolder helper, MultiItemEntity item, List<MultiItemEntity> data) {

        if (item instanceof ReturnPlatform) {
            //do conevrt 0
            helper.setText(R.id.order_header, ((ReturnPlatform) item).getPlatformName());
            helper.setText(R.id.order_time, ((ReturnPlatform) item).getOrderTime());
            helper.setText(R.id.rich, ((ReturnPlatform) item).getEstimatedRebate());


        } else if (item instanceof ReturnShop) {
            //do conevert 1
            helper.setText(R.id.order_tv, ((ReturnShop) item).getShopName());
            helper.addOnClickListener(R.id.checkbox_head);


            boolean isFirstShop = isFirstShop(mActivity, helper, item, data);
//            helper.setVisible(R.id.toplhine, isFirstShop);
//            helper.getView(R.id.topline).setVisibility(isFirstShop ? View.GONE : View.VISIBLE);

            if (isFirstShop) {
                helper.getView(R.id.topline).setVisibility(View.VISIBLE);
                helper.getView(R.id.topline).setBackgroundResource(R.drawable.shape_corner_left_top_right_top);
            } else {
                helper.getView(R.id.topline).setVisibility(View.GONE);
            }


            helper.getView(R.id.checkbox_head).setSelected(((ReturnShop) item).isChecked());

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


            helper.addOnClickListener(R.id.checkbox_context);
            helper.addOnClickListener(R.id.add_count);
            helper.addOnClickListener(R.id.delete_count);

            helper.getView(R.id.checkbox_context).setSelected(((ReturnOrder) item).isChecked());


            helper.setText(R.id.order_name, ((ReturnOrder) item).getOrderName());
            helper.setText(R.id.order_price, ((ReturnOrder) item).getCommodityPrice());
            helper.setText(R.id.count,((ReturnOrder) item).getShopNumber());

//            helper.getView(R.id.checkbox).setSelected(helper.getAdapterPosition() % 3 == 0);


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


    private static boolean isFirstShop(Activity mActivity, BaseViewHolder helper, MultiItemEntity item, List<MultiItemEntity> data) {
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
    protected void initData() {
        requestData();
    }


    public void requestData() {


    }


    @Override
    protected void onFirstUserVisible() {
        OkHttpUtils
                .get()
                .url("https://www.luocaca.cn/download/platform.txt")//平台数据列表
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

                        adapter.getData().clear();
                        adapter.addData(list);
                        adapter.expandAll();
                        adapter.notifyDataSetChanged();
                        Log.i("result", "onResponse: " + list);

                    }
                });

        recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        OrderAdapter orderAdapter = new OrderAdapter(new ArrayList()) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                Log.i(TAG, "convert: " + item);
                doConvert(mActivity, helper, item, getData());
            }
        };
        orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (orderAdapter.getData().get(position) instanceof ReturnShop) {

                    ReturnShop shop = ((ReturnShop) orderAdapter.getData().get(position));


                    //选中时  被点击
                    //子项全部 取消选中

                    List<ReturnOrder> ddd = shop.getSubItems();
                    for (ReturnOrder returnOrder : ddd) {
                        returnOrder.setChecked(!shop.isChecked());
                    }
                    shop.toggle();
                    adapter.notifyDataSetChanged();
                } else if (orderAdapter.getData().get(position) instanceof ReturnOrder) {

                    //商品点击
                    ReturnOrder order = ((ReturnOrder) orderAdapter.getData().get(position));
                    //find  parent poision
                    order.toggle();
                    adapter.notifyDataSetChanged();

                    boolean isFind = false;
                    int index = position;
                    while (!isFind) {
                        if (orderAdapter.getData().get(index) instanceof ReturnShop) {
                            isFind = true;
                        } else {
                            index--;
                        }
                    }

                    ReturnShop sho = ((ReturnShop) orderAdapter.getData().get(index));


                    boolean isAllCheck = true;
                    for (Object subItem : sho.getSubItems()) {
                        if (((ReturnOrder) subItem).isChecked()) {

                        } else {
                            isAllCheck = false;
                        }
                    }

                    sho.setChecked(isAllCheck);


                }


//                if (view.getId() == R.id.checkbox_head) {
////                    Object object = ReturnShop.class;
////                    if (object instanceof ReturnPlatform) {
////                        for (int i = 0; i < orderAdapter.getData().size(); i++) {
////                            Object shopName = ((ReturnShop) orderAdapter.getData().get(i)).getShopName();
////                            if (((ReturnShop) orderAdapter.getData().get(i)).getSubItems() == ((ReturnShop) orderAdapter.getData().get(position)).getSubItems()) {
////                                ((ReturnShop) orderAdapter.getData().get(i)).setChecked(((ReturnShop) orderAdapter.getData().get(i)).isChecked());
////                            }
////                        }
////                    }
//                    for (int i = 0; i < orderAdapter.getData().size(); i++) {
//                        if (orderAdapter.getData().get(i) instanceof ReturnShop) {
//                            List<ReturnOrder> ddd = ((ReturnShop) orderAdapter.getData().get(position)).getSubItems();
//                            for (int i1 = 0; i1 < ddd.size(); i1++) {
//                                Log.i(TAG, "type: " + (position + i1));
////                                    ((ReturnOrder) orderAdapter.getData().get(position + i1)).setChecked(true);
//                                ddd.get(i1).setChecked(true);
////
//                            }
//                        }
//                    }
//                    adapter.notifyDataSetChanged();
////                    view.setSelected(!view.isSelected());
//
//                }
//                if (view == view.findViewById(R.id.checkbox_context)) {
//                    view.setSelected(!view.isSelected());
//                    Log.i(TAG, "type: " + position);
//                }


                if (view.getId()==R.id.add_count){
                    acount++;
                    ((ReturnOrder) orderAdapter.getData().get(position)).setShopNumber(acount+"");
                }
                if(view.getId()==R.id.delete_count){
                    if(acount>1){
                        acount--;
                        ((ReturnOrder) orderAdapter.getData().get(position)).setShopNumber(acount+"");
                    }
                }
            }
        });
        recycleView.setAdapter(orderAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onFirstUserVisible();
                    }
                }, 3000);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

//    private boolean Check(ReturnShop returnShop) {
//
//        boolean isall = true;
//        for (Object subItem : returnShop.getSubItems()) {
//            isall = ((ReturnOrder) subItem).isChecked();
//            if (isall) {
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }

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
