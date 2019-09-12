package com.just.rebate.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.base.BaseResponse;
import com.just.rebate.entity.invite.InviteInfo;
import com.rebate.base.fragment.BaseLazyFragment;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.Type;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 邀请 fragment
 */
public class InviteFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    @BindView(R.id.Profit)
    TextView profit;

    @BindView(R.id.money)
    TextView money;

    @BindView(R.id.Partner)
    TextView partner;

    @BindView(R.id.Fans)
    TextView fans;

    @BindView(R.id.Code)
    TextView code;

    @BindView(R.id.Invitation)
    TextView invitation;


    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_invite;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        swipe.setOnRefreshListener(this::onRefresh);
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onFirstUserVisible() {


        requestData();


    }


    private String TAG = this.getClass().getSimpleName();

    /**
     * 请求网络
     */
    private void requestData() {


        OkHttpUtils
                .get()
                .url("http://192.168.1.171:8080/download/rebate/api/invite.txt")//邀请 信息
                .build()
                .execute(new Callback<BaseResponse<InviteInfo>>() {

                    @Override
                    public BaseResponse<InviteInfo> parseNetworkResponse(Response response, int id) throws Exception {
                        String json = response.body().string();

                        Log.i(TAG, "json: \n" + json);


                        Type t = new TypeToken<BaseResponse<InviteInfo>>() {
                        }.getType();


                        BaseResponse<InviteInfo> baseResponse = GsonUtil.getGson().fromJson(json, t);

                        return baseResponse;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onResponse(BaseResponse<InviteInfo> response, int id) {
                        Log.i(TAG, "onResponse: ");
                        profit.setText(response.getData().totalProfit);
                        money.setText(response.getData().timelyProfit);
                        partner.setText(response.getData().numberOfPartners);
                        fans.setText(response.getData().numberOfFans);
                        code.setText(response.getData().inviteCode);
                        invitation.setText(response.getData().shareEarn);





                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        swipe.setRefreshing(false);
                    }
                });


        /***
         *  .execute(new StringCallback() {
         *                     @Override
         *                     public void onError(Call call, Exception e, int id) {
         *                         Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
         *                     }
         *
         *                     @Override
         *                     public void onResponse(String response, int id) {
         *
         *                         Type t = new TypeToken<List<ReturnPlatform>>() {
         *                         }.getType();
         *
         *                         List<ReturnPlatform> list = GsonUtil.getGson().fromJson(response, t);
         *
         *
         *                         Log.i("result", "onResponse: " + list);
         *
         *
         *                     }
         *                 });
         */


    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void destroyViewAndThing() {

    }

    @Override
    public void onRefresh() {
        requestData();
    }
}
