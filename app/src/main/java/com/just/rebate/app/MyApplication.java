package com.just.rebate.app;

import android.app.Application;
import android.content.Context;

/**
 * 全局app
 */
public class MyApplication extends Application {
    public static  final String Host="";
    public static final String authorization="";


    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    private String host;
    private String Authorization;


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
        setHost(Host);
        setAuthorization(authorization);
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
