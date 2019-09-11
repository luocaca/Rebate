package me.luocaca.rebate;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.just.rebate.base.BaseResponse;
import com.just.rebate.entity.invite.InviteInfo;
import com.just.rebate.entity.order.ReturnOrder;
import com.just.rebate.entity.order.ReturnPlatform;
import com.just.rebate.entity.order.ReturnShop;
import com.rebate.commom.util.GsonUtil;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TestGson {


    private static final String TAG = "TestGson";

    public static void main(String... args) {


//        GsonUtil.getGson().toJson();


        getInviteInfo();


//        getPlatformJson();

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
