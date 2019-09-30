package me.luocaca.rebate;

public class CookieBean {

    /**
     * UID=23340945140574976 : PPU="TT=f5dc5d662f400d0704cb2fa3d95d1cb7847570e2&UID=233409451405749760&SF=ZHUANZHUAN&SCT=1569568009250&V=1&ET=1572156409250"; Version=1; Domain=zhuanzhuan.com; Max-Age=2592000; Expires=Sun, 27-Oct-2019 06:36:20 GMT; Path=/
     */

    private String UID = "";

    private String cookieString = "";


    public CookieBean(String UID, String cookieString) {
        this.UID = UID;
        this.cookieString = cookieString;
    }

    public String getCookieString() {
        return cookieString;
    }

    public void setCookieString(String cookieString) {
        this.cookieString = cookieString;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
