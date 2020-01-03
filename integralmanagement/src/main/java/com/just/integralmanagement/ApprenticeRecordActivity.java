package com.just.integralmanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.integralmanagement.entity.DeviceBean;
import com.just.integralmanagement.entity.ErrorDataBean;
import com.just.integralmanagement.entity.ListAgentBean;
import com.just.integralmanagement.entity.TokenDataBean;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.DeviceManagementAdapter;
import application.ApplicationClass;
import okhttp3.Call;

/**
 * 收徒记录
 */

public class ApprenticeRecordActivity extends AppCompatActivity {
    private RecyclerView mRv_ApprenticeRecord;
    private List<TokenDataBean> tokenDataBeans = new ArrayList<>();
    private int Id;
    private String Authorization = "";
    private ApplicationClass applicationClass;
    private List<DeviceBean.DataBean> deviceBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apprentice_record);
        applicationClass = (ApplicationClass) getApplication();
        mRv_ApprenticeRecord = findViewById(R.id.RV_ApprenticeRecord);
        setToolbars();
        initReceiveData();
        initData();
    }


    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setTitle("收徒记录");
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

    //接收Fragment传过来的数据
    private void initReceiveData() {
        Intent intent = getIntent();
        Id = intent.getIntExtra("Id", 0);
        Authorization = intent.getStringExtra("Authorization");
    }

    //添加分割线
    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dibufengex));
        }
    }

    //网络请求及RecyclerView适配
    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://" + applicationClass.getHost() + "/api/Admin/User/AgentUserListByApp?UserId=" + Id)
                .addHeader("Authorization", "Bearer " + Authorization)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ErrorDataBean errorDataBean = GsonUtil.getGsonLower().fromJson(response, ErrorDataBean.class);
                        if (errorDataBean.getType() == 403) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(ApprenticeRecordActivity.this);
                            builder1.setMessage("" + errorDataBean.getContent());
                            builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder1.create();
                            builder1.show();
                        } else {
                            DeviceBean deviceBean = GsonUtil.getGsonLower().fromJson(response, DeviceBean.class);
                            deviceBeans.clear();
                            BaseQuickAdapter adapter = (BaseQuickAdapter) mRv_ApprenticeRecord.getAdapter();
                            adapter.getData().addAll(deviceBean.getData());
                            mRv_ApprenticeRecord.getAdapter().notifyDataSetChanged();
                        }
                    }
                });

        mRv_ApprenticeRecord.setLayoutManager(new LinearLayoutManager(ApprenticeRecordActivity.this));
        DeviceManagementAdapter deviceManagementAdapter = new DeviceManagementAdapter(new ArrayList()) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
                doConvert(helper, item, getData());

            }
        };
//        deviceManagementAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                if(view.getId()==R.id.ExPance){
//
//                }
//            }
//        });
        mRv_ApprenticeRecord.setAdapter(deviceManagementAdapter);
    }

    //为RecyclerView填充数据
    private void doConvert(BaseViewHolder helper, MultiItemEntity item, List<MultiItemEntity> data) {
        if (item instanceof DeviceBean.DataBean) {
            Log.i("收到请回复", "" + item.getItemType());
            helper.setText(R.id.Master, ((DeviceBean.DataBean) item).getParentName());
            helper.setText(R.id.DiscipleName, ((DeviceBean.DataBean) item).getUserName());
            String []s=null;
            s=((DeviceBean.DataBean) item).getCreatedTime().split(" ",0);
            helper.setText(R.id.ApprenticeTime,s[0]);
            helper.setText(R.id.HoldIntegral, ((DeviceBean.DataBean) item).getTotalIntegral() + "");
            helper.setText(R.id.ContributionIntegral, ((DeviceBean.DataBean) item).getDivideIntegral() + "");
            helper.addOnClickListener(R.id.ExPance);
            Log.i("收到请回复", "" + item.getItemType()+"   "+helper.getAdapterPosition());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = helper.getAdapterPosition();
                    if (((DeviceBean.DataBean) item).isExpanded()) {
                        ((DeviceManagementAdapter) mRv_ApprenticeRecord.getAdapter()).collapse(pos, false);
                    } else {
                        ((DeviceManagementAdapter) mRv_ApprenticeRecord.getAdapter()).expand(pos, false);
                    }
                }
            });
        } else if (item instanceof ListAgentBean) {
            Log.i("收到请回复", "" + item.getItemType());
            helper.setText(R.id.Disciplename, ((ListAgentBean) item).getUserName());
            helper.setText(R.id.master, ((ListAgentBean) item).getParentName());
            String []s1=null;
            s1=((ListAgentBean) item).getCreatedTime().split(" ",0);
            helper.setText(R.id.Apprenticetime, s1[0]);
            helper.setText(R.id.Holdintegral, ((ListAgentBean) item).getTotalIntegral() + "");
            helper.setText(R.id.Contributionintegral, ((ListAgentBean) item).getDivideIntegral() + "");
        }
    }
}
