package com.just.integralmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.ActivityDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.SpecocalActivityAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 活动界面
 */

public class SpecialActivity extends AppCompatActivity {
    private ApplicationClass applicationClass;
    private RecyclerView mRv_activity;
    private List<ActivityDataBean.DataBean> activityDataBeans=new ArrayList<>();
    private String Authorization="";
    private int Id;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        applicationClass= (ApplicationClass) getApplication();
        mRv_activity=findViewById(R.id.mRv_activity);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_activity);
        getSupportActionBar().setTitle("活动中心");
        setToolbars();
        initReceiveData();
        initData();
        initRecyclerview();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initReceiveData() {
        Intent intent=getIntent();
        Authorization=intent.getStringExtra("Authorization");
        Id=intent.getIntExtra("Id",0);
    }

    private void initRecyclerview() {
        mRv_activity.setLayoutManager(new LinearLayoutManager(this));
        SpecocalActivityAdapter activityAdapter=new SpecocalActivityAdapter(activityDataBeans,this);
        mRv_activity.setAdapter(activityAdapter);
    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskActivity/GetTaskActivityListByApp")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<ActivityDataBean>() {
                        }.getType();
                        ActivityDataBean dataBean=GsonUtil.getGsonLower().fromJson(response,t);
                        activityDataBeans.clear();
                        activityDataBeans.addAll(dataBean.Data);
                        mRv_activity.getAdapter().notifyDataSetChanged();
                    }
                });
    }

    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);//这两句就可以让actionBar的图标可以响应点击事件
        mActionBar.setDisplayHomeAsUpEnabled(true);//这一句主要用于后面返回效果，后面会讲
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //id不要写错，前面要加android
                onBackPressed();
                break;
        }
        return true;
    }
}
