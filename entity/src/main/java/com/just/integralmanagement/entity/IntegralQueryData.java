package com.just.integralmanagement.entity;

import java.util.List;

public class IntegralQueryData {

    /**
     * Message : 查询成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"ClientId":null,"UserId":1,"User":{"Remark":null,"UserTypeId":0,"UserTypeName":"转转","UserDetail":null,"UserRoles":[],"UserClaims":[],"UserLogins":[],"UserTokens":[],"UserName":"admin","NormalizedUserName":null,"NickName":null,"Email":null,"NormalizeEmail":null,"EmailConfirmed":false,"PasswordHash":null,"HeadImg":null,"SecurityStamp":null,"ConcurrencyStamp":"f5c47ded-d2e2-4e2f-95df-518163522268","PhoneNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEnd":null,"LockoutEnabled":false,"AccessFailedCount":0,"IsSystem":false,"IsLocked":false,"CreatedTime":"2019-11-06 10:58:55","DeletedTime":null,"Id":0},"TJNum":1,"TJAmount":10,"CJNum":0,"CJAmount":0,"DFHNum":0,"DJNum":0,"DJAmount":0,"DZNum":0,"DZAmount":0,"JDNum":0,"JDAmount":0,"AppType":0}]
     */

    private String Message;
    private boolean Succeeded;
    private boolean Error;
    private int ResultType;
    private List<DataBean> Data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean isSucceeded() {
        return Succeeded;
    }

    public void setSucceeded(boolean Succeeded) {
        this.Succeeded = Succeeded;
    }

    public boolean isError() {
        return Error;
    }

    public void setError(boolean Error) {
        this.Error = Error;
    }

    public int getResultType() {
        return ResultType;
    }

