package com.just.rebate.ui.activity.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.just.rebate.app.MyApplication;
import com.just.rebate.ui.activity.Socket_Utils.CatMessageOuterClass;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketService extends Service {
    private Thread connectThread;
    private Timer timer = new Timer();
    private TimerTask task;
    private MyApplication application;
    private boolean isReConnect = true;
    private SocketBinder sockerBinder = new SocketBinder();
    private Handler handler = new Handler(Looper.getMainLooper());
    private WebSocketService webSocketService;

    @Override
    public void onCreate() {
        application = (MyApplication) getApplication();
        webSocketService = this;
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sockerBinder;
    }

    public class SocketBinder extends Binder {

        /*返回SocketService 在需要的地方可以通过ServiceConnection获取到SocketService  */
        public WebSocketService getService() {
            return webSocketService;
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {


        /*初始化socket*/
        initSocket();

        return super.onStartCommand(intent, flags, startId);
    }

    private void initSocket() {
        EchoWebSocketListener listener = new EchoWebSocketListener();
        Request request = new Request.Builder()
                .url(application.getHost())
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }

    private final class EchoWebSocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            try {
                CatMessageOuterClass.CatMessage.Builder builder = CatMessageOuterClass.CatMessage.newBuilder();
                builder.setBody(application.getAuthorization());
                builder.setType("Login");
                CatMessageOuterClass.CatMessage info = builder.build();
                byte[] bytes = info.toByteArray();
                webSocket.send(ByteString.of(bytes));
                Log.i("onOpen", "onOpen: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    webSocketService = new WebSocketService();
                    if (timer == null) {

                    }
                    if (task == null) {
                        task = new TimerTask() {
                            @Override
                            public void run() {
                                CatMessageOuterClass.CatMessage.Builder builder = CatMessageOuterClass.CatMessage.newBuilder();
                                builder.setType("Ping");
                                CatMessageOuterClass.CatMessage info = builder.build();
                                byte[] bytes = info.toByteArray();
                                try {
                                    webSocket.send(ByteString.of(bytes));
                                    Log.i("发送心跳包", "run: 成功");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    initSocket();
                                    Log.i("发送心跳包", "run: 失败,重连");
                                }
                            }
                        };
                    }
                    timer.schedule(task, 0, 8000);
                }
            }).start();
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            output("onMessage: " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            output("onMessage byteString: " + bytes);
            byte[] bytes1 = bytes.toByteArray();
            String StringBody = "";
            CatMessageOuterClass.CatMessage ByteStringBody = null;
            try {
                ByteStringBody = CatMessageOuterClass.CatMessage.parseFrom(bytes1);
                StringBody = ByteStringBody.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("onMessage", "onMessage: " + StringBody);
            initBodyData(StringBody);
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

    private void output(String s) {
    }

    private void initBodyData(String stringBody) {

    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
