package com.just.rebate.ui.activity.web.web_util;

import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;


public class WebUtil {


    private  Context context;

    /**
     * 获取 Headers 参数
     */
    public Map getMap(WebResourceRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, String> requestHeaders = request.getRequestHeaders();
            String referer = requestHeaders.get("referer");
            String age = requestHeaders.get("age");
            String age2 = requestHeaders.get("test1");
            String age3 = requestHeaders.get("test2");
            String age4 = requestHeaders.get("test3");
            String s = request.getUrl().toString();
            return requestHeaders;
        }
        return null;
    }




    /**
     * 获取URL的基本域名
     */
    private String getBaseUrl(String url) {
        url = url.replace("http://", "").replace("https://", "").replace("ftb://", "");
        if (url.contains("/")) {
            url = url.substring(0, url.indexOf('/'));
        }
        return url;
    }


    /**
     * 解析域名获取IP数组
     */
    public String[] parseHostGetIPAddress(String host) {
        String[] ipAddressArr = null;
        try {
            InetAddress[] inetAddressArr = InetAddress.getAllByName(host);
            if (inetAddressArr != null && inetAddressArr.length > 0) {
                ipAddressArr = new String[inetAddressArr.length];
                for (int i = 0; i < inetAddressArr.length; i++) {
                    ipAddressArr[i] = inetAddressArr[i].getHostAddress();
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
        return ipAddressArr;
    }

    /**
     * 拦截指定网址
     */
    boolean getWrrThread(HandlerUtil.HandlerHolder handlerHolder, String url) {
        LogUtil.e("getWrrThread______处理拦截", url);
        try {
            //特殊请求头，拦截跳转APP 直接调用其他APP
            if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("ftp")) {
                Message message = handlerHolder.obtainMessage();
                message.obj = url;
                if (url.startsWith("fzhifu")) {
                    message.what = ConstantPool.START_FZHIFU;
                } else {
                    message.what = ConstantPool.START_APP;
                }
                handlerHolder.sendMessage(message);
                return true;
            }


        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        return false;
    }


    //访问拦截网址 解密反馈 并传递意图
    private void getWrr(final HandlerUtil.HandlerHolder handlerHolder, final String urlPath) {
        LogUtil.e("getWrr______拦截 - 请求服务器", urlPath);


    }

    /**
     * 替换所有
     * 获取数据WebResourceResponse
     * b判断只加载一次
     */
    public WebResourceResponse getWrr(String urlPath) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            //获取网址代码
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(40 * 1000);
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String s = stringBuilder.toString();
            //           return new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(FileUtil.readFileString("test1.html").getBytes()));
            return new WebResourceResponse("text/html", "utf-8", new ByteArrayInputStream(s.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }





}
