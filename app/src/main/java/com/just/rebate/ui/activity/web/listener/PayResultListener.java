package com.just.rebate.ui.activity.web.listener;


import okhttp3.ResponseBody;
import retrofit2.Call;

public interface PayResultListener {
    void onSucceed(String json);

    void onFailed(Throwable t, Call<ResponseBody> call);
}