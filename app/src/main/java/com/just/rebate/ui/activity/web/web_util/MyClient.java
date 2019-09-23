package com.just.rebate.ui.activity.web.web_util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.just.rebate.R;
import com.just.rebate.ui.activity.web.listener.OnWebViewFinishListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

public class MyClient extends WebViewClient {
    private Context context;
    private WebView webView;
    private SwipeRefreshLayout pb;
    //    private ProgressBar pb;
    private TextView tv;
    private HandlerUtil.HandlerHolder handlerHolder;


    private OnWebViewFinishListener onWebViewFinishListener;

    public void setOnWebViewFinishListener(OnWebViewFinishListener listener) {

        this.onWebViewFinishListener = listener;
    }

    public void removeWebViewFinishListener() {

        if (this.onWebViewFinishListener != null) {
            this.onWebViewFinishListener = null;
        }
    }


    /**
     * 访问网址
     */
    public MyClient(Context context, WebView webView, SwipeRefreshLayout pb, TextView tv, HandlerUtil.HandlerHolder handlerHolder) {
        //查询要拦截的数据

        this.context = context;
        this.webView = webView;
        this.pb = pb;
        this.tv = tv;
        this.handlerHolder = handlerHolder;
        initWebView();

    }

    /**
     * @param rUrl        请求地址
     * @param httpHeaders 请求头参数
     * @param cookie      cookie
     * @method loadUrl
     * @describe 加载网页
     * @retrun void
     */
    public void loadUrl(String rUrl, Map<String, String> httpHeaders, String cookie) {
        if (TextUtils.isEmpty(rUrl))
            return;
        LogUtil.e("web______初始化", rUrl);
        boolean wrrThread = new WebUtil().getWrrThread(handlerHolder, rUrl);
        if (wrrThread)
            return;
        if (!TextUtils.isEmpty(cookie)) {
            CookieHelper cookieHelper = new CookieHelper();
            cookieHelper.syncCookie(context, rUrl, cookie);

            String old = CookieManager.getInstance().getCookie(rUrl);

        }
        if (httpHeaders != null && !httpHeaders.isEmpty()) {
            loadUrl(rUrl, httpHeaders);
        } else {
            loadUrl(rUrl);
        }


    }

    /**
     * 添加请求头
     */
    @SuppressWarnings("unchecked")
    public void loadUrl(String url, String headers) {
        Map<String, String> extraHeaders = new HashMap<String, String>();
        extraHeaders.put(ConstantPool.KEY_URL_REFERER, headers);//固定请求头 来源头
        //        extraHeaders.put("test1", test1);//自定义属性
        //        extraHeaders.put("test2", test2);
        loadUrl(url, extraHeaders);
    }

    private void loadUrl(String url, Map<String, String> httpHeaders) {
        if (!TextUtils.isEmpty(httpHeaders.get("User-Agent"))) {
            webView.getSettings().setUserAgentString(httpHeaders.get("User-Agent"));
        }
        webView.loadUrl(url, httpHeaders);
    }

    /**
     * 加载网址Url 或者本地文件FileUrl
     */
    //         webView.loadUrl("file:///android_asset/xxx.html");
    private void loadUrl(String url) {
        if (TextUtils.isEmpty(url))
            return;
        webView.loadUrl(url);
    }


