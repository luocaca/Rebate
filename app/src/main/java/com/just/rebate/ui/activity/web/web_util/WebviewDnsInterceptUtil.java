package com.just.rebate.ui.activity.web.web_util;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Objects;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WebviewDnsInterceptUtil {

    @SuppressLint("NewApi")
    public static WebResourceResponse getDnsInterceptRequest(WebView view, WebResourceRequest request) {


        //http://192.168.1.6:8085/
//        if (request != null && request.getUrl() != null && request.getMethod().equalsIgnoreCase("get")&& request.getUrl().toString().contains("http://www.baidu.com")) {
//        if (request != null && request.getUrl() != null && request.getMethod().equalsIgnoreCase("get")&& request.getUrl().toString().contains("http://192.168.1.6:8085")) {
//            return getWebResourceFromUrl(request.getUrl().toString());
//        }
//        String s = request.getUrl().toString();
//        if (!TextUtils.isEmpty(s) ) {
//            return new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(FileUtil.readFileString("test.html").getBytes()));
//        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static WebResourceResponse getDnsInterceptUrl(WebView view, String url) {
        if (!TextUtils.isEmpty(url) && Uri.parse(url).getScheme() != null) {
            return getWebResourceFromUrl(url);
        }
        return null;
    }

    //核心拦截方法
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static WebResourceResponse getWebResourceFromUrl(String url) {

        String scheme = Objects.requireNonNull(Uri.parse(url).getScheme()).trim();
        String ips = ProxyList.getList().get(Uri.parse(url).getHost());//获取替换的URL
        if (TextUtils.isEmpty(ips)) {
            Log.d("ips为空：", url);
            return null;
        }
        if ((scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))) {
            HttpsURLConnection connection = null;
            try {
                URL oldUrl = new URL(url);
                // 获取HttpDns域名解析结果 // 通过HTTPDNS获取IP成功，进行URL替换和HOST头设置
                Log.d("HttpDns ips are: ", ips + " for host: " + oldUrl.getHost());
                //                Log.d("HttpDns ips are: ", "115.239.210.27" + " for host: " + oldUrl.getHost());
                String ip;
                if (ips.contains(";")) {
                    ip = ips.substring(0, ips.indexOf(";"));
                } else {
                    ip = ips;
                }
                String newUrl = url.replaceFirst(oldUrl.getHost(), ip);
                //                String newUrl = url.replaceFirst(oldUrl.getHost(), "116.62.172.33:8081");
                Log.d("newUrl a is: ", "______________________________" + newUrl);
                connection = (HttpsURLConnection) new URL(newUrl).openConnection(); // 设置HTTP请求头Host域
                connection.setHostnameVerifier(getNullHostNameVerifier());
                connection.setSSLSocketFactory(getIgnoreSSLContext().getSocketFactory());
                connection.setRequestProperty("Host", oldUrl.getHost());
                Log.d("ContentType a: ", connection.getContentType());

                String encode = connection.getContentEncoding();
                if (encode == null) {
                    encode = "UTF-8";
                }
                if (connection.getContentType() != null) {
                    String[] strings = connection.getContentType().split(";");
                    return new WebResourceResponse(strings[0], encode, connection.getInputStream());
                } else {
                    return new WebResourceResponse("document", encode, connection.getInputStream());
                }
            } catch (Exception e) {
                Log.d("Exception  ", e.toString());
                return new WebResourceResponse("document", "UTF-8", null);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
        return null;

        // HttpDns解析css文件的网络请求及图片请求
     /*   if ((scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https"))) {
            try {
                URL oldUrl = new URL(url);
                URLConnection connection = oldUrl.openConnection();
                // 获取HttpDns域名解析结果 // 通过HTTPDNS获取IP成功，进行URL替换和HOST头设置
                Log.d("HttpDns ips are: ", ips + " for host: " + oldUrl.getHost());
                String ip;
                if (ips.contains(";")) {
                    ip = ips.substring(0, ips.indexOf(";"));
                } else {
                    ip = ips;
                }
                String newUrl = url.replaceFirst(oldUrl.getHost(), ip);
                Log.d("newUrl a is: " , newUrl);
                connection =  new URL(newUrl).openConnection(); // 设置HTTP请求头Host域
                connection.setRequestProperty("Host", oldUrl.getHost());
                Log.d("ContentType a: ",connection.getContentType());
//有可能是text/html; charset=utf-8的形式，只需要第一个
                String[] strings = connection.getContentType().split(";");
                return new WebResourceResponse(strings[0], "UTF-8", connection.getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;*/
    }

    private static volatile SSLContext mIgnoreSSLContext;
    private static volatile HostnameVerifier mNullHostNameVerifier;

    public static SSLContext getIgnoreSSLContext() {
        if (mIgnoreSSLContext == null) {
            synchronized (WebviewDnsInterceptUtil.class) {
                if (mIgnoreSSLContext == null) {
                    try {
                        mIgnoreSSLContext = SSLContext.getInstance("TLS");
                        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }

                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            }

                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            }
                        }};
                        mIgnoreSSLContext.init(null, trustAllCerts, new SecureRandom());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mIgnoreSSLContext;
    }

    public static HostnameVerifier getNullHostNameVerifier() {
        if (mNullHostNameVerifier == null) {
            synchronized (WebviewDnsInterceptUtil.class) {
                mNullHostNameVerifier = new NullHostNameVerifier();
            }
        }
        return mNullHostNameVerifier;
    }

    public static class NullHostNameVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}



