package com.just.rebate.ui.activity.web.web_util;

import java.util.HashMap;

public class ProxyList {
    private static HashMap<String, String> mMap;

    public static void setmMap(HashMap<String, String> mMap) {
        ProxyList.mMap = mMap;
    }

    public static HashMap<String, String> getList() {
        if (mMap == null) {
            synchronized (ProxyList.class) {
                if (mMap == null) {
                    mMap = new HashMap<String, String>();
                    // 可以从实际的配置中取，这是只是演示
                    mMap.put("www.baidu.com", "14.215.177.39");
                }
            }
        }
        return mMap;
    }
}