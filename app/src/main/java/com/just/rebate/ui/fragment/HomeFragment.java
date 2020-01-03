package com.just.rebate.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.adapter.recycle.HomeExpandableItemAdapter;
import com.just.rebate.entity.GetRuleData;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.PlatformsBean;
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
 * title 首页
 */
public class HomeFragment extends BaseFragment {
    private List<MultiItemEntity> mData = new ArrayList<>();
    private String Adress = "";
    private List<GetRuleData> getRuleData = new ArrayList<>();


    @BindView(R.id.rv_list)
    RecyclerView recycleView;

    @BindView(R.id.rv_SwipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    private HomeExpandableItemAdapter baseViewHolder;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));


//            SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData) {
//            @Override
//            protected void convert(@NonNull BaseViewHolder helper, HomeItem item) {
//
//                System.out.println("--head--");
//                System.out.println("--no head--");
//
////                helper.setText(R.id.tv, item.Platforms.get(0).Name);
////                Glide.with(getActivity()).load(item.Platforms.get(0).Logo).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.iv));
////                helper.itemView.setOnClickListener(v -> {
////                    WebViewActivity.start(mContext);
////                });
//            }


//            @Override
//            protected void convertHead(BaseViewHolder helper, HomeItem item) {
//                helper.setText(R.id.header, item.Name);
//            }
//        };
//        HomeItem homeItem = new HomeItem();

//        homeItem.setExpanded(true);
//        ArrayList<PlatformsBean> objects = new ArrayList<>();
//        objects.add(new PlatformsBean() {
//            @Override
//            public int getItemType() {
//                return 1;
//            }
//
//
//        });
//        objects.add(new PlatformsBean());
//        homeItem.Platforms = objects;
//        mData.add(homeItem);
        baseViewHolder = new HomeExpandableItemAdapter(mData);
//


        recycleView.setAdapter(baseViewHolder);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {


                MultiItemEntity multiItemEntity = baseViewHolder.getData().get(position);
                System.out.println(position);
                System.out.println(position + multiItemEntity.getItemType());
                return (multiItemEntity).getItemType() == 1 ? 1 : manager.getSpanCount();
            }
        });
        recycleView.setLayoutManager(manager);

        baseViewHolder.expandAll();
        baseViewHolder.notifyDataSetChanged();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestDataOnline();
                    }
                }, 0);
                swipeRefreshLayout.setRefreshing(false);
            }

        });

    }


    @Override
    protected void initData() {
        try {
            requestDataOnline();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 网络请求数据
     */
    public void requestDataOnline() {
        //                .url("http://192.168.1.171:8080/download/personaljson.txt")
        OkHttpUtils
                .post()
                .url("http://192.168.1.137:7001/api/Admin/Platform/Platforms")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();
                        Log.i("onResponse", "onResponse: " + response);
                        Type t = new TypeToken<List<HomeItem>>() {
                        }.getType();

                        List<HomeItem> list = GsonUtil.getGsonLower().fromJson(response, t);


//                        setDataBg(list);
                        baseViewHolder.getData().clear();
//                        mData.addAll(list);
                        baseViewHolder.getData().addAll(list);


                        Log.i("onResponse", "onResponse: " + mData.size());
                        recycleView.getAdapter().notifyDataSetChanged();

                        baseViewHolder.expandAll();

                    }
                });
    }


}
