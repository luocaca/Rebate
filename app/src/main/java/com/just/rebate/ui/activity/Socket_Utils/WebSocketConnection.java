//package com.just.rebate.ui.activity.Socket_Utils;
//
//import android.net.Uri;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.just.rebate.ui.activity.web.web_util.LogUtil;
//import com.smartarmenia.dotnetcoresignalrclientjava.HubConnection;
//import com.smartarmenia.dotnetcoresignalrclientjava.HubConnectionListener;
//import com.smartarmenia.dotnetcoresignalrclientjava.HubEventListener;
//import com.smartarmenia.dotnetcoresignalrclientjava.HubMessage;
//import com.smartarmenia.dotnetcoresignalrclientjava.SignalRMessage;
//
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft_6455;
//import org.java_websocket.handshake.ServerHandshake;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.net.ssl.SSLSocketFactory;
//
//
//public class WebSocketConnection implements HubConnection {
//
//    private OnPongListener pongListener;
//
//    public static final int ON_SERVER_INVOKE_CLIENT = 1;
//    public static final int ON_CLIENT_INVOKE_SERVER = 3;
//    public static final int ON_PONG = 6;
//
//    private static String SPECIAL_SYMBOL = "\u001E";
//    private static String TAG = "WebSockets";
//
//    private WebSocketClient client;
//    private List<HubConnectionListener> listeners = new ArrayList<>();
//    private Map<String, List<HubEventListener>> eventListeners = new HashMap<>();
//    private Uri parsedUri;
//    private Gson gson = new Gson();
//
//    private String connectionId = null;
//    private String authHeader;
//
//    public String getConnectionIdStr() {
//        return connectionId;
//    }
//
//
//    private InterruptThread thread;
//
//    private class InterruptThread extends Thread {
//
//
//        private Runnable runnable;
//
//        private InterruptThread(Runnable r) {
//
//            this.runnable = r;
//        }
//
//        @Override
//        public void run() {
//            super.run();
//            runnable.run();
//        }
//    }
//
//    public WebSocketConnection(String hubUrl, String authHeader, OnPongListener listener) {
//        this.authHeader = authHeader;
//        parsedUri = Uri.parse(hubUrl);
//        this.pongListener = listener;
//    }
//
//    @Override
//    public synchronized void connect() {
//////        if (client != null && (client.isOpen() || client.isConnecting()))
////            return;
//
//
//        Runnable runnable;
//        if (connectionId == null) {
//            runnable = new Runnable() {
//                public void run() {
//                    getConnectionId();
//                }
//            };
//        } else {
//            runnable = new Runnable() {
//                public void run() {
//                    connectClient();
//                }
//            };
//        }
//        thread = new InterruptThread(runnable);
//
//        thread.start();
////        new Thread(runnable).start();
//    }
//
//    private void getConnectionId() {
//        Log.i(TAG, "Requesting connection id...");
//        if (!(parsedUri.getScheme().equals("http") || parsedUri.getScheme().equals("https")))
//            throw new RuntimeException("URL must start with http or https");
//
//        try {
//            String negotiateUri = parsedUri.buildUpon().appendPath("negotiate").build().toString();
//            HttpURLConnection connection = (HttpURLConnection) new URL(negotiateUri).openConnection();
//            if (authHeader != null && !authHeader.isEmpty()) {
//                connection.addRequestProperty("Authorization", authHeader);
//            }
//
//            connection.setConnectTimeout(15000);
//            connection.setReadTimeout(15000);
//            connection.setRequestMethod("POST");
//            int responseCode = connection.getResponseCode();
//
//            if (responseCode == 200) {
//                String result = WebSocketConnection.InputStreamConverter.convert(connection.getInputStream());
//                JsonElement jsonElement = gson.fromJson(result, JsonElement.class);
//                String connectionId = jsonElement.getAsJsonObject().get("connectionId").getAsString();
//                JsonElement availableTransportsElements = jsonElement.getAsJsonObject().get("availableTransports");
//                List<JsonElement> availableTransports = Arrays.asList(gson.fromJson(availableTransportsElements, JsonElement[].class));
//                boolean webSocketAvailable = false;
//                for (JsonElement element : availableTransports) {
//                    if (element.getAsJsonObject().get("transport").getAsString().equals("WebSockets")) {
//                        webSocketAvailable = true;
//                        break;
//                    }
//                }
//                if (!webSocketAvailable) {
//                    throw new RuntimeException("The server does not support WebSockets transport");
//                }
//                this.connectionId = connectionId;
//                connectClient();
//            } else if (responseCode == 401) {
//                throw new RuntimeException("Unauthorized request");
//            } else {
//                throw new RuntimeException("Server error " + "  error message " + connection.getResponseMessage() + "   code: " + responseCode);
//            }
//        } catch (Exception e) {
//            error(e);
//        }
//    }
//
//    private void connectClient() {
//        Uri.Builder uriBuilder = parsedUri.buildUpon();
//        uriBuilder.appendQueryParameter("id", connectionId);
//
//        Log.e(TAG, "connectionId\n" + connectionId);
//
//        uriBuilder.scheme(parsedUri.getScheme().replace("http", "ws"));
//        Uri uri = uriBuilder.build();
//        Map<String, String> headers = new HashMap<>();
//        if (authHeader != null && !authHeader.isEmpty()) {
//            headers.put("Authorization", authHeader);
//        }
//        try {
//            client = new WebSocketClient(new URI("ws://192.168.1.102:8850/ws"), new Draft_6455(), headers, 15000) {
//                @Override
//                public void onOpen(ServerHandshake handshakeData) {
//                    Log.i(TAG, "Opened");
//                    for (HubConnectionListener listener : listeners) {
//                        listener.onConnected();
//                    }
//                    send("{\"protocol\":\"json\",\"version\":1}" + SPECIAL_SYMBOL);
//                }
//
//                @Override
//                public void onMessage(String message) {
//
////                    message = getRealMessage(message);
//                    Log.i("onEventMessage-服务器源信息-", message);
//                    String[] messages = message.split(SPECIAL_SYMBOL);
//                    for (String m : messages) {
//
////                        Type t = new TypeToken<SignalRMessageT<Account>>() {
////                        }.getType();
//
////                        Type t = new TypeToken<SignalRMessageT<BaseResponseT<AccountData>>>() {
////                        }.getType();
////
////
////                        SignalRMessageT<BaseResponseT<AccountData>> baseResponseTSignalRMessageT = new Gson().fromJson(message, t);
////
////                        baseResponseTSignalRMessageT.getResult().getData().getToken();
//
//
////                        SignalRMessageT<Account> element = gson.fromJson(m, t);
//                        SignalRMessage element = gson.fromJson(m, SignalRMessage.class);
//
//                        Log.i("onEventMessage-服务器源信息-", m);
//                        Log.i("onEventMessage-服务器源信息-", element.toString());
//
//
//                        Integer type = element.getType();
//                        Log.i("type", "onEventMessage-type-" + type);
//
//
//                        // type == 1 表示  服务器给 客户端发的消息  onMessage
//                        // type == 3 表示  客户端invoke 服务器方法，的回调  onEvent
//                        if (type != null && type == ON_CLIENT_INVOKE_SERVER) {//&& type == 1
//                            HubMessage hubMessage = new HubMessage(element.getInvocationId(), element.getTarget(), element.getArguments());
//                            message = message.replaceFirst("type", "Type");
//                            message = message.replaceFirst("invocationId", "InvocationId");
//                            message = message.replaceFirst("result", "Result");
//                            hubMessage.setTarget(message.trim());
//                            for (HubConnectionListener listener : listeners) {
//                                listener.onMessage(hubMessage);
//                            }
//                        }
//                        // type == 3 表示  客户端invoke 服务器方法，的回调  onEvent
//                        else if (type != null && type == ON_SERVER_INVOKE_CLIENT) {
//                            HubMessage hubMessage = new HubMessage(element.getInvocationId(), element.getTarget(), element.getArguments());
//                            message = message.replaceFirst("type", "Type");
//                            message = message.replaceFirst("target", "InvocationId");
//                            message = message.replaceFirst("arguments", "Result");
////                            hubMessage.setTarget(message.trim());
//
//                            List<HubEventListener> hubEventListeners = eventListeners.get(hubMessage.getTarget());
//                            if (hubEventListeners != null) {
//                                for (HubEventListener listener : hubEventListeners) {
//                                    listener.onEventMessage(hubMessage);
//                                }
//                            }
//                        } else if (type != null && type == ON_PONG) {
//                            //
//                            if (pongListener != null) {
//                                pongListener.onPong(System.currentTimeMillis(), m);
//                            }
//
//                        }
//
//
//                    }
//                }
//
//                @Override
//                public void onClose(int code, String reason, boolean remote) {
//                    Log.i(TAG, String.format("Closed. Code: %s, Reason: %s, Remote: %s", code, reason, remote));
//                    for (HubConnectionListener listener : listeners) {
//                        listener.onDisconnected();
//                    }
//                    connectionId = null;
//                }
//
//                @Override
//                public void onError(Exception ex) {
//                    Log.i(TAG, "Error " + ex.getMessage());
//                    error(ex);
//
//
//                }
//            };
//
//            if (parsedUri.getScheme().equals("https")) {
//                client.setSocket(SSLSocketFactory.getDefault().createSocket());
//            }
//        } catch (Exception e) {
//            error(e);
//        }
//        Log.i(TAG, "Connecting...");
//        client.connect();
//    }
//
//    /**
//     * 获取真实的 json数据
//     *
//     * @param
//     * @return
//     */
////    private String getRealMessage(String message) {
////
////        String realMessage;
////
////        try {
//////            SimpleJson simpleJson = new Gson().fromJson(message, SimpleJson.class);
//////            if (simpleJson != null && !TextUtils.isEmpty(simpleJson.getResult())) {
//////                realMessage = simpleJson.getResult();
//////            } else {
//////                Log.e(TAG, "getRealMessage: 解析真实json失败 原样返回");
//////                realMessage = message;
//////            }
////
////        } catch (JsonSyntaxException e) {
////            e.printStackTrace();
////            Log.w(TAG, "getRealMessage: 强转失败 --- 原因返回json");
////            realMessage = message;
////        }
////
////        return realMessage;
////    }
//
//    private void error(Exception ex) {
//        for (HubConnectionListener listener : listeners) {
//            listener.onError(ex);
//        }
//    }
//
//    @Override
//    public void disconnect() {
//        Runnable runnable = new Runnable() {
//            public void run() {
//                if (client != null && !(client.isClosed() || client.isClosing()))
//                    client.close();
//            }
//        };
//        new Thread(runnable).start();
//    }
//
//    @Override
//    public synchronized boolean isConnected() {
//        return client!=null && client.isOpen();
//    }
//
//    @Override
//    public void addListener(HubConnectionListener listener) {
//        if (!listeners.contains(listener)) {
//            listeners.add(listener);
//        }
//    }
//
//    @Override
//    public void removeListener(HubConnectionListener listener) {
//        listeners.remove(listener);
//    }
//
//    @Override
//    public void subscribeToEvent(String eventName, HubEventListener eventListener) {
//        Log.i("subscribeToEvent", "subscribeToEvent: ---多少次看看" + eventName);
//        List<HubEventListener> eventMap;
//
//
//        if (eventListeners.containsKey(eventName)) {
//            eventMap = eventListeners.get(eventName);
//        } else {
//            eventMap = new ArrayList<>();
//            eventListeners.put(eventName, eventMap);
//        }
//
//
//        eventMap.add(eventListener);
//    }
//
//    @Override
//    public void unSubscribeFromEvent(String eventName, HubEventListener eventListener) {
//        if (eventListeners.containsKey(eventName)) {
//            List<HubEventListener> eventMap = eventListeners.get(eventName);
//            eventMap.remove(eventListener);
//            if (eventMap.isEmpty()) {
//                eventListeners.remove(eventName);
//            }
//        }
//    }
//
//    @Override
//    public void invoke(String event, Object... parameters) {
//
//
//        LogUtil.i("接口调用地址与参数" + "event :" + event + "----->" + parameters);
//
//        if (client == null || !client.isOpen()) {
//
//            for (HubConnectionListener listener : listeners) {
//                listener.onError(new IllegalAccessException("客户端未连接-或者已断开"));
//            }
//            return;
////            throw new RuntimeException("Not connected");
//        }
//        final Map<String, Object> map = new HashMap<>();
//        map.put("type", 1);
//        map.put("invocationId", UUID.randomUUID());
//        map.put("target", event);
//        map.put("arguments", parameters);
//        map.put("nonblocking", false);
//        Runnable runnable = new Runnable() {
//            public void run() {
//                try {
//
//                    String s = gson.toJson(map);
//
//
//                    Log.i(TAG, "json--\n: " + s);
//                    client.send(s + SPECIAL_SYMBOL);
//                } catch (Exception e) {
//                    error(e);
//                }
//            }
//        };
//        new Thread(runnable).start();
//    }
//
//    private static class InputStreamConverter {
//        private static char RETURN_SYMBOL = '\n';
//
//        static String convert(InputStream stream) throws IOException {
//            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
//            StringBuilder total = new StringBuilder();
//            String line;
//            while ((line = r.readLine()) != null) {
//                total.append(line);
//                total.append(RETURN_SYMBOL);
//            }
//
//            return total.toString();
//        }
//    }
//
//
//    /**
//     * 断开连接
//     * 与
//     * 线程
//     */
//    public void releaseClientAndConnect() {
//        if (client != null) {
//            client.close();
//            client = null;
//        }
//        if (thread != null) {
//            thread.interrupt();
//            thread = null;
//        }
//    }
//
//
//}
//
