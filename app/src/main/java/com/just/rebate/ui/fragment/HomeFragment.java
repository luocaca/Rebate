package com.just.rebate.ui.fragment;

import android.os.HandlerThread;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.ui.activity.FailureToPayActivity;
import com.rebate.base.fragment.BaseFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import butterknife.BindView;
import okhttp3.Call;

import com.just.rebate.adapter.recycle.SectionAdapter;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 3));
//        recycleView.addItemDecoration(new GridSectionAverageGapItemDecoration(10,10,20,15));
        List<HomeItem> homeItems = new ArrayList<>();

        mData = homeItems;

        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, HomeItem item) {
                helper.setText(R.id.tv, item.getItemName());
                Glide.with(getActivity()).load(item.getLogoUrl()).into((ImageView) helper.getView(R.id.iv));

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


                        Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();


                        Type t = new TypeToken<List<HomeItem>>() {
                        }.getType();

                        List<HomeItem> list = GsonUtil.getGson().fromJson(response, t);

                        if (mData == null) {
                            mData = new ArrayList<>();

                        }

                        mData.clear();
                        mData.addAll(list);
                        recycleView.getAdapter().notifyDataSetChanged();


                    }
                });


    }


}
