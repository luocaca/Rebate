package com.just.rebate.ui.activity;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.RechargeListAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.RechargeListData;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

public class RechargeListActivity extends BaseActivity {

    private List<RechargeListData.DataBean> rechargeListDatas = new ArrayList<>();
    private MyApplication application;

    @BindView(R.id.RechargeList)
    RecyclerView mRv_RechargeList;

    @BindView(R.id.Refresh)
    SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    initData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initData() {
        Map<String, String> parems = new HashMap<>();
        parems.put("orderType", "" + 0);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(parems))
                .url("http://192.168.1.190:12004/api/Admin/OrderRechargePay/GetOrderRechargePayByApp")
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 消费记录" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 消费记录" + response);
                        RechargeListData rechargeListData=GsonUtil.getGsonLower().fromJson(response,RechargeListData.class);
                        rechargeListDatas.clear();
                        rechargeListDatas.addAll(rechargeListData.getData());
                        initRecyclerviwe();
                    }
                });
    }

    private void initRecyclerviwe() {
        mRv_RechargeList.setLayoutManager(new LinearLayoutManager(this));
        RechargeListAdapter rechargeListAdapter = new RechargeListAdapter(rechargeListDatas);
        mRv_RechargeList.addItemDecoration(new MyDecoration());
        mRv_RechargeList.setAdapter(rechargeListAdapter);
        mRv_RechargeList.getAdapter().notifyDataSetChanged();
    }

    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_recharge_list;
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dibufengex));
        }
    }
}
