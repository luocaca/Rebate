package com.just.rebate.ui.activity.Socket_Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketsConnection implements IWsManager {

    private final static int RECONNECT_INTERVAL = 10 * 1000;    //重连自增步长
    private final static long RECONNECT_MAX_TIME = 120 * 1000;   //最大重连间隔
    private Context mContext;
    private String wsUrl;
    private WebSocket mWebSocket;
    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private int mCurrentStatus = WebSocketStatus.DISCONNECTED;     //websocket连接状态
    private boolean isNeedReconnect;          //是否需要断线自动重连
    private boolean isManualClose = false;         //是否为手动关闭websocket连接
    private Lock mLock;
    private Handler wsMainHandler = new Handler(Looper.getMainLooper());
    private int reconnectCount = 0;   //重连次数
    private Runnable reconnectRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("websocket", "服务器重连接中...");
            buildConnect();
        }
    };









    private WebSocketListener webSocketListener=new WebSocketListener() {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            mWebSocket = webSocket;
            setCurrentStatus(WebSocketStatus.CONNECTED);
            connected();
            Log.i("WebSocketListener", "onOpen: 服务器连接成功");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            Log.i("WebSocketListener", "onMessage: String Text");
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            Log.i("WebSocketListener", "onMessage: ByteString bytes");
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            Log.i("WebSocketListener", "onClosing: 正在关闭中");

        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            Log.i("WebSocketListener", "onClosed: 已经关闭了");

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, final Response response) {
            Log.i("WebSocketListener", "onFailure: 链接错误");

        }
    };





    public WebSocketsConnection(Builder builder){
        mContext = builder.mContext;
        wsUrl = builder.wsUrl;
        isNeedReconnect = builder.needReconnect;
        mOkHttpClient = builder.mOkHttpClient;
        this.mLock = new ReentrantLock();

    }

    private void initWebSocket() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .build();
        }
        if (mRequest == null) {
            mRequest = new Request.Builder()
                    .url(wsUrl)
                    .build();
        }
        mOkHttpClient.dispatcher().cancelAll();
        try {
            mLock.lockInterruptibly();
            try {
                mOkHttpClient.newWebSocket(mRequest, webSocketListener);
            } finally {
                mLock.unlock();
            }
        } catch (InterruptedException e) {
        }
    }



    private void connected() {
        cancelReconnect();
    }


    private void buildConnect() {
        if (!isNetworkConnected(mContext)) {
            setCurrentStatus(WebSocketStatus.DISCONNECTED);
//            return;
        }
        switch (getCurrentStatus()) {
            case WebSocketStatus.CONNECTED:
            case WebSocketStatus.CONNECTING:
                break;
            default:
                setCurrentStatus(WebSocketStatus.CONNECTING);
                initWebSocket();
        }

    }

    @Override
    public WebSocket getWebSocket() {
        return mWebSocket;
    }

    @Override
    public void startConnect() {
        isManualClose = false;
        buildConnect();
    }

    @Override
    public void stopConnect() {
        isManualClose = true;
        disconnect();
    }

    private void disconnect() {
        if (mCurrentStatus == WebSocketStatus.DISCONNECTED) {
            return;
        }
        cancelReconnect();
        if (mOkHttpClient != null) {
            mOkHttpClient.dispatcher().cancelAll();
        }
        if (mWebSocket != null) {
            boolean isClosed = mWebSocket.close(WebSocketStatus.CODE.NORMAL_CLOSE, WebSocketStatus.TIP.NORMAL_CLOSE);
            //非正常关闭连接
            if (!isClosed) {
                Log.e("websocket", "服务器连接失败");
            }
        }
        setCurrentStatus(WebSocketStatus.DISCONNECTED);

    }

    private void tryReconnect() {
        if (!isNeedReconnect | isManualClose) {
            return;
        }
        Log.e("liusehngjei", "reconnectCount2222222[" + reconnectCount + "]");
        if (!isNetworkConnected(mContext)) {
            setCurrentStatus(WebSocketStatus.DISCONNECTED);
            Log.e("liusehngjei", "[请您检查网络，未连接]");
//            return;
        }
        setCurrentStatus(WebSocketStatus.RECONNECT);
        Log.e("liusehngjei", "reconnectCount11111111[" + reconnectCount + "]");
        long delay = reconnectCount * RECONNECT_INTERVAL;
//        wsMainHandler.postDelayed(reconnectRunnable, delay > RECONNECT_MAX_TIME ? RECONNECT_MAX_TIME : delay);
        wsMainHandler.postDelayed(reconnectRunnable, 10000);
        Log.e("liusehngjei", "reconnectCount[" + reconnectCount + "]");
        reconnectCount++;

    }

    private boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    private void cancelReconnect() {
        wsMainHandler.removeCallbacks(reconnectRunnable);
        reconnectCount = 0;
    }

    @Override
    public boolean isWsConnected() {
        return mCurrentStatus == WebSocketStatus.CONNECTED;
    }

    @Override
    public int getCurrentStatus() {
        return mCurrentStatus;
    }

    @Override
    public void setCurrentStatus(int currentStatus) {
        this.mCurrentStatus = currentStatus;
    }

    @Override
    public boolean sendMessage(String msg) {
        return send(msg);
    }

    @Override
    public boolean sendMessage(ByteString byteString) {
        return send(byteString);
    }

    private boolean send(Object  msg) {
        boolean isSend = false;
        if (mWebSocket != null && mCurrentStatus == WebSocketStatus.CONNECTED) {
            if (msg instanceof String) {
                isSend = mWebSocket.send((String) msg);
            } else if (msg instanceof ByteString) {
                isSend = mWebSocket.send((ByteString) msg);
            }
            //发送消息失败，尝试重连
            if (!isSend) {
                tryReconnect();
            }
        }
        return isSend;

    }

    public static final class Builder {

        private Context mContext;
        private String wsUrl;
        private boolean needReconnect = true;
        private OkHttpClient mOkHttpClient;

        public Builder(Context val) {
            mContext = val;
        }

        public Builder wsUrl(String val) {
            wsUrl = val;
            return this;
        }

        public Builder client(OkHttpClient val) {
            mOkHttpClient = val;
            return this;
        }

        public Builder needReconnect(boolean val) {
            needReconnect = val;
            return this;
        }

        public WebSocketsConnection build() {
            return new WebSocketsConnection(this);
        }

    }

}
