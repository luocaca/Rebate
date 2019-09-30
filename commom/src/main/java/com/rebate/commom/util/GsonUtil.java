package com.rebate.commom.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil {


    private static Gson gson;


    /**
     * 获取gson
     *
     * @return
     */
    public static Gson getGson() {


        if (gson == null) {
//            Type t = new TypeToken<SignalRMessageT<BaseResponseT<AccountData>>>() {
//            }.getType();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLenient();
            gsonBuilder.setPrettyPrinting();

            //全局大写序列号
//            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
            gson = gsonBuilder.create();

        }


        return gson;

    }


    public static String toPrettyFormat(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonObject);
    }

}
