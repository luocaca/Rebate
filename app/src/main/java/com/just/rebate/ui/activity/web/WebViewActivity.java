package com.just.rebate.ui.activity.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.just.rebate.R;
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

        web.getSettings().setJavaScriptEnabled(true);


        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);


        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


//                    if (request.getUrl().toString().startsWith("tbopen://")) {
//                        return false;
//                    }
//
//                    if (request.getUrl().toString().startsWith("tmall://")) {
//                        return false;
//                    }

                    Log.i("scheme", "shouldOverrideUrlLoading: " + request.getUrl().getScheme());
                    Log.i("scheme", "shouldOverrideUrlLoading: " + request.getUrl());

                    if (request.getUrl().getHost().equals("http") || request.getUrl().getScheme().equals("https")) {


                        if (request.getUrl().toString().startsWith("https://s.click.taobao.com/t?e")) {
                            return false;
                        } else {
                            view.loadUrl(request.getUrl().toString());

                            return true;
                        }


                    } else {

//                        view.loadUrl(request.getUrl().toString().replace(request.getUrl().getScheme(), "http"));
                        return false;
                    }

                }


                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                swipe.setRefreshing(true);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                swipe.setRefreshing(false);
                super.onPageFinished(view, url);
            }


        });


        web.loadUrl("https://ai.m.taobao.com/");
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

}
