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
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
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
import okhttp3.OkHttpClient;

/**
 *
 */
public class TestGson {


    private static final String TAG = "TestGson";

    public static void main(String... args) {


        淘宝接口模拟();


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

    private static void 淘宝接口模拟() {
        OkHttpClient client = new OkHttpClient();

        setMyClinet(OkHttpUtils.getInstance());
        OkHttpUtils.get()
                .url("https://h5api.m.taobao.com/h5/mtop.order.queryboughtlist/4.0/?jsv=2.5.1&appKey=12574478&t=1568965003523&sign=213067c09728cb778883991c961ff542&api=mtop.order.queryboughtlist&v=4.0&ttid=%23%23h5&isSec=0&ecode=1&AntiFlood=true&AntiCreep=true&LoginRequest=true&needLogin=true&H5Request=true&type=jsonp&dataType=jsonp&callback=mtopjsonp1&data=%7B%22spm%22%3A%22a215s.7406091.toolbar.i2%22%2C%22page%22%3A1%2C%22tabCode%22%3A%22all%22%2C%22appVersion%22%3A%221.0%22%2C%22appName%22%3A%22tborder%22%7D")
                .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 9; MI CC 9 Build/PKQ1.181121.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36")
                .addHeader("accept", "*/*")
                .addHeader("referer", "https://main.m.taobao.com/olist/index.html?spm=a215s.7406091.toolbar.i2")
//                .addHeader("accept-encoding", "gzip, deflate")
                .addHeader("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .addHeader("cookie", "cookie2=55a9fead2d587c5e44f747b6371b73db, t=bbc9cd7ed74686509c4166d469e42c96, _tb_token_=7337765635b33, cna=++MFFosQkwQCAST5hDSiPfmz, _m_h5_tk=d46ffb009b82ade8f29e137bbb617b11_1568971809631, _m_h5_tk_enc=ed27b14c5d88bdcd735a8b3d0bcdc213, munb=652569484, WAPFDFDTGFG=%2B4cMKKP%2B8PI%2BMKpzutUh23NZIkHBMRk%3D, _w_app_lg=19, lgc=luochaojunaa, ntm=0, dnk=luochaojunaa, tracknick=luochaojunaa, ockeqeudmj=hK9Nq5k%3D, unb=652569484, uc3=lg2=V32FPkk%2Fw0dUvg%3D%3D&id2=VWojfHWaPLQP&nk2=D9ZMJf0xxB5b5t5x&vt3=F8dByuK3QCUIOPJoGAY%3D, uc1=cookie14=UoTaEC%2BQ0Gbk1w%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie21=VT5L2FSpczFp, csg=c5c93781, cookie17=VWojfHWaPLQP, skt=8d8580b05559801e, _cc_=U%2BGCWk%2F7og%3D%3D, _l_g_=Ug%3D%3D, sg=a49, _nk_=luochaojunaa, cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D, isg=BLKy6fGWO0mZPAd10mygaFP_CPpU67e3aKOZb3yL3mVQD1IJZNMG7bh--e0UOS51")
//                .addHeader("cookie", "cookie2=55a9fead2d587c5e44f747b6371b73db, t=bbc9cd7ed74686509c4166d469e42c96, _tb_token_=7337765635b33, cna=++MFFosQkwQCAST5hDSiPfmz, _m_h5_tk=d46ffb009b82ade8f29e137bbb617b11_1568971809631, _m_h5_tk_enc=ed27b14c5d88bdcd735a8b3d0bcdc213, munb=652569484, WAPFDFDTGFG=%2B4cMKKP%2B8PI%2BMKpzutUh23NZIkHBMRk%3D, _w_app_lg=19, lgc=luochaojunaa, ntm=0, dnk=luochaojunaa, tracknick=luochaojunaa, ockeqeudmj=hK9Nq5k%3D, unb=652569484, uc3=lg2=V32FPkk%2Fw0dUvg%3D%3D&id2=VWojfHWaPLQP&nk2=D9ZMJf0xxB5b5t5x&vt3=F8dByuK3QCUIOPJoGAY%3D, uc1=cookie14=UoTaEC%2BQ0Gbk1w%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie21=VT5L2FSpczFp, csg=c5c93781, cookie17=VWojfHWaPLQP, skt=8d8580b05559801e, _cc_=U%2BGCWk%2F7og%3D%3D, _l_g_=Ug%3D%3D, sg=a49, _nk_=luochaojunaa, cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D, isg=BLKy6fGWO0mZPAd10mygaFP_CPpU67e3aKOZb3yL3mVQD1IJZNMG7bh--e0UOS51")
//                .addHeader("cookie", "cookie2=55a9fead2d587c5e44f747b6371b73db, t=bbc9cd7ed74686509c4166d469e42c96, _tb_token_=7337765635b33, cna=++MFFosQkwQCAST5hDSiPfmz, _m_h5_tk=d46ffb009b82ade8f29e137bbb617b11_1568971809631, _m_h5_tk_enc=ed27b14c5d88bdcd735a8b3d0bcdc213, munb=652569484, WAPFDFDTGFG=%2B4cMKKP%2B8PI%2BMKpzutUh23NZIkHBMRk%3D, _w_app_lg=19, lgc=luochaojunaa, ntm=0, dnk=luochaojunaa, tracknick=luochaojunaa, ockeqeudmj=hK9Nq5k%3D, unb=652569484, uc3=lg2=V32FPkk%2Fw0dUvg%3D%3D&id2=VWojfHWaPLQP&nk2=D9ZMJf0xxB5b5t5x&vt3=F8dByuK3QCUIOPJoGAY%3D, uc1=cookie14=UoTaEC%2BQ0Gbk1w%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie21=VT5L2FSpczFp, csg=c5c93781, cookie17=VWojfHWaPLQP, skt=8d8580b05559801e, _cc_=U%2BGCWk%2F7og%3D%3D, _l_g_=Ug%3D%3D, sg=a49, _nk_=luochaojunaa, cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D, isg=BLKy6fGWO0mZPAd10mygaFP_CPpU67e3aKOZb3yL3mVQD1IJZNMG7bh--e0UOS51")
//                .addHeader("cookie", "cookie2=55a9fead2d587c5e44f747b6371b73db, t=bbc9cd7ed74686509c4166d469e42c96, _tb_token_=7337765635b33, cna=++MFFosQkwQCAST5hDSiPfmz, _m_h5_tk=d46ffb009b82ade8f29e137bbb617b11_1568971809631, _m_h5_tk_enc=ed27b14c5d88bdcd735a8b3d0bcdc213, munb=652569484, WAPFDFDTGFG=%2B4cMKKP%2B8PI%2BMKpzutUh23NZIkHBMRk%3D, _w_app_lg=19, lgc=luochaojunaa, ntm=0, dnk=luochaojunaa, tracknick=luochaojunaa, ockeqeudmj=hK9Nq5k%3D, unb=652569484, uc3=lg2=V32FPkk%2Fw0dUvg%3D%3D&id2=VWojfHWaPLQP&nk2=D9ZMJf0xxB5b5t5x&vt3=F8dByuK3QCUIOPJoGAY%3D, uc1=cookie14=UoTaEC%2BQ0Gbk1w%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie21=VT5L2FSpczFp, csg=c5c93781, cookie17=VWojfHWaPLQP, skt=8d8580b05559801e, _cc_=U%2BGCWk%2F7og%3D%3D, _l_g_=Ug%3D%3D, sg=a49, _nk_=luochaojunaa, cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D, isg=BLKy6fGWO0mZPAd10mygaFP_CPpU67e3aKOZb3yL3mVQD1IJZNMG7bh--e0UOS51")
//                .addHeader("cookie", "cookie2=55a9fead2d587c5e44f747b6371b73db, t=bbc9cd7ed74686509c4166d469e42c96, _tb_token_=7337765635b33, cna=++MFFosQkwQCAST5hDSiPfmz, _m_h5_tk=d46ffb009b82ade8f29e137bbb617b11_1568971809631, _m_h5_tk_enc=ed27b14c5d88bdcd735a8b3d0bcdc213, munb=652569484, WAPFDFDTGFG=%2B4cMKKP%2B8PI%2BMKpzutUh23NZIkHBMRk%3D, _w_app_lg=19, lgc=luochaojunaa, ntm=0, dnk=luochaojunaa, tracknick=luochaojunaa, ockeqeudmj=hK9Nq5k%3D, unb=652569484, uc3=lg2=V32FPkk%2Fw0dUvg%3D%3D&id2=VWojfHWaPLQP&nk2=D9ZMJf0xxB5b5t5x&vt3=F8dByuK3QCUIOPJoGAY%3D, uc1=cookie14=UoTaEC%2BQ0Gbk1w%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie21=VT5L2FSpczFp, csg=c5c93781, cookie17=VWojfHWaPLQP, skt=8d8580b05559801e, _cc_=U%2BGCWk%2F7og%3D%3D, _l_g_=Ug%3D%3D, sg=a49, _nk_=luochaojunaa, cookie1=UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D, isg=BLKy6fGWO0mZPAd10mygaFP_CPpU67e3aKOZb3yL3mVQD1IJZNMG7bh--e0UOS51")
//                .addHeader("cookie", "111=55a9fead2d587c5e44f747b6371b73db,22=bb")
                .addHeader("x-requested-with", "com.just.rebate")
                .addHeader(":path", "/h5/mtop.order.queryboughtlist/4.0/?jsv=2.5.1&appKey=12574478&t=1568965003523&sign=213067c09728cb778883991c961ff542&api=mtop.order.queryboughtlist&v=4.0&ttid=%23%23h5&isSec=0&ecode=1&AntiFlood=true&AntiCreep=true&LoginRequest=true&needLogin=true&H5Request=true&type=jsonp&dataType=jsonp&callback=mtopjsonp1&data=%7B%22spm%22%3A%22a215s.7406091.toolbar.i2%22%2C%22page%22%3A1%2C%22tabCode%22%3A%22all%22%2C%22appVersion%22%3A%221.0%22%2C%22appName%22%3A%22tborder%22%7D")
                .addHeader(":authority", "h5api.m.taobao.com")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "18d14457-78dc-47a4-96db-62c72dd0ec78")

//                .addHeader("cookie: lgc%3D", "luochaojunaa")


//                .addHeader("cookie2", "55a9fead2d587c5e44f747b6371b73db")
//                .addHeader("t", "bbc9cd7ed74686509c4166d469e42c96")
//                .addHeader("_tb_token_", "7337765635b33")
//                .addHeader("cna", "++MFFosQkwQCAST5hDSiPfmz")
//                .addHeader("_m_h5_tk", "d46ffb009b82ade8f29e137bbb617b11_1568971809631")
//                .addHeader("_m_h5_tk_enc", "ed27b14c5d88bdcd735a8b3d0bcdc213")
//                .addHeader("munb", "652569484")
//                .addHeader("WAPFDFDTGFG", "%2B4cMKKP%2B8PI%2BMKpzutUh23NZIkHBMRk%3D")
//                .addHeader("_w_app_lg", "19")
//                .addHeader("lgc", "luochaojunaa")
//                .addHeader("ntm", "0")
//                .addHeader("dnk", "luochaojunaa")
//                .addHeader("tracknick", "luochaojunaa")
//                .addHeader("ockeqeudmj", "hK9Nq5k%3D")
//                .addHeader("unb", "652569484")
//                .addHeader("uc3", "lg2=V32FPkk%2Fw0dUvg%3D%3D&id2=VWojfHWaPLQP&nk2=D9ZMJf0xxB5b5t5x&vt3=F8dByuK3QCUIOPJoGAY%3D")
//                .addHeader("uc1", "cookie14=UoTaEC%2BQ0Gbk1w%3D%3D&cookie15=UtASsssmOIJ0bQ%3D%3D&cookie21=VT5L2FSpczFp")
//                .addHeader("csg", "c5c93781")
//                .addHeader("cookie17", "VWojfHWaPLQP")
//                .addHeader("skt", "8d8580b05559801e")
//                .addHeader("_cc_", "U%2BGCWk%2F7og%3D%3D")
//                .addHeader("_l_g_", "Ug%3D%3D")
//                .addHeader("sg", "a49")
//                .addHeader("_nk_", "luochaojunaa")
//                .addHeader("cookie1", "UIHxSZwF3e96OEm4e2lF4I5B%2BkbjfNbtkPzv2gz4sL4%3D")
//                .addHeader("isg", "BLKy6fGWO0mZPAd10mygaFP_CPpU67e3aKOZb3yL3mVQD1IJZNMG7bh--e0UOS51")


//                .addHeader("jsv", "2.5.1")
//                .addHeader("appKey", "12574478")
//                .addHeader("t", "1568965003523")
//                .addHeader("sign", "213067c09728cb778883991c961ff542")
//                .addHeader("api", "mtop.order.queryboughtlist")
//
//
//                .addHeader("v", "4.0")
//
//
//                .addHeader("ttid", "##h5")


//                .addHeader("isSec", "0")
//                .addHeader("ecode", "1")
//                .addHeader("AntiFlood", "true")
//                .addHeader("AntiCreep", "true")
//                .addHeader("LoginRequest", "true")
//                .addHeader("needLogin", "true")
//                .addHeader("H5Request", "true")
//                .addHeader("type", "jsonp")
//                .addHeader("dataType", "jsonp")
//                .addHeader("callback", "mtopjsonp1")
//                .addHeader("data", "{\"spm\":\"a215s.7406091.toolbar.i2\",\"page\":1,\"tabCode\":\"all\",\"appVersion\":\"1.0\",\"appName\":\"tborder\"}")


                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        System.out.println("----------------" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("----------------" + response);

                    }
                });
        ;

//        Response response = client.newCall(request).execute();
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
        Log.i(TAG, "main: \n" + json);


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
        Log.i(TAG, "main: \n" + json);
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
