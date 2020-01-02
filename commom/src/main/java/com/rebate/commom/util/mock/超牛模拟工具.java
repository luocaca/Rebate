package com.rebate.commom.util.mock;

import android.content.Context;
import android.content.Intent;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;


public class 超牛模拟工具 {


    private static final String TAG = "超牛模拟工具";

    /**
     * 模拟
     *
     * @param mapOverwrite   需要覆盖的数据  动态数据
     * @param catchText      charls 抓取的文本内容
     * @param stringCallback 回调接口
     */
    public static void 执行模拟(Map mapOverwrite, String catchText, StringCallback stringCallback) {


        setMyClinet(OkHttpUtils.getInstance());

        Map<String, String> map = getCookiesString(catchText);
        map.putAll(mapOverwrite);
        setScheme(map).url(getUrlByMap(map)).headers(saveRemove(map, "body")).build().execute(stringCallback);

    }


    /**
     * OkHttpUtils
     * {
     * public static final long DEFAULT_MILLISECONDS = 10_000L;
     * private volatile static OkHttpUtils mInstance;
     * private OkHttpClient mOkHttpClient;
     *
     * @param instance
     */
    public static void setMyClinet(OkHttpUtils instance) {
        SSLSocketFactory sslSocketFactory = null;

        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new X509TrustManager[]{trustManager}, null);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }


        try {
            Field field = instance.getClass().getDeclaredField("mOkHttpClient");


            field.setAccessible(true);

            OkHttpClient client = OkHttpUtils.getInstance().getOkHttpClient().newBuilder()
//                    .cookieJar(new CookieJar() {
//                        @Override
//                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//
//                            System.out.println(cookies.size());
////                            t=7f58daa6663bb34f353edd650f8eff63; expires=Thu, 19 Dec 2019 08:35:31 GMT; domain=taobao.com; path=/
//
//
//                        }
//
//                        @Override
//                        public List<Cookie> loadForRequest(HttpUrl url) {
//
//                            //cookie: _l_g_=Ug%3D%3D
////                            List<Cookie> cookies = new ArrayList<>();
//                            Cookie cookie = new Cookie.Builder()
//                                    .domain("taobao.com")
//                                    .expiresAt(1576744531994l)
//                                    .httpOnly()
//                                    .name("cookie: _l_g_=")
//                                    .value("ljlfjadddd")
//                                    .build();
//                            Cookie cookie1 = new Cookie.Builder()
//                                    .domain("taobao.com")
//                                    .expiresAt(1576744531994l)
//                                    .httpOnly()
//                                    .name("luocaca")
//                                    .value("ljlfjadasfdasdfsadfas")
//                                    .build();
////                                    (."t","ljlfja","1576744531994","taobao.com","/",false,false,true,false);
//
//                            System.out.println("loadForRequest: " + url);
//                            List<Cookie> cookies = new ArrayList<>();
//
//
//                            cookies.add(cookie);
//                            cookies.add(cookie1);
//                            return cookies;
//                        }
//                    })
//                    .proxy(Proxy.NO_PROXY)
                    .sslSocketFactory(sslSocketFactory, trustManager)
//                    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.171", 8888)))
                    .build();
//            client.sslSocketFactory(sslSocketFactory, trustManager);
//            OkHttpClient OkHttpClient = (okhttp3.OkHttpClient) field.get(instance);
//设置代理,需要替换
            field.set(instance, client);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //mOkHttpClient


    }


