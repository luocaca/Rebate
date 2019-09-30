package me.luocaca.rebate;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.base.BaseResponse;
import com.just.rebate.entity.invite.InviteInfo;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.just.rebate.entity.order.跟踪处理.TrackingProcess;
import com.just.rebate.entity.order.跟踪处理.TrackingProcessOrder;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 *
 */
public class TestGson {


    private static final String TAG = "TestGson";

    public static void main(String... args) throws Exception {

//        getCookiesString();


//        OkHttpUtils.get().url("http://192.168.1.171:8080/download/recomcate.json").build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//
//                ZZCatearyParent zzCatearyParent = GsonUtil.getGson().fromJson(response, ZZCatearyParent.class);
//                System.out.println(zzCatearyParent);
//
//
//            }
//        });

//        OkHttpUtils.get().url("http://192.168.1.171:8080/download/category.json").build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//
//
//                ZZCateary zzCateary = GsonUtil.getGson().fromJson(response, ZZCateary.class);
//                System.out.println(zzCateary);
//
//
//            }
//        });


        淘宝接口模拟();
//        OkHttpUtils.get().url("http://192.168.1.171:8081/getCookies").build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                System.out.println( "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                System.out.println("response: " + response);
//            }
//        });


//        解密解密();


//        模拟文本写入("你妈妈很皮");

//        最近到账json产生机器();

//        GsonUtil.getGson().toJson();


        //6
        //4

        //9


//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://app.zhuanzhuan.com/zzx/transfer/getInfosByUserId?pageNumber=1&uidB=47699264619540&requestmark=1568613847021&pageSize=100")
//                .post(null)
//                .addHeader("User-Agent", "PostmanRuntime/7.15.0")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "da295d55-2826-424e-aed6-bc89eb8e0c99,e240d7ef-cae3-4ad6-af08-24d9d2c420a6")
//                .addHeader("Host", "app.zhuanzhuan.com")
//                .addHeader("cookie", "id58=c5/nR11/JicbG/N+Gl8DAg==")
//                .addHeader("accept-encoding", "gzip, deflate")
//                .addHeader("content-length", "")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("cache-control", "no-cache")
//                .build();
//
//        Response response = client.newCall(request).execute();


//        pageNumber	1
//        uidB	51055811882972416
//        requestmark	1568615438980
//        pageSize	20


        //用户id
//        String uid = "206529519643942144";// 53 键商品的人
        //        String uid = "40169788368402";// 53 键商品的人
//        String uid = "51055811882972416";//卖相机的
//        String uid = "1568613847021";//莆田鞋
//        String uid = "209445543402026752";// 67
//        String uid = "206529519643942144";// 67
//        String uid = "41978307012752000";// 自己的账号哦

//        System.out.println("start");
//        requestListRecycle(1, uid);


//        System.out.println("step5");
//        getInviteInfo();


//        getPlatformJson();

    }

    private static void 模拟文本写入(String string) throws Exception {

        File file = new File("D:\\java\\apache-tomcat-9.0.22-windows-x64\\apache-tomcat-9.0.22\\webapps\\download\\转转测试\\ppu.txt");


        FileOutputStream fileOutputStream = new FileOutputStream(file);

        fileOutputStream.write(string.getBytes());

    }

    private static void 解密解密() throws UnsupportedEncodingException {
        String str = "\n" +
                "isnewlabel=0&consumingTime=42&nowPrice=189&olon=106.567571&city=2044&pics=n_v29b9c7dd613e445eab86f6886500f0244.jpg%7Cn_v231586d4017c944fd993c7ac70d397968.jpg&isingroup=0&content=%E4%B8%80%E6%89%8B%E8%B4%A7%E6%BA%90%E3%80%82%E7%A8%B3%E5%AE%9A%E4%BB%B7%E6%A0%BC%EF%BC%8C&olat=33.999542&postageExplain=1&phash=f353acaccd99a952%7Cb810814486138a07&business=2125&title=%E8%BD%AC%E8%BD%AC%E5%95%86%E5%93%81%EF%BC%8C%E7%A7%BB%E5%8A%A8%E5%B0%81%E9%9D%A2&area=2125&videos=%5B%5D&picMd5s=76afc2b23cc9b391eecdd3272097bb72%7Caf582f58404d4da7541e2abcc7590aa6&cateId=2103008&";
        ;


        System.out.println(URLDecoder.decode(str, "utf-8").replace("&", "\n"));

    }


