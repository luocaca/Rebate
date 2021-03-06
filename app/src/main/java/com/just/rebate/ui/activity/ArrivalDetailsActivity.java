package com.just.rebate.ui.activity;


import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.ArrivalDetailsAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.data.DataServer;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * title 到账明细
 */


public class ArrivalDetailsActivity extends BaseActivity {

    @BindView(R.id.rv_list3)
    RecyclerView mrecyclerView;

    @BindView(R.id.RefreshLayout)
    SwipeRefreshLayout mRefresh;

    private List<DataServer.DataBean> mDataServers = new ArrayList<>();
    private MyApplication application;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void requestData() {

    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initOnClick();
    }

    private void initOnClick() {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    initData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mRefresh.setRefreshing(false);
            }
        });
    }

    private void initData() {
        OkHttpUtils.post()
                .url("http://192.168.1.190:12004/api/Admin/DividendRebate/GetDividendRebateListByApp ")
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 到账明细" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("response", "response: 到账明细" + response);
                        DataServer dataServer= GsonUtil.getGsonLower().fromJson(response,DataServer.class);
                        mDataServers.clear();
                        mDataServers.addAll(dataServer.getData());
                        initRecyclerview();
                    }
                });
    }


    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.fragment_arrival_details;
    }


    private void initRecyclerview() {
        mrecyclerView = findViewById(R.id.rv_list3);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrivalDetailsAdapter mArrivalDetailsAdapter = new ArrivalDetailsAdapter(mDataServers);
        mrecyclerView.addItemDecoration(new MyDecoration());
        mrecyclerView.setAdapter(mArrivalDetailsAdapter);
        mrecyclerView.getAdapter().notifyDataSetChanged();
    }


    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dibufengex));
        }
    }
}
