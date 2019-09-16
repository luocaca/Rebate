package com.just.rebate.ui.activity.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
import com.just.rebate.ui.activity.web.web_util.HandlerUtil;
import com.just.rebate.ui.activity.web.web_util.MyClient;
import com.just.rebate.wedget.MyTitleBar;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * web 浏览器
 */
public class WebViewActivity extends BaseActivity {


    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    @BindView(R.id.web)
    WebView web;


    @BindView(R.id.title)
    MyTitleBar myTitleBar;

    @Override
    protected void requestData() {

    }

    @Override
    protected View bindTitleView() {
        return myTitleBar;
    }

    @Override
    protected void initView() {
        swipe.setOnRefreshListener(this::onRefresh);
        swipe.setProgressBackgroundColorSchemeColor(Color.RED);
        swipe.setColorSchemeColors(Color.GREEN);


//        initWebView();


        initWebClient();


    }

    private void initWebClient() {

        MyClient myClient = new MyClient(this, web, swipe, myTitleBar.title_tv, new HandlerUtil.HandlerHolder(new HandlerUtil.OnReceiveMessageListener() {
            @Override
            public void handlerMessage(Message msg) {
//                web.loadUrl("https://h5.m.taobao.com");
            }
        }));


        myClient.loadUrl("https://h5.m.taobao.com", null);

//        web.getSettings().setJavaScriptEnabled(true);
//        web.getSettings().setAppCacheEnabled(true);
//        web.getSettings().setDatabaseEnabled(true);
//        web.getSettings().setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
//        web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//
//        web.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                if (url.startsWith("http") || url.startsWith("https")) {
//                    view.loadUrl(url);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        web.loadUrl("https://h5.m.taobao.com");
    }

    @Override
    public int bindLayoutId() {
        return R.layout.activity_web;
    }

    public void onRefresh() {
        web.reload();
    }


    @Override
    public void onBackPressed() {
        if (web.canGoBack()) {
            web.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, WebViewActivity.class);
        context.startActivity(starter);
    }


    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void initWebView() {
        WebSettings settings = web.getSettings();
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
//        settings.setUserAgentString("Mozilla/5.0(Linux; U; Android 8.1.0; zh-cn; Redmi 6A Build/O11019) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.128 Mobile Safari/537.36 XiaoMi/MiuiBrowser/10.5.1");

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

        web.setVerticalScrollBarEnabled(false);//不能垂直滑动
        web.setHorizontalScrollBarEnabled(false);//不能水平滑动
        web.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
//                LogUtil.e("url", url);
//                LogUtil.e("userAgent", userAgent);
//                LogUtil.e("contentDisposition", contentDisposition);
//                LogUtil.e("mimeType", mimeType);
//                LogUtil.e("contentLength", String.valueOf(contentLength));
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse(url));
//                context.startActivity(intent);
            }
        });
        // 将Android里面定义的类对象AndroidJs暴露给javascript
//        web.setWebViewClient(this);
        //        webView.addJavascriptInterface(new AndroidJavaScript(context), "AndroidJs");
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
//                    pb.setVisibility(View.GONE);
                    swipe.setRefreshing(false);
                } else {
//                    pb.setVisibility(View.VISIBLE);
//                    pb.setProgress(newProgress);
                    swipe.setRefreshing(true);

                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                myTitleBar.title_tv.setText(title);
            }
        });


        web.loadUrl("https://h5.m.taobao.com");
    }


}
