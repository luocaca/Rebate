package com.just.rebate.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.GetRouteData;
import com.just.rebate.entity.LoginingDataBean;
import com.just.rebate.entity.WebSocketClientBean;
import com.just.rebate.entity.WebSocketClientPayBean;
import com.just.rebate.ui.MainActivity;
import com.just.rebate.ui.activity.Socket_Utils.CatMessageOuterClass;
import com.just.rebate.ui.activity.Socket_Utils.WebSocketsConnection;
import com.just.rebate.ui.activity.web.web_util.SPUtil;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class PassWordSignActivity extends AppCompatActivity {
    private EditText mTv_AccountNumber, mTv_PassWordNumber;
    private Button mBtn_YZMlogin, mBtn_sign;
    private String Authorization = "";
    private String Route = "";
    private Timer timer = new Timer();
    private TimerTask task, task1;
    private ImageView mIv_Back;
    private boolean have;
    private String responseHeaders;
    private Map<String, String> map = new HashMap<>();
    private String HTMLweb;
    private String NewString;
    private List<WebSocketClientBean> clientBeans = new ArrayList<>();
    private MyApplication application;
    private String Url, RequestPacket, ResponsePacket;
    private WebSocketsConnection webSocketsConnection;
    private List<LoginingDataBean> loginingDataBeans = new ArrayList<>();
    private List<GetRouteData> getRouteDatas = new ArrayList<>();
    private List<WebSocketClientBean> webSocketClientBeans = new ArrayList<>();
    private List<WebSocketClientPayBean> payBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mTv_AccountNumber = findViewById(R.id.Account_Number);
        mTv_AccountNumber.setText(getAccount(PassWordSignActivity.this));
        mTv_PassWordNumber = findViewById(R.id.PassWord_Number);
        mTv_PassWordNumber.setText(getpassword(PassWordSignActivity.this));
        mBtn_YZMlogin = findViewById(R.id.btn_YZM_login);
        mBtn_sign = findViewById(R.id.btn_sign);
        mIv_Back = findViewById(R.id.back);
        application = (MyApplication) getApplication();
        mBtn_YZMlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassWordSignActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
        mIv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mBtn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    initData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initWebData(String stringBody) {
        if (stringBody.contains("Pong")) {
            Log.i("initWebData", "initWebData: 现在是心跳" + stringBody);
        } else {
            Log.i("initWebData", "initWebData: 现在开始执行发送");
//            String orderDetai = "{\"Version\":\"HTTP/1.1\",\"Mothed\":\"GET\",\"Url\":\"https://wqdeal.jd.com/bases/orderdetail/detailview?deal_id=107646720867&callersource=mainorder&callback=detailFirCbA&traceid=799109821516399007&t=1576228627817&g_ty=ls&g_tk=5381&isoldpin=0&sceneval=2\",\"Headers\":{\"Host\":\" wqdeal.jd.com\",\"Connection\":\" keep-alive\",\"User-Agent\":\" Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1\",\"Accept\":\" */*\",\"Referer\":\" https://wqs.jd.com/order/n_detail_v2.shtml?deal_id=107646720867&isoldpin=0&sceneval=2\",\"Accept-Encoding\":\" gzip, deflate, br\",\"Accept-Language\":\" zh-CN,zh;q=0.9\",\"Cookie\":\"  __jdv=122270672%7Cdirect%7C-%7Cnone%7C-%7C1576029038060; shshshfpa=8cf62d16-d5e2-0224-5cb8-95c5a8f8111a-1576029041; shshshfpb=rPZYFgXpQ9Wcu2Xm%20R0oH5Q%3D%3D; __jdu=15760290380591392053442; areaId=16; ipLoc-djd=16-1315-46764-0; mt_xid=V2_52007VwMWVFpZUVIeSxBdAW8DEFpeWVZYGkwabAZiBRAGCAwGRhpKEVQZYlAaBkELAghPVR0LUG4DFQcOC1MJH3kaXQVvHxJRQVhSSx9NElwEbAIUYl9oUmofTBxVAWMHE1NdaFJZHEw%3D; TrackID=1HslKUJspbARLRn-pw2enlCGj64Po4l_sfgsbmYVeX4rWVMHNxLioSP0pbfFzeK5SgTz6Rr9z_Uqrwkx2dZj4vXDesjc71Rr5FrqI6UikaEc; pinId=4_oA54fIQ_QRF8rUzD4kDQ; pin=jd_diVzyybqdfNZ; unick=jd_diVzyybqdfNZ; _tp=xbRMKVQ0f3QF8UJbN9AptA%3D%3D; _pst=jd_diVzyybqdfNZ; user-key=679279ab-98ce-4668-af73-ea1307e5aba5; cn=0; __jdc=76161171; wxa_level=1; retina=1; webp=1; mba_muid=15760290380591392053442; visitkey=61430029752575648; shshshfp=48ca79102687c8110ba52ad03a93ce25; TrackerID=tiEKEXjHYhvWSUG3m0q5jB2XIbx5ZXSzBKUGmTvo3hZHJKZmAiM336r3XlXnIiM6bikYSyGFelZoF1y6pxbp03vfQdkxdX5wfKOvvQjzqjOZtorDhvNDZLnsbbybySbB89piW89JOT1qdjNA50wxpQ; pt_key=AAJd8ablADCMIBHb6TToEba4WwCyJ0ktx4KW7ikKUYGLLqp5aoyI-318W1k6vhIozbrYmSw8-Cg; pt_pin=jd_diVzyybqdfNZ; pt_token=rgecm28m; pwdt_id=jd_diVzyybqdfNZ; cid=9; sc_width=375; jdAddrId=18_1482_48936_53640; jdAddrName=%u6E56%u5357_%u957F%u6C99%u5E02_%u5CB3%u9E93%u533A_%u6885%u6EAA%u6E56%u8857%u9053; mitemAddrId=18_1482_48936_53640; mitemAddrName=%u6E56%u5357%u957F%u6C99%u5E02%u5CB3%u9E93%u533A%u6885%u6EAA%u6E56%u8857%u9053%u54B8%u5609%u6E56%u897F%u8DEF%u7EAF%u78E8%u574A%u5BA2%u5BB6%u8C46%u8150; wqmnx=1bcd1c43jdm356a3e3a6hpea2b4e4130; addrId_1=1843595148; addrType_1=1; wq_addr=1843595148%7C18_1482_48936_53640%7C%u6E56%u5357_%u957F%u6C99%u5E02_%u5CB3%u9E93%u533A_%u6885%u6EAA%u6E56%u8857%u9053%7C%u6E56%u5357%u957F%u6C99%u5E02%u5CB3%u9E93%u533A%u6885%u6EAA%u6E56%u8857%u9053%u54B8%u5609%u6E56%u897F%u8DEF%u7EAF%u78E8%u574A%u5BA2%u5BB6%u8C46%u8150%7C112.882004%2C28.178633; __tak=84924eb9f0d48418c7d096b730ea3494bc4db16f87308d44217b94ba3ee091c6d1b8dc6f8d30d4185f8d8d50607dd76a6dcfa67449d474f4eb36f5828e3dc50c2e7af42a8ee0ed3378afd1525e0729f2; cd_eid=HZWXSRPPT4DMLN4MEP7LGZVRPPUFH6DCGBABTNQTLKPMN2DWVBRCWNUMGS6ZWSQOBTK6WQK5SFEKYLCLL6RG3X2UTI; cartNum=0; __jda=76161171.15760290380591392053442.1576029038.1576220773.1576227699.11; PPRD_P=UUID.15760290380591392053442-CT.138624.10002.130008-LOGID.1576227715759.1360262672; sk_history=7671973%2C4640491%2C; _modc=098f6bcd4621d373cade4e832627b4f6; string123=7FC268B8A97F71877A3B4C7DF4C260B6H*1348LJ%25; cartLastOpTime=1576227717; wq_logid=1576227717_1804289383; wqmnx1=MDEyNjM4M3Blb2NybmE9aCVlY283aG5DMXRvbTYxM2xpOCZsbTQyby5uaVNpT3B0M0xHZTFsMi81ZlUyVk8pKCk%3D; __jdb=76161171.8.15760290380591392053442|11.1576227699; mba_sid=15762276986837127137653864646.8; __wga=1576227720228.1576227699420.1576220773350.1576117991654.6.8; shshshsID=6213739e7debb1fc2461f1dd4dbb4c7a_6_1576227720553; 3AB9D23F7A4B3C9B=HZWXSRPPT4DMLN4MEP7LGZVRPPUFH6DCGBABTNQTLKPMN2DWVBRCWNUMGS6ZWSQOBTK6WQK5SFEKYLCLL6RG3X2UTI; __jd_ref_cls=MNeworder_SubmitProductSku\"},\"Content\":\"\"}";
            WebSocketClientBean json = GsonUtil.getGsonLower().fromJson(stringBody, WebSocketClientBean.class);
            webSocketClientBeans.clear();
            webSocketClientBeans.add(json);
            Map<String, String> parem = new HashMap<>();
            Set<String> headers = webSocketClientBeans.get(0).Headers.keySet();
            for (String key : headers) {
                String value = webSocketClientBeans.get(0).Headers.get(key);
                if (key.equals("Accept-Encoding")) {

                } else {
                    parem.put(key, value);
                }
            }

//OKhttpUtils非链式写法
            GetBuilder getBuilder = OkHttpUtils.get();
            getBuilder.url(json.Url);
            getBuilder.headers(parem);
            getBuilder.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.i("onError", "onError: " + e);
                }

                @Override
                public String parseNetworkResponse(Response response, int id) throws IOException {
                    String responseStrng = response.headers().toString().replaceAll("\n", "\r" + "\n");
                    responseHeaders = "HTTP1.1" + " " + response.code() + "\r" + "\n" + responseStrng;
                    Log.i("parseNetworkResponse", "parseNetworkResponse: " + responseHeaders);
                    return super.parseNetworkResponse(response, id);
                }

                @Override
                public void onResponse(String response, int id) {
                    Log.i("onResponse", "onResponse: 这就是请求过来的" + response);
                    Url = json.Url;
                    Uri s = Uri.parse(json.Url);
                    String[] url = json.Url.split("https://wqdeal.jd.com");
                    RequestPacket = json.Mothed + " "
                            + s.getPath() + "\r" + "\n"
                            + "Host:" + json.Headers.get("Host") + "\r" + "\n"
                            + "Accept:" + json.Headers.get("Accept") + "\r" + "\n"
                            + "Referer:" + json.Headers.get("Referer") + "\r" + "\n"
                            + "Cookie:" + json.Headers.get("Cookie") + "\r" + "\n" + "\r" + "\n";
                    Log.i("RequestPacket", "onResponse: " + RequestPacket);
                    ResponsePacket = responseHeaders + "\r" + "\n" + response + "";
                    Log.i("RequestPacket", "onResponse: " + ResponsePacket);
                    sendData(Url, RequestPacket, ResponsePacket);
                }
            });
