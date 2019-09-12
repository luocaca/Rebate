package com.just.rebate.ui.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.SectionAdapter;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.ui.activity.web.WebViewActivity;
import com.rebate.base.fragment.BaseFragment;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    private List<HomeItem> mData;


    @BindView(R.id.rv_list)
    RecyclerView recycleView;

    @BindView(R.id.rv_SwipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents(View view) {

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 3));
//        recycleView.addItemDecoration(new GridSectionAverageGapItemDecoration(10,10,20,15));
        List<HomeItem> homeItems = new ArrayList<>();

        mData = homeItems;

        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, HomeItem item) {
                helper.setText(R.id.tv, item.getItemName());
                Glide.with(getActivity()).load(item.getLogoUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.iv));

                helper.itemView.setOnClickListener(v -> {
                    WebViewActivity.start(mContext);
                });


                helper.getView(R.id.card_view).setBackgroundResource(item.bgId);


                //6 -  0
                //4 -  1
                //9 -  0
                //8 %3 -  8-2=6-1 = 7  8-1=7+1 =8
                //7 -  1

//                int toatleCount = 1;
//                int pos = helper.getAdapterPosition();
//                int currentPosAtGroup = 0;
//
//
//                while (pos > 0 && !getData().get(pos--).isHead()) {
//                    toatleCount++;
//                }
//                currentPosAtGroup = toatleCount - 1;
//
//                pos = helper.getAdapterPosition();
//                while (pos < getData().size() && !getData().get(pos++).isHead()) {
//                    toatleCount++;
//                }
//                toatleCount--;
//                toatleCount--;
//
//
//                int i = ((toatleCount - currentPosAtGroup) % 3);
//                int sishewuru = toatleCount;
//
//                if (toatleCount % 3 != 0) {
//                    sishewuru = toatleCount + (3 - toatleCount % 3);
//                    i = ((sishewuru - currentPosAtGroup) % 3);
//                }
//
//
//                if (sishewuru - currentPosAtGroup < 3) {
//                    if (i == 0) {
//                        Log.i(TAG, "convert: 右下角 " + sishewuru + "/ " + currentPosAtGroup);
//
//                        helper.getView(R.id.card_view).setBackgroundResource(R.drawable.shape_corner_right_bottom);
//
//
//                    } else if (i == 1) {
//                        Log.i(TAG, "convert:中间 " + sishewuru + "/ " + currentPosAtGroup);
//                        helper.getView(R.id.card_view).setBackgroundResource(R.color.white_text_color);
//                    } else {
//                        Log.i(TAG, "convert:左下角 " + sishewuru + "/ " + currentPosAtGroup);
//                        helper.getView(R.id.card_view).setBackgroundResource(R.drawable.shape_corner_left_bottom);
//
//                    }
//
//                } else {
//                    Log.i(TAG, "convert:不处理圆角 " + sishewuru + "/ " + currentPosAtGroup);
//                    helper.getView(R.id.card_view).setBackgroundResource(R.color.white_text_color);
//                }


            }

            @Override
            protected void convertHead(BaseViewHolder helper, HomeItem item) {
                super.convertHead(helper, item);
            }
        };


        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeItem mySection = mData.get(position);
                if (mySection.isHead())
                    Toast.makeText(mActivity, mySection.getItemName(), Toast.LENGTH_LONG).show();
            }
        });
        /*sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "onItemChildClick", Toast.LENGTH_SHORT).show();
            }
        });*/


        recycleView.setAdapter(sectionAdapter);


        requestDataOnline();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestDataOnline();
                    }
                }, 3000);
                swipeRefreshLayout.setRefreshing(false);
            }

        });

    }


    @Override
    protected void initData() {

    }


    /**
     * 网络请求数据
     */
    public void requestDataOnline() {


        //                .url("http://192.168.1.171:8080/download/personaljson.txt")

        OkHttpUtils
                .get()
                .url("http://192.168.1.171:8080/download/homejson.txt")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ///Log.e("TAG", "日志");


                        //Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();


                        Type t = new TypeToken<List<HomeItem>>() {
                        }.getType();

                        List<HomeItem> list = GsonUtil.getGson().fromJson(response, t);

                        if (mData == null) {
                            mData = new ArrayList<>();

                        }


                        setDataBg(list);
                        mData.clear();
                        mData.addAll(list);
                        recycleView.getAdapter().notifyDataSetChanged();


                    }
                });


    }


    public void ajkldjal(List<HomeItem> list) {

        //组的数量
        int groupCount = countGroup(list);

        for (int i = 0; i < groupCount; i++) {

        }


    }


    public int childCound(List<HomeItem> list, int groupIndex) {
        int toatleCount = 1;
        for (int i = 0; i < list.size(); i++) {
            toatleCount = 1;
            int pos = i;
            int currentPosAtGroup = 0;


            while (pos > 0 && !list.get(pos--).isHead()) {
                toatleCount++;
            }
            currentPosAtGroup = toatleCount - 1;

            pos = i;
            while (pos < list.size() && !list.get(pos++).isHead()) {
                toatleCount++;
            }
            toatleCount--;
            toatleCount--;
        }

        return toatleCount;


    }


    public int countGroup(List<HomeItem> list) {

        int count = 0;
        for (HomeItem homeItem : list) {
            if (homeItem.isHead())
                count++;
        }

        return count;

    }

    public void setDataBg(List<HomeItem> list) {

        String TAG = "setDataBg";

        for (int i = 0; i < list.size(); i++) {

            //不是头部
            if (!list.get(i).isHead()) {

                //6 -  0
                //4 -  1
                //9 -  0
                //8 %3 -  8-2=6-1 = 7  8-1=7+1 =8
                //7 -  1

                int toatleCount = 1;
                int pos = i;
                int currentPosAtGroup = 0;


                while (pos > 0 && !list.get(pos--).isHead()) {
                    toatleCount++;
                }
                currentPosAtGroup = toatleCount - 1;

                pos = i;
                while (pos < list.size() && !list.get(pos++).isHead()) {
                    toatleCount++;
                }
                toatleCount--;
                toatleCount--;


                int remainder = ((toatleCount - currentPosAtGroup) % 3);
                int sishewuru = toatleCount;

                if (toatleCount % 3 != 0) {

                    // toatleCount

                    sishewuru = toatleCount + (3 - toatleCount % 3);


                    remainder = ((sishewuru - currentPosAtGroup) % 3);


                }


                if (sishewuru - currentPosAtGroup < 3) {
                    if (remainder == 0) {
                        Log.i(TAG, "convert: 右下角 " + sishewuru + "/ " + currentPosAtGroup);

//                        helper.getView(R.id.card_view).setBackgroundResource();
                        list.get(i).bgId = R.drawable.shape_corner_right_bottom;

                    } else if (remainder == 1) {
                        Log.i(TAG, "convert:中间 " + sishewuru + "/ " + currentPosAtGroup);
//                        helper.getView(R.id.card_view).setBackgroundResource(R.color.white_text_color);
                        list.get(i).bgId = R.color.white_text_color;
                    } else {
                        Log.i(TAG, "convert:左下角 " + sishewuru + "/ " + currentPosAtGroup);
//                        helper.getView(R.id.card_view).setBackgroundResource(R.drawable.shape_corner_left_bottom);
                        list.get(i).bgId = R.drawable.shape_corner_left_bottom;

                    }

                } else {
                    Log.i(TAG, "convert:不处理圆角 " + sishewuru + "/ " + currentPosAtGroup);
//                    helper.getView(R.id.card_view).setBackgroundResource(R.color.white_text_color);

                    list.get(i).bgId = R.color.white_text_color;
                }


            }


        }


    }


}
