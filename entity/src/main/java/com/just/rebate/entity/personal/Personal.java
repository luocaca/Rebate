package com.just.rebate.entity.personal;

import android.util.Log;

import com.rebate.commom.util.GsonUtil;

/**
 * 个人信息界面
 */
public class Personal {


    private static final String TAG = "Personal";

    public String headImage;


    public String invitationCode;


    public String account;


    public String integral;


    public static void main(String... args) {
        Personal personal = new Personal();
        personal.headImage = "https://ss1.baidu.com/-4o3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=a9e671b9a551f3dedcb2bf64a4eff0ec/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg";
        personal.invitationCode = "123456";
        personal.account = "1314520";
        personal.integral = "666";


        Log.i(TAG, "main: " + GsonUtil.getGson().toJson(personal));

    }

}
