package com.just.rebate.ui.activity.web.listener;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

public interface OnWebViewFinishListener {


    void onPageFinish(WebView webView, String url);


    WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest);

}
