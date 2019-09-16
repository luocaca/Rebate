package com.just.rebate.ui.activity.web.web_util;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


public class CookieHelper {


    private CookieManager cookieManager;

    private CookieManager getCookieManager(Context context) {
        if (cookieManager == null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                CookieSyncManager.createInstance(context);
            }
            cookieManager = CookieManager.getInstance();
        }
        return cookieManager;
    }


    /**
     * 同步 Cookie
     */
    public void syncCookie(Context context, String url, String s) {
        try {
            CookieManager cookieManager = getCookieManager(context);
            cookieManager = removeAllCookie(cookieManager);
            cookieManager = setCokies(cookieManager, url, s);
            syncCookie(cookieManager);
        } catch (Exception e) {
            LogUtil.i(e.toString());
        }
    }

    /**
     * 清除 Cookie
     */
    public CookieManager removeAllCookie(CookieManager cookieManager) {
        try {
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();
            cookieManager.removeAllCookie();// Removes all cookies.
            return cookieManager;
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        return null;
    }

    /**
     * 设置 Cookie
     */
    public CookieManager setCokies(CookieManager cookieManager, String url, String cookies) {
        try {
            String[] strs = cookies.split("; ");
            for (String str : strs) {
                cookieManager.setCookie(url, str);
            }
            return cookieManager;
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        return null;

    }

    /**
     * 同步cookie
     */
    public void syncCookie(CookieManager cookieManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.flush();
        } else {
            CookieSyncManager.getInstance().sync(); // forces sync manager to sync now
        }
    }

}