    public static OkHttpRequestBuilder setScheme(Map<String, String> params) {

        if (params.get(":method").trim().equals("POST")) {
            if (params.get("body") != null) {
                if (params.get("Content-Type") != null) {
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


    public static Map<String, String> saveRemove(Map<String, String> map, String... tas) {
        for (String ta : tas) {
            map.remove(ta);
        }
        return map;
    }

    private static void 淘宝接口模拟() {
//        OkHttpClient client = new OkHttpClient();


//        String decod = "/h5/mtop.order.queryboughtlist/4.0/?jsv=2.5.1&appKey=12574478&t=1569410448225&sign=6cc9042b8718ac7c318612b187df6dcb&api=mtop.order.queryboughtlist&v=4.0&ttid=%23%23h5&isSec=0&ecode=1&AntiFlood=true&AntiCreep=true&LoginRequest=true&needLogin=true&H5Request=true&type=jsonp&dataType=jsonp&callback=mtopjsonp1&data=%7B%22spm%22%3A%22a215s.7406091.toolbar.i2%22%2C%22page%22%3A1%2C%22tabCode%22%3A%22all%22%2C%22appVersion%22%3A%221.0%22%2C%22appName%22%3A%22tborder%22%7D";

        setMyClinet(OkHttpUtils.getInstance());

        Map<String, String> map = getCookiesString();

        setScheme(map)
//                .url("https://h5api.m.taobao.com/h5/mtop.order.queryboughtlist/4.0/?jsv=2.5.1&appKey=12574478&t=1569410448225&sign=6cc9042b8718ac7c318612b187df6dcb&api=mtop.order.queryboughtlist&v=4.0&ttid=##h5&isSec=0&ecode=1&AntiFlood=true&AntiCreep=true&LoginRequest=true&needLogin=true&H5Request=true&type=jsonp&dataType=jsonp&callback=mtopjsonp1&data={\"spm\":\"a215s.7406091.toolbar.i2\",\"page\":1,\"tabCode\":\"all\",\"appVersion\":\"1.0\",\"appName\":\"tborder\"}")
                .url(getUrlByMap(map))

//                .addHeader("User-Agent", getCookiesString().get("user-agent"))
//                .addHeader("Accept", getCookiesString().get("accept"))
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "ec793c51-1509-4b85-9716-eb78e3c4ae2b,aa653df4-c081-4333-85f5-b67543476464")
////                .addHeader("Host", getCookiesString().get("Host"))
//                .addHeader("Cookie", getCookiesString().get("cookie"))
////                .addHeader("Connection", getCookiesString().get("Connection"))
////                .addHeader("cache-control", getCookiesString().get("cache-control"))
//                .addHeader(":authority", getCookiesString().get(":authority"))
//                .addHeader(":path", getCookiesString().get(":path"))
//                .addHeader(":method", getCookiesString().get(":method"))
//                .addHeader(":scheme", getCookiesString().get(":scheme"))
//                .addHeader("referer", getCookiesString().get("referer"))
                .headers(saveRemove(map, "body"))
//                .addHeader("Content-Type", "json")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        System.out.println("----------------" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        try {
                            System.out.println("----------------" + GsonUtil.toPrettyFormat(response));
                        } catch (Exception e) {
                            System.out.println("----------------\n" + response);
                        }


//                        JdEntity jdEntity = GsonUtil.getGson().fromJson(response, JdEntity.class);


//                        System.out.println(jdEntity.toString());


                    }
                });
        ;

//        Response response = client.newCall(request).execute();
    }


    /**
     * 判断是否是json结构
     */
    public static boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    private static String getUrlByMap(Map<String, String> cookiesString) {
        StringBuffer url = new StringBuffer();
        url.append(cookiesString.get(":scheme"));
        url.append("://");
        url.append(cookiesString.get(":authority").trim());
        url.append(cookiesString.get(":path").trim());
        return url.toString();
    }


    public static Map<String, String> getCookiesString() {


        String string = ":method: POST\n" +
                ":path: /zz/transfer/getsearchword\n" +
                ":authority: app.zhuanzhuan.com\n" +
                ":scheme: https\n" +
                "cookie: t=15;sts=1569497335899;tk=5E73C295A0C1BC14D2C30A9F88469962;imei=352284043546120;v=6.11.1;channelid=market_1274;lat=33.999542;lon=106.567571;osv=22;model=Nexus+6;brand=Android;seq=117;uid=41978307012752000;PPU=\"TT=5fb1b816ad00d5dea8402ae2850e3e732e694d39&UID=41978307012752000&SF=ZHUANZHUAN&SCT=1569550077447&V=1&ET=1572138477447\"; Version=1; Domain=zhuanzhuan.com; Max-Age=2592000; Expires=Sun, 27-Oct-2019 01:23:07 GMT; Path=/;\n" +
                "content-type: application/x-www-form-urlencoded; charset=UTF-8\n" +
                "content-length: 0\n" +
                "accept-encoding: gzip\n" +
                "user-agent: Zhuan/6.11.1 (6011001) Dalvik/2.1.0 (Linux; U; Android 5.1.1; Nexus 6 Build/LYZ28N)\n" +
                "\n";

        Map<String, String> map = new HashMap<>();


        try {


            File file = new File("C:\\Users\\Administrator.DESKTOP-259D8ER\\Desktop\\cookies.txt");


            System.out.println(file.exists());


            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] b = string.getBytes();
//            new ByteArrayInputStream(string.getBytes())

            InputStreamReader inputreader
                    = new InputStreamReader(fileInputStream);
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


    private static void 最近到账json产生机器() {


        BaseResponse<List<TrackingProcess>> baseResponse = new BaseResponse();


        baseResponse.setCode(200);
        baseResponse.setMessage("成功");


        List<TrackingProcess> trackingProcesses = new ArrayList<>();
        trackingProcesses.add(creteList("淘宝"));
        trackingProcesses.add(creteList("天猫"));
        trackingProcesses.add(creteList("拼多多"));
        trackingProcesses.add(creteList("隔壁工厂"));
        baseResponse.setData(trackingProcesses);


        String json = GsonUtil.getGson().toJson(baseResponse);


        System.out.println(json);


//        GsonUtil.getGson().fromJson(json,)


    }

    private static TrackingProcess creteList(String platformName) {


        TrackingProcess trackingProcess = new TrackingProcess();

        trackingProcess.setPlatformName(platformName);
        trackingProcess.setReciverTime("2019-9-19");
        trackingProcess.setStatus("200");
        trackingProcess.setId("10086");
        trackingProcess.setOrders(getOrde());


        return trackingProcess;
    }


    private static List<TrackingProcessOrder> getOrde() {
        List<TrackingProcessOrder> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            TrackingProcessOrder trackingProcessOrder = new TrackingProcessOrder();
            trackingProcessOrder.setCoverUrl("https://m.360buyimg.com/mobilecms/s358x358_jfs/t18142/89/748990263/172316/65e9f4fe/5aa62f0fNf9082be7.jpg!q70.dpg.webp");
            trackingProcessOrder.setBackPrice(10 * i + "");
            trackingProcessOrder.setOrderName("订单" + i);
            trackingProcessOrder.setOrderNo(i * 23 + "");
            list.add(trackingProcessOrder);
        }


        return list;
    }


    public static void requestListRecycle(int fromPage, String userId) {

        int nextPage = fromPage + 1;


        System.out.println("step1");


        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");

//        OkHttpClient client = OkHttpUtils.getInstance().getOkHttpClient();
//设置代理,需要替换
//         Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
//        client = OkHttpUtils.getInstance().getOkHttpClient().newBuilder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888))).build();
//        client.newCall(new re).enqueue();


        setMyClinet(OkHttpUtils.getInstance());

        OkHttpUtils
                .post()
                .url("https://app.zhuanzhuan.com/zzx/transfer/getInfosByUserId?pageNumber=" + fromPage + "&uidB=" + userId + "&pageSize=20")
//                .addHeader("User-Agent", "PostmanRuntime/7.15.0")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "da295d55-2826-424e-aed6-bc89eb8e0c99,e240d7ef-cae3-4ad6-af08-24d9d2c420a6")
//                .addHeader("Host", "app.zhuanzhuan.com")
//                .addHeader("cookie", "id58=c5/nR11/JicbG/N+Gl8DAg==")
//                .addHeader("accept-encoding", "gzip, deflate")
//                .addHeader("content-length", "")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("cache-control", "no-cache")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ///Log.e("TAG", "日志");


                        //Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());

                        System.out.println("step2");

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();

                        System.out.println(response);


                        zzdataddd zzdataddd = GsonUtil.getGson().fromJson(response, zzdataddd.class);


                        System.out.println(zzdataddd);

                        System.out.println(zzdataddd.getRespData().getInfosArray().size());

                        if (zzdataddd.getRespData().getInfosArray().size() == 20) {
                            requestListRecycle(nextPage, userId);

                            System.out.println("step3");
                        } else {
                            //执行结束
                            System.out.println("step4");
                        }


                    }
                });
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
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888))).build();
//            client.sslSocketFactory(sslSocketFactory, trustManager);
//            OkHttpClient OkHttpClient = (okhttp3.OkHttpClient) field.get(instance);
//设置代理,需要替换
            field.set(instance, client);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //mOkHttpClient


    }

    HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };


    private static List<String> 插砖算法(List<String> sourceList) {
        return 插砖算法(sourceList, 0);
    }


    private static List<String> 插砖算法(List<String> sourceList, int groupId) {


        List<Group> groups = 获取最新的组(sourceList);


        System.out.println(Arrays.asList(groups));


        List<String> newlist = sourceList;


        //对第0组茶砖


//        for (int zucount = 0; zucount < groups.size(); zucount++) {

        for (int i1 = 0; i1 < 补全数量(groups.get(groupId)); i1++) {
            newlist.add(groups.get(groupId).groupPos + i1 + groups.get(groupId).groupChild + 1, "插砖");
        }


        if (groupId < groups.size() - 1) {

            return 插砖算法(newlist, ++groupId);
        } else {
            System.out.println(Arrays.asList(newlist));
            return newlist;
        }


    }


    public static List<Group> 获取最新的组(List<String> datas) {

        List<String> list = datas;

        List<Group> groups = new ArrayList<>();


        int groupPos = 0;
        for (int i = 0; i < list.size(); i++) {

            if (isHead(list.get(i))) {

                //寻找当前
                int count = 0;
                int nextPos = i;

                while (!nextIsHead(++nextPos, list)) {
                    count++;
                }


                System.out.println("当前组：" + groupPos + "  pos = " + i + "  total child = " + count);
                groups.add(new Group(groupPos, i, count));

                groupPos++;


            }


        }


        return groups;
    }


    public static class Group {
        public Group(int groupId, int groupPos, int groupChild) {
            this.groupPos = groupPos;
            this.groupId = groupId;
            this.groupChild = groupChild;

        }

        public int groupId;
        public int groupPos;
        public int groupChild;


        @Override
        public String toString() {
            return "Group{" +
                    "groupId=" + groupId +
                    ", groupPos=" + groupPos +
                    ", groupChild=" + groupChild +
                    '}';
        }
    }


    public static boolean nextIsHead(int nextIndex, List<String> list) {
        if (nextIndex >= list.size()) {
            return true;
        }

        return isHead(list.get(nextIndex));

    }


    public static int 补全数量(Group group) {
//        group.groupChild
//        group.groupPos
//        group.groupId
        int groupChild = group.groupChild;
        if ((groupChild % 3) == 0) {
            return 0;
        } else {
            return 3 - groupChild % 3;
        }
    }


    public static boolean isHead(String string) {

        return string.equals("head");
    }


    public static List getData() {

        List<String> list = new ArrayList();


        list.add("head"); // 1
        list.add("1");
        list.add("2");
        list.add("3");

        list.add("head");//5
        list.add("1");//6
        list.add("2");//7
        list.add("3");//8
        list.add("4");//9
        //10  11


        list.add("head");//10
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        return list;
    }

    /**
     * 获取邀请信息
     */
    private static void getInviteInfo() {


//        BaseResponse<InviteInfo> response = new BaseResponse<>();
        BaseResponse<InviteInfo> response = new BaseResponse<>();

        InviteInfo inviteInfo = new InviteInfo();
        inviteInfo.totalProfit = "300";
        inviteInfo.inviteCode = "ILOVEYOU";
        inviteInfo.numberOfFans = "60";
        inviteInfo.numberOfPartners = "5";
        inviteInfo.shareEarn = "100";
        inviteInfo.timelyProfit = "5.00";


        Map map = new HashMap();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        inviteInfo.setMap(map);


        Map map1 = new HashMap();
        map1.put("aa", inviteInfo);
        inviteInfo.setMap(null);
        map1.put("bb", inviteInfo);


        response.setCode(200);
        response.setMessage("成功");
        response.setData(inviteInfo);


        String json = GsonUtil.getGson().toJson(response);
        Log.i(TAG, "main: " + json);


//        getJsonBean(json);


    }


    public static void getJsonBean(String json) {


        Type t = new TypeToken<BaseResponse<JsonElement>>() {
        }.getType();

        BaseResponse<JsonElement> baseResponse = GsonUtil.getGson().fromJson(json, t);


        //((JsonObject) baseResponse.getData()).getAsJsonObject("aa").get("totalProfit")

        Log.i(TAG, "getJsonBean: " + baseResponse);


    }


    public static void getPlatformJson() {

        List<ReturnPlatform> returnPlatforms = new ArrayList<>();
        returnPlatforms.add(getPlatform("淘宝"));
        returnPlatforms.add(getPlatform("天猫"));
        returnPlatforms.add(getPlatform("京东"));
        returnPlatforms.add(getPlatform("拼多多"));
        returnPlatforms.add(getPlatform("其他平台"));


        String json = GsonUtil.getGson().toJson(returnPlatforms);
        Log.i(TAG, "main: " + json);
    }


    public static ReturnPlatform getPlatform(String platformName) {

        ReturnPlatform platform = new ReturnPlatform();
        platform.setPlatformName(platformName);
        platform.setEstimatedRebate("预估返利15.36元");
        platform.setOrderTime("2019-07-31");
        platform.setReturnShops(getShops(platformName));


        return platform;
    }

    private static List<ReturnShop> getShops(String shopName) {

        List<ReturnShop> returnShops = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            ReturnShop returnShop = new ReturnShop();

            returnShop.setId(i + "");
            returnShop.setShopName(shopName + "店铺" + i);
            returnShop.setReturnOrders(getOrders(returnShop.getShopName()));
            returnShops.add(returnShop);
        }


        return returnShops;
    }

    private static List<ReturnOrder> getOrders(String orderName) {

        List<ReturnOrder> returnOrders = new ArrayList<>();


        for (int i = 0; i < 6; i++) {
            ReturnOrder returnOrder = new ReturnOrder();
            returnOrder.setCoverUrl("http://img3.imgtn.bdimg.com/it/u=4186486800,813755701&fm=26&gp=0.jpg");
            returnOrder.setCommodityPrice("我是商品简介" + orderName + i);
            returnOrder.setOrderName(orderName + " 商品 " + i);
            returnOrder.setCommodityPrice("￥100.00");
            returnOrders.add(returnOrder);
        }


        return returnOrders;
    }


}
