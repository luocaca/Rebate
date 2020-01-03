package com.just.integralmanagement.entity;


import java.util.List;

public class DetailitemList {

    /**
     * Message : 更新成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"UserId":1,"User":{"Remark":null,"UserTypeId":0,"UserTypeName":"转转","UserDetail":null,"UserRoles":[],"UserClaims":[],"UserLogins":[],"UserTokens":[],"UserName":"admin","NormalizedUserName":null,"NickName":null,"Email":null,"NormalizeEmail":null,"EmailConfirmed":false,"PasswordHash":null,"HeadImg":null,"SecurityStamp":null,"ConcurrencyStamp":"ffc68536-f8af-43a8-b503-c6380a6cd699","PhoneNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEnd":null,"LockoutEnabled":false,"AccessFailedCount":0,"IsSystem":false,"IsLocked":false,"CreatedTime":"2019-10-30 14:52:15","DeletedTime":null,"Id":0},"TotalIntegral":0,"UseIntegral":0,"ClientId":"admin1","Account":"admin1","OrderNo":"1314564","IntegralNum":100,"AppType":0,"CreatedTime":"2019-10-16 11:36:06","Id":6},{"UserId":3,"User":{"Remark":null,"UserTypeId":0,"UserTypeName":"转转","UserDetail":null,"UserRoles":[],"UserClaims":[],"UserLogins":[],"UserTokens":[],"UserName":"smsdown","NormalizedUserName":null,"NickName":null,"Email":null,"NormalizeEmail":null,"EmailConfirmed":false,"PasswordHash":null,"HeadImg":null,"SecurityStamp":null,"ConcurrencyStamp":"9c0bd683-ed0b-4549-9382-354997ba730c","PhoneNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEnd":null,"LockoutEnabled":false,"AccessFailedCount":0,"IsSystem":false,"IsLocked":false,"CreatedTime":"2019-10-30 14:52:15","DeletedTime":null,"Id":0},"TotalIntegral":0,"UseIntegral":0,"ClientId":"smsdown1","Account":"smsdown1","OrderNo":"23423423","IntegralNum":100,"AppType":1,"CreatedTime":"2019-10-16 11:56:52","Id":7},{"UserId":1,"User":{"Remark":null,"UserTypeId":0,"UserTypeName":"转转","UserDetail":null,"UserRoles":[],"UserClaims":[],"UserLogins":[],"UserTokens":[],"UserName":"admin","NormalizedUserName":null,"NickName":null,"Email":null,"NormalizeEmail":null,"EmailConfirmed":false,"PasswordHash":null,"HeadImg":null,"SecurityStamp":null,"ConcurrencyStamp":"092b087a-04e5-4820-b5a6-576de8e6a5d4","PhoneNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEnd":null,"LockoutEnabled":false,"AccessFailedCount":0,"IsSystem":false,"IsLocked":false,"CreatedTime":"2019-10-30 14:52:15","DeletedTime":null,"Id":0},"TotalIntegral":0,"UseIntegral":0,"ClientId":"111","Account":"111","OrderNo":"111","IntegralNum":11,"AppType":0,"CreatedTime":"2019-10-25 17:21:27","Id":9}]
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
         * UserId : 1
         * User : {"Remark":null,"UserTypeId":0,"UserTypeName":"转转","UserDetail":null,"UserRoles":[],"UserClaims":[],"UserLogins":[],"UserTokens":[],"UserName":"admin","NormalizedUserName":null,"NickName":null,"Email":null,"NormalizeEmail":null,"EmailConfirmed":false,"PasswordHash":null,"HeadImg":null,"SecurityStamp":null,"ConcurrencyStamp":"ffc68536-f8af-43a8-b503-c6380a6cd699","PhoneNumber":null,"PhoneNumberConfirmed":false,"TwoFactorEnabled":false,"LockoutEnd":null,"LockoutEnabled":false,"AccessFailedCount":0,"IsSystem":false,"IsLocked":false,"CreatedTime":"2019-10-30 14:52:15","DeletedTime":null,"Id":0}
         * TotalIntegral : 0
         * UseIntegral : 0
         * ClientId : admin1
         * Account : admin1
         * OrderNo : 1314564
         * IntegralNum : 100
         * AppType : 0
         * CreatedTime : 2019-10-16 11:36:06
         * Id : 6
         */

        private int UserId;
        private UserBean User;
        private int TotalIntegral;
        private int UseIntegral;
        private String ClientId;
        private String Account;
        private String OrderNo;
        private int IntegralNum;
        private int AppType;
        private String CreatedTime;
        private int Id;

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

        public int getTotalIntegral() {
            return TotalIntegral;
        }

        public void setTotalIntegral(int TotalIntegral) {
            this.TotalIntegral = TotalIntegral;
        }

        public int getUseIntegral() {
            return UseIntegral;
        }

        public void setUseIntegral(int UseIntegral) {
            this.UseIntegral = UseIntegral;
        }

        public String getClientId() {
            return ClientId;
        }

        public void setClientId(String ClientId) {
            this.ClientId = ClientId;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public int getIntegralNum() {
            return IntegralNum;
        }

        public void setIntegralNum(int IntegralNum) {
            this.IntegralNum = IntegralNum;
        }

        public int getAppType() {
            return AppType;
        }

        public void setAppType(int AppType) {
            this.AppType = AppType;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
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
             * ConcurrencyStamp : ffc68536-f8af-43a8-b503-c6380a6cd699
             * PhoneNumber : null
             * PhoneNumberConfirmed : false
             * TwoFactorEnabled : false
             * LockoutEnd : null
             * LockoutEnabled : false
             * AccessFailedCount : 0
             * IsSystem : false
             * IsLocked : false
             * CreatedTime : 2019-10-30 14:52:15
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
