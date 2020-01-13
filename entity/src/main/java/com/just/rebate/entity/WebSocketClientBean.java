package com.just.rebate.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class WebSocketClientBean {

    @SerializedName("Version")
    public String _$Version21; // FIXME check this code
    public String Mothed;
    public String Url;
    public Map<String,String> Headers;
    public String Content;

    public static class HeadersBean {
        public String Host;
        public String Accept;
        public String Referer;
        public String Cookie;
    }
}