//        }
        }
    }

    private void sendData(String Url, String RequestPacket, String ResponsePacket) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("UserName", "王某");
        params.put("Url", "" + Url);
        params.put("RequestPacket", RequestPacket);
        params.put("ResponsePacket", ResponsePacket);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://192.168.1.137:7001/api/Open/OpenOrder/Collect")
                .addHeader("Authorization", "Bearer " + Authorization)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: " + response);
                    }
                });
    }

    private void initGetRouteData() {
        OkHttpUtils.get()
                .url("http://192.168.1.137:7001/api/Identity/GetRoute")
                .addHeader("Authorization", "Bearer " + Authorization)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e);
                        Toast.makeText(PassWordSignActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: " + response);
                        GetRouteData getRouteData = GsonUtil.getGsonLower().fromJson(response, GetRouteData.class);
                        getRouteDatas.clear();
                        getRouteDatas.add(getRouteData);
                        if (getRouteDatas.get(0).Type == 200) {
                            Route = getRouteDatas.get(0).Data.Scheme + "://" + getRouteDatas.get(0).Data.Host + getRouteDatas.get(0).Data.Path;
                            saveHost(PassWordSignActivity.this, Route);
                            application.setHost(Route);
                            connect();
//                            webSocketsConnection=new WebSocketsConnection.Builder(getBaseContext()).client(new OkHttpClient().newBuilder()
//                            .pingInterval(15,TimeUnit.SECONDS).retryOnConnectionFailure(true).build())
//                                    .needReconnect(true)
//                                    .wsUrl(Route)
//                                    .build();
//                            sendMessage(Authorization);
                        }
                    }
                });
    }


    private final class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            try {
                CatMessageOuterClass.CatMessage.Builder builder = CatMessageOuterClass.CatMessage.newBuilder();
                builder.setBody(Authorization);
                builder.setType("Login");
                CatMessageOuterClass.CatMessage info = builder.build();
                byte[] bytes = info.toByteArray();
                webSocket.send(ByteString.of(bytes));
                Log.i("onOpen", "onOpen: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (task == null) {
                task = new TimerTask() {
                    @Override
                    public void run() {
                        CatMessageOuterClass.CatMessage.Builder builder = CatMessageOuterClass.CatMessage.newBuilder();
                        builder.setType("Ping");
                        CatMessageOuterClass.CatMessage info = builder.build();
                        byte[] bytes1 = info.toByteArray();
                        try {
                            webSocket.send(ByteString.of(bytes1));
                            Log.i("发送心跳包", "run: 连接正常");

                        } catch (Exception e) {
                            connect();
                            Log.i("发送心跳包", "run: 失败,重连");
                            e.printStackTrace();
                        }
                    }
                };
            }
            timer.schedule(task, 0, 10000);
        }


        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            output("onMessage: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            byte[] bytes1 = bytes.toByteArray();
            System.out.println("这是需要存储的数据" + bytes1);
            String StringBody = "";
            CatMessageOuterClass.CatMessage ByteStringBoydy = null;
            try {
                ByteStringBoydy = CatMessageOuterClass.CatMessage.parseFrom(bytes1);
                StringBody = ByteStringBoydy.getType();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            if(StringBody.equals("Pong")){
//                if (task1 == null) {
//                    task1 = new TimerTask() {
//                        @Override
//                        public void run() {
//                            connect();
//                            Log.i("run", "run: 重新链接");
//                        }
//                    };
//                    timer.schedule(task, 0, 15000);
//                }
//
//            }
            output("onMessage byteString: " + StringBody);
            if (ByteStringBoydy.getType().equals("OrderPayment")) {
                //响应后台的支付
                StringBody = ByteStringBoydy.getBody();
                try {
                    initWebDataParen(StringBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ByteStringBoydy.getType().equals("OrderDetail")) {
                //响应订单详情
                String bodys = ByteStringBoydy.getBody();
                try {
                    initWebData(bodys);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            output("onClosing: " + code + "/" + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            output("onClosed: " + code + "/" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            output("onFailure: " + t.getMessage());
        }
    }

    private void initWebDataParen(String stringBody) {
        WebSocketClientPayBean payBean = GsonUtil.getGsonLower().fromJson(stringBody, WebSocketClientPayBean.class);
        payBeans.clear();
        payBeans.add(payBean);
        Map<String, String> FristMap = new HashMap<>();
        Set<String> headers = payBeans.get(0).Rules.get(0)._$RequestPacket15.Headers.keySet();
        for (String key : headers) {
            //Cookie有BUG
            String value = payBeans.get(0).Rules.get(0)._$RequestPacket15.Headers.get(key);
            if (key.equals("Accept-Encoding")) {

            } else if (key.equals("Cookie")) {
                value = payBeans.get(0).Headers.get(key);
                Log.i("initWebDataParen", "initWebDataParen: Cookies有值");
                FristMap.put(key, value);
            } else {
                FristMap.put(key, value);
            }
        }
        String s = payBeans.get(0).Rules.get(0)._$RequestPacket15.Url.replaceAll("dealId=【OrderNo】&", "dealId=" + payBeans.get(0)._$Order311.OrderNo + "&");
        Log.i("initWebDataParen", "initWebDataParen: " + s);
        OkHttpUtils.get()
                .url(s)
                .headers(FristMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 多步骤" + e);
                    }


                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 多步骤" + response);
                        Log.i("onResponse", "onResponse: " + payBeans.get(0).Rules.get(0).Dics.get(0).Value);
                        try {
                            String s1 = payBeans.get(0).Rules.get(0).Dics.get(0).Value;
                            if (s1.equals("newPay-index.html\\?payId=(?<PayId>.*?)&")) {
                                Log.i("onResponse", "onResponse: 匹配的没有什么毛病");
                            } else {
                                Log.i("onResponse", "onResponse: 匹配的有毛病" + s1);
                            }
                            Pattern pattern = Pattern.compile(payBeans.get(0).Rules.get(0).Dics.get(0).Value);
                            Matcher matcher = pattern.matcher(response);
                            boolean matches = matcher.find();
                            /**
                             * 模拟器运行正则会出错,模拟器问题，暂未找到合适的适配方法
                             * 还缺少字符串抓取规则未写
                             * 还缺少Xpath规则未写
                             */
                            if (matches) {
                                payBeans.get(0).Rules.get(0).Dics.get(0).Key = matcher.group(1);
                                Log.i("onResponse", "onResponse: 看看正则匹配到的记过" + payBeans.get(0).Rules.get(0).Dics.get(0).Key);
                                String PayId = payBeans.get(0).Rules.get(0).Dics.get(0).Key;
                                if (PayId != null) {
                                    TheSecondData(PayId);
                                }
                            } else{
                                Log.i("onResponse", "onResponse: 订单可能已经过了支付时间了");
                            }
                        } catch (Exception e) {
                            Log.i("错误日志", "onResponse: 错误日志" + e);
                            e.printStackTrace();
                        }

                    }
                });


    }

    private void TheSecondData(String payId) {
        Map<String, String> parems = new HashMap<>();
        Set<String> headers = payBeans.get(0).Rules.get(1)._$RequestPacket15.Headers.keySet();
        for (String key : headers) {
            String value = payBeans.get(0).Rules.get(1)._$RequestPacket15.Headers.get(key);
            if (key.equals("Cookie")) {
                //替换Cookies
//                JsonObject j = (JsonObject) payBeans.get(0).Headers;
                value = payBeans.get(0).Headers.get(key);
//                value = j.get(key).getAsString();
                Log.i("TheSecondData", "TheSecondData: Cookies有值");
                parems.put(key, value);
            } else if (key.equals("Referer")) {
                String s = payBeans.get(0).Headers.get(key);
                String Referer = s.replace("【PayId】", payId);
                value = Referer;
                parems.put(key, value);
            } else {
                parems.put(key, value);
            }
        }
        String Url = payBeans.get(0).Rules.get(1)._$RequestPacket15.Url.replaceAll("【PayId】", payId);
        Log.i("TheSecondData", "TheSecondData: 请求第二步" + Url);
        String content = payBeans.get(0).Rules.get(1)._$RequestPacket15.Content.replaceAll("【PayId】", payId);
        Log.i("TheSecondData", "TheSecondData: 请求第二步" + content);
        OkHttpUtils.postString()
                .url(Url)
                .content(content)
                .headers(parems)
                .mediaType(MediaType.parse("application/x-www-form-urlencoded"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 请求第二步" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse: ", "onResponse: 请求第二步" + response);
                        TheThridData(payId);
                    }
                });


    }

    private void TheThridData(String payId) {
        Set<String> headers = payBeans.get(0).Rules.get(2)._$RequestPacket15.Headers.keySet();
        for (String key : headers) {
            String value = payBeans.get(0).Rules.get(2)._$RequestPacket15.Headers.get(key);
            if (key.equals("Cookie")) {
                //替换Cookies
//                JsonObject j = (JsonObject) payBeans.get(0).Headers;
                value = payBeans.get(0).Headers.get("Cookie");
//                value = j.get(key).getAsString();
                Log.i("TheSecondData", "TheSecondData: Cookies有值");
                map.put(key, value);
            } else if (key.equals("Referer")) {
                String s = payBeans.get(0).Headers.get("Referer");
                String Referer = s.replace("【PayId】", payId);
                value = Referer;
                map.put(key, value);
            } else {
                map.put(key, value);
            }
        }

        String Url = payBeans.get(0).Rules.get(2)._$RequestPacket15.Url.replaceAll("【PayId】", payId);
        Log.i("TheSecondData", "TheSecondData: Url  " + Url);
        OkHttpUtils.get()
                .url(Url)
                .headers(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 多步骤第三次" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("OnResponse", "OnResponse: 多步骤第三次" + response);
                        Pattern pattern = null;
                        try {
                            pattern = Pattern.compile(payBeans.get(0).Rules.get(2).Dics.get(0).Value);
                            Matcher matcher = pattern.matcher(response);
                            while (matcher.find()) {
                                payBeans.get(0).Rules.get(2).Dics.get(0).Key = matcher.group(1);
                                String PaymentUrl = payBeans.get(0).Rules.get(2).Dics.get(0).Key;
                                String OrderNo = payBeans.get(0)._$Order311.OrderNo;
                                if (PaymentUrl != null) {
                                    Log.i("onResponse", "onResponse: 拿到了PaymentUrl");

                                    CallBackData(OrderNo, PaymentUrl);
                                } else {
                                    Log.i("onResponse", "onResponse: 请求不正确或是没匹配到");
                                }
                            }
                        } catch (Exception e) {
                            Log.i("错误日志", "onResponse: 错误日志" + e);
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void CallBackData(String orderNo, String paymentUrl) {
        Map<String, String> callback = new HashMap<>();
        callback.put("orderNo", orderNo);
        callback.put("paymentUrl", paymentUrl);
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(callback))
                .url(payBeans.get(0).CallBackUrl)
                .addHeader("Authorization", "Bearer " + Authorization)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: 最后一步" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: 最后一步" + response);
                    }
                });
    }

    private void initBodyData(String stringBody) {
//        String[] JSON = stringBody.split("Body: ");
//        System.out.println(JSON[1]);
    }


    private void connect() {
        new Thread() {
            @Override
            public void run() {
                EchoWebSocketListener listener = new EchoWebSocketListener();
                Request request = new Request.Builder()
                        .url(application.getHost())
                        .build();
                OkHttpClient client = new OkHttpClient();
                client.newWebSocket(request, listener);
                client.dispatcher().executorService().shutdown();
            }
        }.start();


    }

    private void output(final String content) {
        Log.i("output", "output: " + content);
        //如果连不上会导致死循环
//        if(!content.equals("onMessage byteString: Pong")){
//            Log.i("output", "output:尝试重连");
//            connect();
//        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBtn_YZMlogin.setText(mBtn_YZMlogin.getText().toString() + content + "\n");
            }
        });

    }

    private void sendMessage(String authorization) {

    }


    public static void saveHost(Context mContext, String host) {

        SPUtil.put(mContext, "host", host);
    }


    public static String getHost(Context mContext) {

        return SPUtil.get(mContext, "host", "").toString();

    }

    public static void saveAccount(Context mContext, String account) {

        SPUtil.put(mContext, "Account", account);
    }


    public static String getAccount(Context mContext) {

        return SPUtil.get(mContext, "Account", "").toString();

    }

    public static void savepassword(Context mContext, String password) {

        SPUtil.put(mContext, "Password", password);
    }


    public static String getpassword(Context mContext) {

        return SPUtil.get(mContext, "Password", "").toString();

    }

    private void initData() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("Account", "" + mTv_AccountNumber.getText().toString());
        params.put("Password", "" + mTv_PassWordNumber.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://192.168.1.137:7001/api/Identity/Jwtoken")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: " + response);
                        LoginingDataBean loginingDataBean = GsonUtil.getGsonLower().fromJson(response, LoginingDataBean.class);
                        loginingDataBeans.clear();
                        loginingDataBeans.add(loginingDataBean);
                        if (loginingDataBeans.get(0).Type == 200) {
                            Authorization = loginingDataBeans.get(0).Data;
                            application.setAuthorization(Authorization);
                            try {
                                initGetRouteData();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            saveAccount(PassWordSignActivity.this, mTv_AccountNumber.getText().toString());
                            savepassword(PassWordSignActivity.this, mTv_PassWordNumber.getText().toString());
                            Intent intent = new Intent(PassWordSignActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(PassWordSignActivity.this);
                            builder.setTitle("提示");
                            builder.setMessage(loginingDataBeans.get(0).Content);
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            builder.create();
                            builder.show();
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        if (webSocketsConnection != null) {
            webSocketsConnection.stopConnect();
            webSocketsConnection = null;
        }
        super.onDestroy();
    }

    public static void main(String[] args) {

        String s1 = "newPay-index.html\\?payId=(?<PayId>.*?)&";
        String s4 = "newPay-index.html?payId=(?<PayId>.*?)&";
        String s2 = "jdappmpayCb({\"errno\":0, \"msg\":\"\", \"rurl\":\"\", \"data\":{\"jumpurl\":\"https://pay.m.jd.com/cpay/newPay-index.html?payId=f276995bf7ae4ee397dc3c22c9c797d3&appId=jd_m_pay\"}})";
        String s3 = "啦啦啦啦啦啦啦";
        Pattern pattern = Pattern.compile(s1);
        Matcher matcher = pattern.matcher(s2);
        boolean matchers = matcher.find();
        if (matchers) {
            System.out.println("匹配到了正则");
        } else {
            System.out.println("我没有匹配到正则");
        }
        if (s1.equals("newPay-index.html" + "\\?" + "payId=(?<PayId>.*?)&")) {
            System.out.println("两者相同。");
            Pattern pattern2 = Pattern.compile("newPay-index.html" + "\\?" + "payId=(?<PayId>.*?)&");
            Matcher matcher2 = pattern2.matcher(s2);
            boolean matchers2 = matcher2.find();
            if (matchers2) {
                System.out.println("两者相同，不知道为什么没匹配的上");
            }
        } else {
            System.out.println("两个确实不太一样");
        }
    }
}