    /**
     * 加载H5代码
     */
    private void loadData(String data) {
        //设置图片适应手机
        data = "<!DOCTYPE HTML><html><head><style>img{ max-width:100%;width:100%;max-height:100%;" +
                "height:100%;}</style></head><body>" + data + "</body></html>";
        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }


    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void initWebView() {
        WebSettings settings = webView.getSettings();
        //        settings.setUseWideViewPort(true);//调整到适合webview的大小，不过尽量不要用，有些手机有问题
        settings.setLoadWithOverviewMode(true);//设置WebView是否使用预览模式加载界面。
        //        if (Build.VERSION.SDK_INT<+)
        settings.setTextSize(WebSettings.TextSize.NORMAL);//通过设置WebSettings，改变HTML中文字的大小
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
//        String ua = settings.getUserAgentString();//原来获取的UA
        //        settings.setUserAgentString(ua.replace("Android", "HFWSH_USER Android"));
//        settings.setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
//        settings.setUserAgentString("Mozilla/5.0。dd顶顶顶 (Linux; U; Android 8.1.0; zh-cn; Redmi 6A Build/O11019) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoMi/MiuiBrowser/10.5.1");

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        //        settings.setAllowFileAccess(true);// 设置允许访问文件数据

        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        //  settings.setBuiltInZoomControls(true);

        //设置代理
        //        String original = settings.getUserAgentString();// 获取 WebView 的 UserAgent
        //        original += " Version:" + "versionName" + ";";// 替换
        //        settings.setUserAgentString(original);// 设置新的 UserAgent添加UA,  “app/XXX”：是与h5商量好的标识，h5确认UA为app/XXX就认为该请求的终端为App

        //设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptEnabled(true);//设置js可用
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局

        webView.setVerticalScrollBarEnabled(false);//不能垂直滑动
        webView.setHorizontalScrollBarEnabled(false);//不能水平滑动
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                LogUtil.e("url", url);
                LogUtil.e("userAgent", userAgent);
                LogUtil.e("contentDisposition", contentDisposition);
                LogUtil.e("mimeType", mimeType);
                LogUtil.e("contentLength", String.valueOf(contentLength));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
        // 将Android里面定义的类对象AndroidJs暴露给javascript
        webView.setWebViewClient(this);
        //        webView.addJavascriptInterface(new AndroidJavaScript(context), "AndroidJs");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
//                    pb.setVisibility(View.GONE);
                    pb.setRefreshing(false);
                } else {
//                    pb.setVisibility(View.VISIBLE);
                    pb.setRefreshing(true);

//                    pb.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

           /* @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (TextUtils.isEmpty(title))
                    return;
                tv.setText(title);
            }*/
        });
    }

    /**
     * AndroidJavaScript
     * 本地与h5页面交互的js类，这里写成内部类了
     * returnAndroid方法上@JavascriptInterface一定不能漏了
     */
    private class AndroidJavaScript {
        Context mContxt;

        public AndroidJavaScript(Context mContxt) {
            this.mContxt = mContxt;
        }

        @JavascriptInterface
        public void showList() {
            new AlertDialog.Builder(context)
                    .setTitle("图书列表")
                    .setIcon(R.drawable.bg_white_bottom_line)
                    .setItems(
                            new String[]{"疯狂java讲义", "疯狂Android讲义",
                                    "轻量级java EE开发"}, null)
                    .setPositiveButton("确定", null).create().show();
        }

        @JavascriptInterface
        public void showToast() {

            Toast.makeText(context, "hello", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * 加载的内容
     * <p>
     * 1拦截 不访问 不加载
     * 2拦截 加载访问网址 不加载内容 替换内容
     *
     * @param request 加载的内容
     */
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        WebResourceResponse webResourceResponse = null;
        if (onWebViewFinishListener != null) {

            webResourceResponse = onWebViewFinishListener.shouldInterceptRequest(view, request);

            if (webResourceResponse != null) {
                return webResourceResponse;
            } else {
                return super.shouldInterceptRequest(view, request);
            }

        } else {
            return super.shouldInterceptRequest(view, request);

        }


    }

      /*  if (BuildConfig.DEBUG) {
            return super.shouldInterceptRequest(view, request);
        } else {
            return WebviewDnsInterceptUtil.getDnsInterceptRequest(view, request);
        }*/

    /**
     * 在每一次请求资源时，都会通过这个函数来回调
     * 1 拦截 不访问 不加载
     * 2 拦截 加载访问网址 不加载内容 替换内容
     */
    //加载内容的url
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        WebResourceResponse response = null;
        if (url.equals("https://m.zhuanzhuan.58.com/wechat/m-pay.html?payid=1164397587963390845&mchid=1001&webview=zzn&pageid=1610678272&tt=B5D9942D2C565B235F2F8F61D74D1B161566448994094&zzv=6.11.1")) {
            try {
//                InputStream in_nocode = new ByteArrayInputStream("abc".getBytes());
                InputStream in_withcode = new ByteArrayInputStream("abc".getBytes("UTF-8"));
                response = new WebResourceResponse("text/html", "UTF-8", in_withcode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return response;
    }
 /*if (BuildConfig.DEBUG) {
            return super.shouldInterceptRequest(view, url);
        } else {
            return WebviewDnsInterceptUtil.getDnsInterceptUrl(view, url);
        }*/

    /**
     * 拦截 url 跳转,在里边添加点击链接跳转或者操作
     * 1 拦截 不访问 不加载
     * 2 拦截 加载访问网址 不加载内容 替换内容
     * 3 不拦截
     * 4 拦截特殊网址 跳转APP
     */
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtil.e("shouldOverrideUrlLoading______点击", url);

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        //自动跳转的网址之拦截一次 访问主页
//        String backUrl = (String) SharedPreUtils.obtain(ConstantPool.KEY_BACK_URL, "");
//        if (!TextUtils.isEmpty(backUrl) && url.equalsIgnoreCase(backUrl)) {
//            SharedPreUtils.save(ConstantPool.KEY_BACK_URL, "");
//
//            String baseUrl = (String) SharedPreUtils.obtain(ConstantPool.KEY_HOME_URL, "");
//            view.loadUrl(baseUrl);
//            return true;
//        }

        //拦截过滤网址
        if (new WebUtil().getWrrThread(handlerHolder, url))
            return true;


        //非指定地址需要进行重载
        if (!url.startsWith("https://wx.tenpay.com/") && !url.startsWith("https://mapi.alipay.com/")) {
            view.loadUrl(url);
            return true;
        }

        return false;

    }

    /**
     * 重写此方法才能够处理在浏览器中的按键事件。
     * 是否让主程序同步处理Key Event事件，如过滤菜单快捷键的Key Event事件。
     * 如果返回true，WebView不会处理Key Event，
     * 如果返回false，Key Event总是由WebView处理。默认：false
     */
    //    @Override
    //    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //            LogUtil.d("shouldOverrideUrlLoading : " + request.getUrl().toString());
    //        }
    //        return super.shouldOverrideUrlLoading(view, request);
    //    }


    /**
     * 在开始加载网页时会回调
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        boolean wrrThread = new WebUtil().getWrrThread(handlerHolder, url);
        if (wrrThread)
            return;
        LogUtil.e("onPageStarted______开始加载", url);

        if (onWebViewFinishListener != null) {
            onWebViewFinishListener.onPageFinish(webView, url);
        }

        super.onPageStarted(view, url, favicon);
    }


    /**
     * 在结束加载网页时会回调
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        if (tv != null && !TextUtils.isEmpty(view.getTitle()))
            tv.setText(view.getTitle());
        //    获取Cookies
        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = cookieManager.getCookie(url);
        LogUtil.d("onPageFinished\n" + "url ：\n " + url + "\ncookies ：\n " + cookie + "\n");






//        cookieManager.setCookie(url,);
        printHead(view, url);


        super.onPageFinished(view, url);

    }


    public Map<String, Headers> headCaches = new HashMap<>();

    /**
     * 打印头部
     *
     * @param view
     * @param url
     */
    private void printHead(WebView view, String url) {

        if (headCaches.containsKey(url)) {
            LogUtil.d("缓存获取 onPageFinished\n" + headCaches.get(url).toString());
        } else {

            OkHttpUtils.get().url(url)
                    .build()
                    .execute(new Callback() {
                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            headCaches.put(url, response.headers());
                            LogUtil.d(" 网络获取 onPageFinished\n" + response.headers().toString());
                            return null;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(Object response, int id) {

                        }
                    });
        }


    }


    /**
     * 加载错误的时候会回调，在其中可做错误处理，比如再请求加载一次，或者提示404的错误页面
     * WebView view:当前的WebView实例
     * int errorCode：错误码
     * String description：错误描述
     */
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        //        webView.loadUrl("file:///android_asset/test.html");//加载返回错误时，重新加载错误页面
        handlerHolder.sendEmptyMessage(ConstantPool.WEB_HOME);
    }

    /**
     * 当接收到https错误时，会回调此函数，在其中可以做错误处理
     */
//    当使用HTTPS通信的网址（以https://开头的网站）出现错误时，就会通过onReceivedSslError回调通知过来
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        LogUtil.d("sslError:" + error.toString());
//      handler.cancel(); //默认的处理方式，WebView变成空白页
//        handler.proceed();//接受证书
//    handleMessage(Message msg); //其他处理
        super.onReceivedSslError(view, handler, error);
//
//    自定义处理
//                handlerHolder.sendEmptyMessage(ConstantPool.WEB_HOME);

    }

    /**
     * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次
     */
    //    @Override
    //    public void onLoadResource(WebView view, String url) {
    //        super.onLoadResource(view, url);
    //    }


    //    @Override
    //    public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
    //        super.onSafeBrowsingHit(view, request, threatType, callback);
    //    }

    /**
     * (WebView发生改变时调用)
     * 可以参考http://www.it1352.com/191180.html的用法
     */
    //    @Override
    //    public void onScaleChanged(WebView view, float oldScale, float newScale) {
    //        super.onScaleChanged(view, oldScale, newScale);
    //    }


    /**
     * 是否重发POST请求数据，默认不重发。
     */
    //    @Override
    //    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
    //        super.onFormResubmission(view, dontResend, resend);
    //    }

    /**
     * 更新访问历史
     */
    //    @Override
    //    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
    //        super.doUpdateVisitedHistory(view, url, isReload);
    //    }

    /**
     * 通知主程序输入事件不是由WebView调用。是否让主程序处理WebView未处理的Input Event。
     * 除了系统按键，WebView总是消耗掉输入事件或shouldOverrideKeyEvent返回true。
     * 该方法由event 分发异步调用。注意：如果事件为MotionEvent，则事件的生命周期只存在方法调用过程中，
     * 如果WebViewClient想要使用这个Event，则需要复制Event对象。
     */
    //    @Override
    //    public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
    //        super.onUnhandledKeyEvent(view, event);
    //    }

    /**
     * 通知主程序执行了自动登录请求。
     */
    //    @Override
    //    public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
    //        super.onReceivedLoginRequest(view, realm, account, args);
    //    }

    /**
     * 通知主程序：WebView接收HTTP认证请求，主程序可以使用HttpAuthHandler为请求设置WebView响应。默认取消请求。
     */
    //    @Override
    //    public void onReceivedHttpAuthRequest(WebView view,
    //                                          HttpAuthHandler handler, String host, String realm) {
    //        // TODO Auto-generated method stub
    //        super.onReceivedHttpAuthRequest(view, handler, host, realm);
    //    }

    /**
     * 通知主程序处理SSL客户端认证请求。如果需要提供密钥，主程序负责显示UI界面。
     * 有三个响应方法：proceed(), cancel() 和 ignore()。
     * 如果调用proceed()和cancel()，webview将会记住response，
     * 对相同的host和port地址不再调用onReceivedClientCertRequest方法。
     * 如果调用ignore()方法，webview则不会记住response。该方法在UI线程中执行，
     * 在回调期间，连接被挂起。默认cancel()，即无客户端认证
     */
    //    @Override
    //    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
    //        super.onReceivedClientCertRequest(view, request);
    //        LogUtil.d("onReceivedClientCertRequest");
    //    }

}