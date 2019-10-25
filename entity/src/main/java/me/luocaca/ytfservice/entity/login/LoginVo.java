package me.luocaca.ytfservice.entity.login;

public class LoginVo {

    /**
     * code : 200
     * msg : 登录成功
     * obj : {"merchantId":"1184807534615523328","userType":"1","userId":"1184807535013982208","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTg0ODA3NTM1MDEzOTgyMjA4Iiwic2VydmljZVByb3ZpZGVySWQiOiIxMTc0MTQwNDQxMzE3MzkyMzg0IiwibWVyY2hhbnRJZCI6IjExODQ4MDc1MzQ2MTU1MjMzMjgiLCJpc3MiOiJtZXJjaGFudCIsImV4cCI6MTU3MzI3NTgzN30.kXunnJ-Uvg_ljQNWSi-idsWj1R_OSEI-IZzERXDBfu9TR9BhDm2Im9eqDk2mMxX5XVGjIOtRdRkVHynaGpUB8A"}
     */

    private int code;
    private String msg;
    private ObjBean obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * merchantId : 1184807534615523328
         * userType : 1
         * userId : 1184807535013982208
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTg0ODA3NTM1MDEzOTgyMjA4Iiwic2VydmljZVByb3ZpZGVySWQiOiIxMTc0MTQwNDQxMzE3MzkyMzg0IiwibWVyY2hhbnRJZCI6IjExODQ4MDc1MzQ2MTU1MjMzMjgiLCJpc3MiOiJtZXJjaGFudCIsImV4cCI6MTU3MzI3NTgzN30.kXunnJ-Uvg_ljQNWSi-idsWj1R_OSEI-IZzERXDBfu9TR9BhDm2Im9eqDk2mMxX5XVGjIOtRdRkVHynaGpUB8A
         */

        private String merchantId;
        private String userType;
        private String userId;
        private String token;

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
