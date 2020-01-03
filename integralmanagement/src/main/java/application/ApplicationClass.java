package application;

import android.app.Application;
import android.content.Context;

public class ApplicationClass extends Application {
    public static  final String Host="192.168.1.190:7003";
    private String host;
    public  static Application INSTANCE ;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        INSTANCE = this;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        setHost(Host);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
