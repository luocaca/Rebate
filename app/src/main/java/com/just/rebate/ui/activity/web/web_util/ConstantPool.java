package com.just.rebate.ui.activity.web.web_util;


/**
 * Created by Administrator on 2018/1/26 0026.
 */

public final class ConstantPool {

    //    public static final String HOST_URL = "http://192.168.1.6:8089/";
//    public static final String HOST_URL = "http://2h613s6738.zicp.vip:29179/";
    public static final String HOST_URL = "http://192.168.1.6:8088/";
//    public static final String HOST_URL = "http://192.168.1.169:2126/";

    public static final String KEY_BACK_URL = "backUrl";//拦截的跳转地址
    public static final String KEY_HOME_URL = "home_url";//主页
    public static final String KEY_PAY_URL = "pay_url";//充值
    public static final String KEY_PAYS_URL = "pay_urls";//拦截列表
    public static final String KEY_WEB_URL = "web_url";


    public static final String PAY_KEY_MCH_KEY = "pay_key_mch_key";


    public static final String KEY_URL_REFERER = "Referer";//请求头
    public static final String KEY_USER_AGENT = "User-Agent";//User-Agent
    public static final String KEY_COOKIES = "ck";// 哭可
    public static final String KEY_WEB_PAGE = "webPage";// 是否显示webview


    public static final String IS_OPEN_HOME = "isOpenHome";
    public static final String IS_OPEN_WEB = "isOpenWeb";
    public static final String IS_OPEN_PAY = "isOpenPay";
    public static final String IS_OPEN_MY = "isOpenMy";


    public static final String KEY_WEB_TYPE = "open_web_type";//
    public static final String KEY_PAY_TYPE = "payType";//支付方式（微信或者支付宝等等）
    public static final String KEY_ORDERNO = "orderNo";//支付方式（微信或者支付宝等等）
    public static final String KEY_APP_PAY_URL = "apppPayUrl";//二维码支付接口
    public static final String KEY_START_ACT = "startAct";//二维码支付接口

    public final static int WEB_RECHARGE = 2001;
    public final static int WEB_HOME = 2002;
    public final static int WEB_HANDLEURL = 2003;
    public final static int WEB_REPLACE_CONTENT = 2004;

    public final static int START_FZHIFU = 1000;//处理fzhifu请求头
    public final static int START_APP = 1003;//打开app
    public final static int START_URL = 1004;//打开网址


    /**
     * 浏览器界面显示  充值按钮 或者 底部输入框
     */
    public static final String IS_GO_URL = "isGoUrl";
    public static final String IS_SHOW_PAY = "isShowPay";


}