    public void setResultType(int ResultType) {
        this.ResultType = ResultType;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ClientId : null
         * UserId : 1
         * User : {"Remark":null,"UserTypeId":0,"UserTypeName":"转转","UserDetail":null,"UserRoles":[],"UserClaims":[],"UserLogins":[],"UserTokens":[],"UserName":"admin","NormalizedUserName":null,"NickName":null,"Email":null,"NormalizeEmail":null,"EmailConfirmed":false,"PasswordHash":null,"HeadImg":null,"SecurityStamp":null,"ConcurrencyStamp":"f5c47ded-d2e2-4e2f-95df-518163522268","PhoneNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEnd":null,"LockoutEnabled":false,"AccessFailedCount":0,"IsSystem":false,"IsLocked":false,"CreatedTime":"2019-11-06 10:58:55","DeletedTime":null,"Id":0}
         * TJNum : 1
         * TJAmount : 10
         * CJNum : 0
         * CJAmount : 0
         * DFHNum : 0
         * DJNum : 0
         * DJAmount : 0
         * DZNum : 0
         * DZAmount : 0
         * JDNum : 0
         * JDAmount : 0
         * AppType : 0
         */

        private Object ClientId;
        private int UserId;
        private UserBean User;
        private int TJNum;
        private int TJAmount;
        private int CJNum;
        private int CJAmount;
        private int DFHNum;
        private int DJNum;
        private int DJAmount;
        private int DZNum;
        private int DZAmount;
        private int JDNum;
        private int JDAmount;
        private int AppType;

        public Object getClientId() {
            return ClientId;
        }

        public void setClientId(Object ClientId) {
            this.ClientId = ClientId;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public UserBean getUser() {
            return User;
        }

        public void setUser(UserBean User) {
            this.User = User;
        }

        public int getTJNum() {
            return TJNum;
        }

        public void setTJNum(int TJNum) {
            this.TJNum = TJNum;
        }

        public int getTJAmount() {
            return TJAmount;
        }

        public void setTJAmount(int TJAmount) {
            this.TJAmount = TJAmount;
        }

        public int getCJNum() {
            return CJNum;
        }

        public void setCJNum(int CJNum) {
            this.CJNum = CJNum;
        }

        public int getCJAmount() {
            return CJAmount;
        }

        public void setCJAmount(int CJAmount) {
            this.CJAmount = CJAmount;
        }

        public int getDFHNum() {
            return DFHNum;
        }

        public void setDFHNum(int DFHNum) {
            this.DFHNum = DFHNum;
        }

        public int getDJNum() {
            return DJNum;
        }

        public void setDJNum(int DJNum) {
            this.DJNum = DJNum;
        }

        public int getDJAmount() {
            return DJAmount;
        }

        public void setDJAmount(int DJAmount) {
            this.DJAmount = DJAmount;
        }

        public int getDZNum() {
            return DZNum;
        }

        public void setDZNum(int DZNum) {
            this.DZNum = DZNum;
        }

        public int getDZAmount() {
            return DZAmount;
        }

        public void setDZAmount(int DZAmount) {
            this.DZAmount = DZAmount;
        }

        public int getJDNum() {
            return JDNum;
        }

        public void setJDNum(int JDNum) {
            this.JDNum = JDNum;
        }

        public int getJDAmount() {
            return JDAmount;
        }

        public void setJDAmount(int JDAmount) {
            this.JDAmount = JDAmount;
        }

        public int getAppType() {
            return AppType;
        }

        public void setAppType(int AppType) {
            this.AppType = AppType;
        }

        public static class UserBean {
            /**
             * Remark : null
             * UserTypeId : 0
             * UserTypeName : 转转
             * UserDetail : null
             * UserRoles : []
             * UserClaims : []
             * UserLogins : []
             * UserTokens : []
             * UserName : admin
             * NormalizedUserName : null
             * NickName : null
             * Email : null
             * NormalizeEmail : null
             * EmailConfirmed : false
             * PasswordHash : null
             * HeadImg : null
             * SecurityStamp : null
             * ConcurrencyStamp : f5c47ded-d2e2-4e2f-95df-518163522268
             * PhoneNumber : null
             * PhoneNumberConfirmed : false
             * TwoFactorEnabled : false
             * LockoutEnd : null
             * LockoutEnabled : false
             * AccessFailedCount : 0
             * IsSystem : false
             * IsLocked : false
             * CreatedTime : 2019-11-06 10:58:55
             * DeletedTime : null
             * Id : 0
             */

            private Object Remark;
            private int UserTypeId;
            private String UserTypeName;
            private Object UserDetail;
            private String UserName;
            private Object NormalizedUserName;
            private Object NickName;
            private Object Email;
            private Object NormalizeEmail;
            private boolean EmailConfirmed;
            private Object PasswordHash;
            private Object HeadImg;
            private Object SecurityStamp;
            private String ConcurrencyStamp;
            private Object PhoneNumber;
            private boolean PhoneNumberConfirmed;
            private boolean TwoFactorEnabled;
            private Object LockoutEnd;
            private boolean LockoutEnabled;
            private int AccessFailedCount;
            private boolean IsSystem;
            private boolean IsLocked;
            private String CreatedTime;
            private Object DeletedTime;
            private int Id;
            private List<?> UserRoles;
            private List<?> UserClaims;
            private List<?> UserLogins;
            private List<?> UserTokens;

            public Object getRemark() {
                return Remark;
            }

            public void setRemark(Object Remark) {
                this.Remark = Remark;
            }

            public int getUserTypeId() {
                return UserTypeId;
            }

            public void setUserTypeId(int UserTypeId) {
                this.UserTypeId = UserTypeId;
            }

            public String getUserTypeName() {
                return UserTypeName;
            }

            public void setUserTypeName(String UserTypeName) {
                this.UserTypeName = UserTypeName;
            }

            public Object getUserDetail() {
                return UserDetail;
            }

            public void setUserDetail(Object UserDetail) {
                this.UserDetail = UserDetail;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public Object getNormalizedUserName() {
                return NormalizedUserName;
            }

            public void setNormalizedUserName(Object NormalizedUserName) {
                this.NormalizedUserName = NormalizedUserName;
            }

            public Object getNickName() {
                return NickName;
            }

            public void setNickName(Object NickName) {
                this.NickName = NickName;
            }

            public Object getEmail() {
                return Email;
            }

            public void setEmail(Object Email) {
                this.Email = Email;
            }

            public Object getNormalizeEmail() {
                return NormalizeEmail;
            }

            public void setNormalizeEmail(Object NormalizeEmail) {
                this.NormalizeEmail = NormalizeEmail;
            }

            public boolean isEmailConfirmed() {
                return EmailConfirmed;
            }

            public void setEmailConfirmed(boolean EmailConfirmed) {
                this.EmailConfirmed = EmailConfirmed;
            }

            public Object getPasswordHash() {
                return PasswordHash;
            }

            public void setPasswordHash(Object PasswordHash) {
                this.PasswordHash = PasswordHash;
            }

            public Object getHeadImg() {
                return HeadImg;
            }

            public void setHeadImg(Object HeadImg) {
                this.HeadImg = HeadImg;
            }

            public Object getSecurityStamp() {
                return SecurityStamp;
            }

            public void setSecurityStamp(Object SecurityStamp) {
                this.SecurityStamp = SecurityStamp;
            }

            public String getConcurrencyStamp() {
                return ConcurrencyStamp;
            }

            public void setConcurrencyStamp(String ConcurrencyStamp) {
                this.ConcurrencyStamp = ConcurrencyStamp;
            }

            public Object getPhoneNumber() {
                return PhoneNumber;
            }

            public void setPhoneNumber(Object PhoneNumber) {
                this.PhoneNumber = PhoneNumber;
            }

            public boolean isPhoneNumberConfirmed() {
                return PhoneNumberConfirmed;
            }

            public void setPhoneNumberConfirmed(boolean PhoneNumberConfirmed) {
                this.PhoneNumberConfirmed = PhoneNumberConfirmed;
            }

            public boolean isTwoFactorEnabled() {
                return TwoFactorEnabled;
            }

            public void setTwoFactorEnabled(boolean TwoFactorEnabled) {
                this.TwoFactorEnabled = TwoFactorEnabled;
            }

            public Object getLockoutEnd() {
                return LockoutEnd;
            }

            public void setLockoutEnd(Object LockoutEnd) {
                this.LockoutEnd = LockoutEnd;
            }

            public boolean isLockoutEnabled() {
                return LockoutEnabled;
            }

            public void setLockoutEnabled(boolean LockoutEnabled) {
                this.LockoutEnabled = LockoutEnabled;
            }

            public int getAccessFailedCount() {
                return AccessFailedCount;
            }

            public void setAccessFailedCount(int AccessFailedCount) {
                this.AccessFailedCount = AccessFailedCount;
            }

            public boolean isIsSystem() {
                return IsSystem;
            }

            public void setIsSystem(boolean IsSystem) {
                this.IsSystem = IsSystem;
            }

            public boolean isIsLocked() {
                return IsLocked;
            }

            public void setIsLocked(boolean IsLocked) {
                this.IsLocked = IsLocked;
            }

            public String getCreatedTime() {
                return CreatedTime;
            }

            public void setCreatedTime(String CreatedTime) {
                this.CreatedTime = CreatedTime;
            }

            public Object getDeletedTime() {
                return DeletedTime;
            }

            public void setDeletedTime(Object DeletedTime) {
                this.DeletedTime = DeletedTime;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public List<?> getUserRoles() {
                return UserRoles;
            }

            public void setUserRoles(List<?> UserRoles) {
                this.UserRoles = UserRoles;
            }

            public List<?> getUserClaims() {
                return UserClaims;
            }

            public void setUserClaims(List<?> UserClaims) {
                this.UserClaims = UserClaims;
            }

            public List<?> getUserLogins() {
                return UserLogins;
            }

            public void setUserLogins(List<?> UserLogins) {
                this.UserLogins = UserLogins;
            }

            public List<?> getUserTokens() {
                return UserTokens;
            }

            public void setUserTokens(List<?> UserTokens) {
                this.UserTokens = UserTokens;
            }
        }
    }
}
