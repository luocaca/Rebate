package com.just.rebate.app;

import android.app.Application;
import android.content.Context;

/**
 * 全局app
 */
public class MyApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();


        System.setProperty("http.proxyHost", "192.168.1.171");
        System.setProperty("http.proxyPort", "8888");


    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