//    public static void requestData(String orderId, String ppu, StringCallback stringCallback) {
//
//        Map<String, String> map = getCookiesString(orderId, " " + ppu);
//
//        setScheme(map).url(getUrlByMap(map)).headers(saveRemove(map, "body")).build().execute(stringCallback);
//
//    }

    /**
     * 抓取的问问内容
     *
     * @param catchText
     * @return
     */
    public static Map<String, String> getCookiesString(String catchText) {


        Map<String, String> map = new HashMap<>();


        try {


            File file = new File("C:\\Users\\Administrator.DESKTOP-259D8ER\\Desktop\\cookies.txt");


            System.out.println(file.exists());


//            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] b = catchText.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(catchText.getBytes());

            InputStreamReader inputreader
                    = new InputStreamReader(byteArrayInputStream);
//            InputStreamReader inputreader
//                    = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputreader);


            byte[] bytes = new byte[2048];
            int lenth = -1;
            String lineString = "";

            StringBuffer stringBuffer = new StringBuffer();

            String cookie = "";

            boolean isBody = false;
            while ((lineString = bufferedReader.readLine()) != null) {
//            lenth = fileInputStream.read(bytes);


                if (lineString.startsWith("cookie")) {
                    cookie = cookie + lineString.replaceFirst("cookie: ", "") + ";";
                    continue;
                }
                if (lineString.startsWith("Cookie")) {
                    cookie = cookie + lineString.replaceFirst("Cookie: ", "") + ";";
                    continue;
                }

                if (lineString.startsWith("accept-encoding") || lineString.startsWith("Accept-Encoding")) {
                    System.out.println("accept-encoding--不要");
                    continue;
                }
                if (lineString.equals("")) {
                    System.out.println("next line is body");
                    isBody = true;
                    continue;

                }


                if (isBody) {
                    map.put("body", lineString.trim());
                    continue;
                }
                if (!lineString.contains(":")) {
                    lineString = lineString.replaceAll(" ", ":");
                    map.put(":method", lineString.split(":")[0].trim());
                    map.put(":path", lineString.split(":")[1].trim());

                    continue;
                }


                try {
                    stringBuffer.append(lineString);

                    if (lineString.startsWith(":")) {
                        lineString = lineString.replaceFirst(":", "");

                        if (lineString.split(lineString).length > 2) {
                            String value = "";

                            for (int i = 2; i < lineString.split(lineString).length; i++) {
                                value = value + lineString.split(lineString)[i];
                            }
                            map.put(":" + lineString.split(":")[0], lineString.split(":")[1].trim());
                        }
                        map.put(":" + lineString.split(":")[0], lineString.split(":")[1].trim());

                    } else {


                        if (lineString.split(":").length > 2) {


                            String value = "";

                            for (int i = 1; i < lineString.split(":").length; i++) {

                                if (value.trim().equals("https")) {
                                    value += ":";
                                } else if (value.trim().equals("http")) {
                                    value += ":";
                                }

                                value = value + lineString.split(":")[i];
                            }
                            map.put(lineString.split(":")[0], value.trim());

//                            map.put(lineString.split(":")[0], lineString.split(":")[1]);

                        } else {
                            map.put(lineString.split(":")[0], lineString.split(":")[1].trim());
                        }


                    }


                    System.out.println("getCookiesString: " + lineString);
                    System.out.println("getCookiesString: " + map);


                    stringBuffer.append(";");
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }

            }

            System.out.println(stringBuffer.toString());
            map.put("cookie", cookie.trim());

            if (map.get("Host") != null) {
                map.put(":authority", map.get("Host").trim());
            }

            map.put(":scheme", getSchecmeHttpOrHttps(map));


            String result = stringBuffer.toString();

//            result = result.replaceAll("cookie: ", "");


            System.out.println(result);

            return map;
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return map;

    }


    private static String getSchecmeHttpOrHttps(Map<String, String> map) {


        if (map.get("Origin") != null && map.get("Origin").contains("https")) {
            return "https";
        }
        if (map.get("Referer") != null && map.get("Referer").contains("https")) {
            return "https";
        }
        if (map.get("Origin") != null && map.get("Origin").contains("http://")) {
            return "http";
        }
        if (map.get("Referer") != null && map.get("Referer").contains("http://")) {
            return "http";
        }
        return "https";

    }

    public static Map<String, String> saveRemove(Map<String, String> map, String... tas) {
        for (String ta : tas) {
            map.remove(ta);
        }
        return map;
    }

    private static String getUrlByMap(Map<String, String> cookiesString) {
        StringBuffer url = new StringBuffer();
        url.append(cookiesString.get(":scheme"));
        url.append("://");
        url.append(cookiesString.get(":authority").trim());
        url.append(cookiesString.get(":path").trim());
        return url.toString();
    }

    public static OkHttpRequestBuilder setScheme(Map<String, String> params) {

        if (params.get(":method").trim().equals("POST")) {
            if (params.get("body") != null) {
                if (params.get("Content-Type") != null) {

                    //URLEncoder.encode(params.get("body"),"UTF-8")
                    return OkHttpUtils.postString().mediaType(MediaType.parse(params.get("Content-Type").trim())).content(params.get("body"));

                } else if (params.get("content-type") != null) {
                    return OkHttpUtils.postString().mediaType(MediaType.parse(params.get("content-type").trim())).content(params.get("body"));

                } else {
                    return OkHttpUtils.postString().content(params.get("body"));
                }
            } else {
                return OkHttpUtils.post();
            }
        } else {
            return OkHttpUtils.get();
        }


    }


}
