package me.luocaca.rebate.app;

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
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
