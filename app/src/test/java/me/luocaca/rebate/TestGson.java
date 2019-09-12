package me.luocaca.rebate;

import android.net.Uri;
import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.base.BaseResponse;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.invite.InviteInfo;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.rebate.commom.util.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *
 */
public class TestGson {


    private static final String TAG = "TestGson";

    public static void main(String... args) {


//        GsonUtil.getGson().toJson();


        //6
        //4

        //9


        OkHttpUtils
                .get()
                .url("http://192.168.1.171:8080/download/homejson.txt")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ///Log.e("TAG", "日志");


                        //Toast.makeText(mActivity, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();


                        Type t = new TypeToken<List<HomeItem>>() {
                        }.getType();

                        List<HomeItem> list = GsonUtil.getGson().fromJson(response, t);



                    }
                });

//        getInviteInfo();


//        getPlatformJson();

    }


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

            return 插砖算法(newlist,++groupId);
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
