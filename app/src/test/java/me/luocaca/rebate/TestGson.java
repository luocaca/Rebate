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


        最近到账json产生机器();

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


        System.out.println(GsonUtil.getGson().toJson(baseResponse));


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
