package com.just.rebate.ui.activity.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
import com.just.rebate.ui.activity.web.web_util.HandlerUtil;
import com.just.rebate.ui.activity.web.web_util.LogUtil;
import com.just.rebate.ui.activity.web.web_util.MyClient;
import com.just.rebate.wedget.MyTitleBar;
import com.rebate.base.activity.BaseActivity;

import butterknife.BindView;

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

        //这个在MyClient已经调用，使用这个会导致调用cookie的时候跳出淘宝登录
//        initWebView();


        initWebClient();



    }





    private void initWebClient() {


//        String url = "http://auth.alipay.com/w/applyPeerPay.do?tcode=eyJiaXpPcmRlcklkcyI6IjU2MTA4Njg4NjU3NzczNTA5NiIsImJ1eWVySWQiOiIzMTg5NzM5NjUwIiwidHlwZSI6IjMifQ%3D%3D&alipay_trade_no=2019101222001170540598592116&s_id=5c3df21b48a7562f9e008f6da355b145&return_url=https%253A%252F%252Fmarket.m.taobao.com%252Fapps%252Fmarket%252Ftrade%252Findex.html%253Fwh_weex%253Dtrue%2526wx_navbar_transparent%253Dtrue%2526orderIds%253D561086886577735096%2526degrade%253D0%2526act%253Dfalse%3Fspm%3Da220l.7770993.0.i0&pay_order_id=561086886577735096";
        String url ="https://h5.m.taobao.com";
//        String url="https://auth.alipay.com/w/applyPeerPay.do?tcode=eyJiaXpPcmRlcklkcyI6IjU2MTA2NzQ2MzYwODczNTA5NiIsImJ1eWVySWQiOiIzMTg5NzM5NjUwIiwidHlwZSI6IjMifQ%3D%3D&alipay_trade_no=2019101222001170540500764605&s_id=1bc64c2622e327d888fa82b1fc6a358b&return_url=https%253A%252F%252Fmarket.m.taobao.com%252Fapps%252Fmarket%252Ftrade%252Findex.html%253Fwh_weex%253Dtrue%2526wx_navbar_transparent%253Dtrue%2526orderIds%253D561067463608735096%2526degrade%253D0%2526act%253Dfalse&pay_order_id=561067463608735096";
        MyClient myClient = new MyClient(this, web, swipe, myTitleBar.title_tv, new HandlerUtil.HandlerHolder(new HandlerUtil.OnReceiveMessageListener() {
            @Override
            public void handlerMessage(Message msg) {
                web.loadUrl(url);
            }
        }));


        // .addHeader("cookie", "")
        //       String cookies = "_m_h5_tk_enc=e3f7d58860b22cace169e93099afba43; ockeqeudmj=uC2m2qk%3D; munb=652569484/unb=652569484; uc3=id2=VWojfHWaPLQP&lg2=U%2BGCWk%2F75gdr5Q%3D%3D&vt3=F8dByuK3QCO2SogCkuU%3D&nk2=D9ZMJf0xxB5b5t5x; uc1=cookie15=URm48syIIVrSKA%3D%3D&cookie14=UoTaEC%2BTitGYAg%3D%3D&cookie21=Vq8l%2BKCLjA%2Bl; csg=15d38b91; lgc=luochaojunaa; ntm=0; cookie17=VWojfHWaPLQP; dnk=luochaojunaa; skt=c1802f1fe6d4bf10; tracknick=luochaojunaa; _cc_=WqG3DMC9EA%3D%3D; _l_g_=Ug%3D%3D; sg=a49; _nk_=luochaojunaa; cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D; isg=BKSkEhzopZNTCdElm04vuboFfqBWlcnNCoVvhb7FMG8yaUQz5k2YN9rHLIco3gD_";

//      myClient.loadUrl("https://h5.m.taobao.com",cookies);
//      myClient.loadUrl("https://h5.m.taobao.com",null,CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
//        myClient.loadUrl("https://h5.m.taobao.com",CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
        myClient.loadUrl(url, CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
        LogUtil.i("cookiesssss", CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
//        myClient.loadUrl("https://list.tmall.com",cookies);
//        myClient.loadUrl("https://www.tmall.hk",cookies);


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

//        web.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                web.loadUrl(url);
//                return true;
//            }
//        });


        web.loadUrl("https://h5.m.taobao.com");
    }


}
