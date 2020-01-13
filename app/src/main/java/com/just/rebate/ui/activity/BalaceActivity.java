package com.just.rebate.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.BlanceMoenyData;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * title 提现
 */
public class BalaceActivity extends BaseActivity {
    //    private ImageView mIv_back;
    private MyApplication application;
    private List<BlanceMoenyData> blanceMoenyDatas = new ArrayList<>();

    @BindView(R.id.blance_to_cash)
    Button mBtn_Cash;

    @BindView(R.id.Accumulated_Moeny)
    TextView mTv_Accumulated_Moeny;


    protected int bindTitleViewId() {
        return R.id.title;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==175){
            Log.i("onActivityResult", "onActivityResult: 又请求了一次");
            requestData();
        }
    }

    @Override
    protected void requestData() {
        OkHttpUtils.post()
                .addHeader("Authorization", "Bearer " + application.getAuthorization())
                .url("http://192.168.1.190:12004/api/Admin/User/GetTotalAmountByApp")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 提现金额" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 提现金额" + response);
                        BlanceMoenyData blanceMoenyData = GsonUtil.getGsonLower().fromJson(response, BlanceMoenyData.class);
                        blanceMoenyDatas.clear();
                        blanceMoenyDatas.add(blanceMoenyData);
                        if (blanceMoenyData.ResultType == 3) {
                            mTv_Accumulated_Moeny.setText(blanceMoenyData.Data + "");
                        } else {
                            mTv_Accumulated_Moeny.setText(""+0);
                            Toast.makeText(application, "数据加载错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    protected void initView() {
        application = (MyApplication) getApplication();
        initOnClick();
    }

    private void initOnClick() {
        mBtn_Cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BalaceActivity.this, CashWithdrawalActivity.class);
                intent.putExtra("BlanceMoeny",mTv_Accumulated_Moeny.getText().toString());
                startActivityForResult(intent,150);
            }
        });

    }

    @Override
    public int bindLayoutId() {
        return R.layout.balance;
    }
}
