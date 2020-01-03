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
        @SerializedName("Host")
        public String Host; // FIXME check this code
        public String Connection;
        @SerializedName("User-Agent")
        public String UserAgent;
        public String Accept;
        public String Referer;
        @SerializedName("Accept-Encoding")
        public String AcceptEncoding;
        @SerializedName("Accept-Language")
        public String AcceptLanguage;
        public String Cookie;
    }
}
