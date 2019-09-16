package com.just.rebate.ui.activity.web.web_util;

import android.text.TextUtils;
import android.util.Log;


public class LogUtil {


    public static boolean DEBUG = true;

    public static void i(String tag, String s) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(s))
            return;
        if (DEBUG) {
            Log.i(tag, s);
        }
    }

    public static void e(String tag, String s) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(s))
            return;
        if (DEBUG) {
            Log.e(tag, s);
        }
    }

    public static void e(Class cls, String s) {
        if (cls == null || TextUtils.isEmpty(s))
            return;
        if (DEBUG) {
            Log.e(cls.getName(), s);
        }
    }

    public static void d(String tag, String s) {
        if (TextUtils.isEmpty(tag) || TextUtils.isEmpty(s))
            return;
        if (DEBUG) {
            Log.d(tag, s);
        }
    }


    /**
     * 获取打印信息所在方法名，行号等信息
     *
     * @return
     */
    private static String[] getAutoJumpLogInfos() {
        String[] infos = new String[]{"", "", ""};
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length < 5) {
            Log.e("MyLogger", "Stack is too shallow!!!");
            return infos;
        } else {
            infos[0] = elements[4].getClassName().substring(elements[4].getClassName().lastIndexOf(".") + 1);
            infos[1] = elements[4].getMethodName() + "()";
            infos[2] = " at (" + elements[4].getClassName() + ".java:" + elements[4].getLineNumber() + ")";
            return infos;
        }
    }


    //    自定义Log：
    //        Log.e("kevin", ((StackTraceElement)(new Throwable().getStackTrace()[0])).getFileName() + ": Line " + ((StackTraceElement)(new Throwable().getStackTrace()[0])).getLineNumber());

    public static void v(String msg) {
        if (DEBUG) {
            String[] infos = getAutoJumpLogInfos();
            Log.v(infos[0], msg + " : " + infos[1] + infos[2]);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            String[] infos = getAutoJumpLogInfos();
            Log.d(infos[0], msg + " : " + infos[1] + infos[2]);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            String[] infos = getAutoJumpLogInfos();
            Log.i(infos[0], msg + " : " + infos[1] + infos[2]);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            String[] infos = getAutoJumpLogInfos();
            Log.w(infos[0], msg + " : " + infos[1] + infos[2]);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            String[] infos = getAutoJumpLogInfos();
            Log.e(infos[0], msg + " : " + infos[1] + infos[2]);
        }
    }
}
