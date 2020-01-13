package com.just.rebate.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
import com.just.rebate.adapter.recycle.BankCardAdapter;
import com.just.rebate.app.MyApplication;
import com.just.rebate.data.Bank_Card_DataServer;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * title 银行卡
 */
public class BankCardActivity extends BaseActivity {
    private MyApplication application;


    @BindView(R.id.rv_list8)
    RecyclerView recyclerView;


    @BindView(R.id.rv_SwipeRefresh)
    SwipeRefreshLayout rv_SwipeRefresh;

    @BindView(R.id.Add_Card)
    TextView mTv_AddBankCard;


    List<Bank_Card_DataServer.DataBean> mDataServer = new ArrayList<>();


    @Override
    protected void requestData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            Log.i("onActivityResult", "onActivityResult: 又请求了一次");
            initData();
        }
    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initRecyclerview();
        initData();
        initOnClick();
    }

    private void initOnClick() {
        mTv_AddBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BankCardActivity.this, AddBankCardActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_bank_card;
    }

    private void initRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BankCardAdapter mbankCardAdapter = new BankCardAdapter(mDataServer, this);
//        // recyclerView.addItemDecoration(new DetailedActivity.MyDecoration());
        recyclerView.setAdapter(mbankCardAdapter);


    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("ReceivingType", "" + 1);
        params.put("PayModeType", "" + 1);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .url("http://192.168.1.190:12004/api/Admin/PayMode/GetPayModeListByApp?receivingType=1")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 银行卡列表" + e);

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 银行卡列表" + response);
                        Bank_Card_DataServer bankcard = GsonUtil.getGsonLower().fromJson(response, Bank_Card_DataServer.class);
                        mDataServer.clear();
                        mDataServer.addAll(bankcard.Data);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
    }
}
