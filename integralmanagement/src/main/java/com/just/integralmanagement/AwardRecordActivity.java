package com.just.integralmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.just.integralmanagement.entity.AwardRecordDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.AwardRecordAdapter;
import application.ApplicationClass;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 奖励记录
 */

public class AwardRecordActivity extends AppCompatActivity {

    private RecyclerView mRv_AwardRecord;
    private int Id;
    private String Authorization = "";
    private ApplicationClass applicationClass;
    private List<AwardRecordDataBean.DataBean> awardRecordDataBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award_record);
        mRv_AwardRecord = findViewById(R.id.RV_award_record);
        applicationClass = (ApplicationClass) getApplication();
        initReceiveData();
        initData();
        initRecyclerview();
        setToolbars();
    }

    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setTitle("奖励记录");
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

    private void initReceiveData() {
        Intent intent = getIntent();
        Id = intent.getIntExtra("Id", 0);
        Authorization = intent.getStringExtra("Authorization");
        Log.i("AwardRecordActivity", "initReceiveData: " + Id + "  +   " + Authorization);
    }

    private void initRecyclerview() {
        mRv_AwardRecord.setLayoutManager(new LinearLayoutManager(this));
        AwardRecordAdapter awardRecordAdapter = new AwardRecordAdapter(awardRecordDataBeans, this);
        mRv_AwardRecord.setAdapter(awardRecordAdapter);
    }


    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + Authorization)
                .url("http://" + applicationClass.getHost() + "/api/Admin/TaskIntegralDetail/GetBonusIntegralForUser?UserId="+Id)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("登录错误", "onError: " + e);
                        Toast.makeText(applicationClass, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type t = new TypeToken<AwardRecordDataBean>() {
                        }.getType();
                        AwardRecordDataBean recordDataBean = GsonUtil.getGsonLower().fromJson(response, t);
                        awardRecordDataBeans.clear();
                        awardRecordDataBeans.addAll(recordDataBean.Data);
                        mRv_AwardRecord.getAdapter().notifyDataSetChanged();
                    }
                });
    }
}
