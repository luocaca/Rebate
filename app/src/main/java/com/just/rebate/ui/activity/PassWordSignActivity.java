package com.just.rebate.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.just.rebate.R;
import com.just.rebate.app.MyApplication;
import com.just.rebate.entity.GetRouteData;
import com.just.rebate.entity.LoginingDataBean;
import com.just.rebate.ui.MainActivity;
import com.just.rebate.ui.activity.Socket_Utils.CatMessageOuterClass;
import com.just.rebate.ui.activity.Socket_Utils.WebSocketsConnection;
import com.just.rebate.ui.activity.web.web_util.SPUtil;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class PassWordSignActivity extends AppCompatActivity {
    private EditText mTv_AccountNumber,mTv_PassWordNumber;
    private Button mBtn_YZMlogin,mBtn_sign;
    private String Authorization="";
    private String Route="";
    private MyApplication application;
    private WebSocketsConnection webSocketsConnection;
    private List<LoginingDataBean> loginingDataBeans=new ArrayList<>();
    private List<GetRouteData> getRouteDatas=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mTv_AccountNumber=findViewById(R.id.Account_Number);
        mTv_PassWordNumber=findViewById(R.id.PassWord_Number);
        mBtn_YZMlogin=findViewById(R.id.btn_YZM_login);
        mBtn_sign=findViewById(R.id.btn_sign);
        application= (MyApplication) getApplication();
        mBtn_YZMlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PassWordSignActivity.this,SignActivity.class);
                startActivity(intent);
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



    private void initGetRouteData() {
        OkHttpUtils.get()
                .url("http://192.168.1.137:7001/api/Identity/GetRoute")
                .addHeader("Authorization", "Bearer " + Authorization)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: "+e);
                        Toast.makeText(PassWordSignActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: "+response);
                        GetRouteData getRouteData=GsonUtil.getGsonLower().fromJson(response,GetRouteData.class);
                        getRouteDatas.clear();
                        getRouteDatas.add(getRouteData);
                        if(getRouteDatas.get(0).Type==200){
                            Route=getRouteDatas.get(0).Data.Scheme+"://"+getRouteDatas.get(0).Data.Host+getRouteDatas.get(0).Data.Path;
                            saveHost(PassWordSignActivity.this,Route);
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
            try {
                CatMessageOuterClass.CatMessage.Builder builder =CatMessageOuterClass.CatMessage.newBuilder();
                builder.setBody(Authorization);
                builder.setType("Login");
                CatMessageOuterClass.CatMessage info=builder.build();
                byte[] bytes=info.toByteArray();
                webSocket.send(ByteString.of(bytes));
                Log.i("onOpen", "onOpen: "+response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("onMessage: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("onMessage byteString: " + bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            output("onClosing: " + code + "/" + reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            output("onClosed: " + code + "/" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t,Response response) {
            output("onFailure: " + t.getMessage());
        }
    }
    private void connect() {
        EchoWebSocketListener listener = new EchoWebSocketListener();
        Request request = new Request.Builder()
                .url(application.getHost())
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    private void output(final String content) {
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
        params.put("Account", ""+mTv_AccountNumber.getText().toString());
        params.put("Password", "" + mTv_PassWordNumber.getText().toString());
        OkHttpUtils.postString()
                .content(GsonUtil.getGson().toJson(params))
                .url("http://192.168.1.137:7001/api/Identity/Jwtoken")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i("onError", "onError: "+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("onResponse", "onResponse: "+response);
                        LoginingDataBean loginingDataBean=GsonUtil.getGsonLower().fromJson(response,LoginingDataBean.class);
                        loginingDataBeans.clear();
                        loginingDataBeans.add(loginingDataBean);
                        if(loginingDataBeans.get(0).Data!=null){
                            Authorization=loginingDataBeans.get(0).Data;
                            try {
                                initGetRouteData();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            saveAccount(PassWordSignActivity.this,mTv_AccountNumber.getText().toString());
                            savepassword(PassWordSignActivity.this,mTv_PassWordNumber.getText().toString());
                            Intent intent=new Intent(PassWordSignActivity.this, MainActivity.class);
                            startActivity(intent);

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
}
