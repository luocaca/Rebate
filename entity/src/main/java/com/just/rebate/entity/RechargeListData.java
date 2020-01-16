package com.just.rebate.entity;

import java.util.List;

public class RechargeListData {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"OrderType":3,"IsState":1,"UpdateTime":"2020-01-14T09:58:23.6959335","PayMode":{"UserId":1,"ReceivingType":1,"ReceivingBank":2,"IsDefault":1,"PayModeType":2,"IsState":0,"ReceivingLevel":0,"BankBranch":"shoukuannongye","ReceivingAccount":"shoukuannongye","ReceivingName":"shoukuannongye","ReceivingImg":"","CreatedTime":"2020-01-07T16:27:46.304747","Id":10},"UserUserName":null,"ReceivePayModeId":4,"RReceivingAccount":"622425124221255222","RReceivingName":"哇哈哈","IntegralNum":0,"Id":40,"UserId":1,"PayModeId":10,"Remark":"","OrderNo":"20200114095800844","RealAmount":-100,"Amount":-100,"TotalIntegral":0,"UseIntegral":0,"CreatedTime":"2020-01-14T09:58:00.8472401"},{"OrderType":1,"IsState":0,"UpdateTime":null,"PayMode":{"UserId":1,"ReceivingType":2,"ReceivingBank":1,"IsDefault":1,"PayModeType":1,"IsState":0,"ReceivingLevel":0,"BankBranch":"付款支付宝","ReceivingAccount":"付款支付宝","ReceivingName":"付款支付宝","ReceivingImg":"upload-files/timg-011316043004.jpg","CreatedTime":"2020-01-13T16:04:34.1342135","Id":13},"UserUserName":null,"ReceivePayModeId":14,"RReceivingAccount":"收款支付宝","RReceivingName":"收款支付宝","IntegralNum":100,"Id":39,"UserId":1,"PayModeId":13,"Remark":null,"OrderNo":"20200114095722820","RealAmount":100,"Amount":100,"TotalIntegral":200,"UseIntegral":0,"CreatedTime":"2020-01-14T09:57:22.8222485"},{"OrderType":1,"IsState":1,"UpdateTime":"2020-01-14T09:56:41.1385858","PayMode":{"UserId":1,"ReceivingType":1,"ReceivingBank":1,"IsDefault":1,"PayModeType":1,"IsState":0,"ReceivingLevel":0,"BankBranch":"软三支行","ReceivingAccount":"622425124221255222","ReceivingName":"哇哈哈","ReceivingImg":"","CreatedTime":"2020-01-06T15:27:08.6899176","Id":4},"UserUserName":null,"ReceivePayModeId":10,"RReceivingAccount":"shoukuannongye","RReceivingName":"shoukuannongye","IntegralNum":100,"Id":38,"UserId":1,"PayModeId":4,"Remark":"201908","OrderNo":"20200114095620081","RealAmount":100,"Amount":100,"TotalIntegral":100,"UseIntegral":0,"CreatedTime":"2020-01-14T09:56:20.0834084"}]
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
         * OrderType : 3
         * IsState : 1
         * UpdateTime : 2020-01-14T09:58:23.6959335
         * PayMode : {"UserId":1,"ReceivingType":1,"ReceivingBank":2,"IsDefault":1,"PayModeType":2,"IsState":0,"ReceivingLevel":0,"BankBranch":"shoukuannongye","ReceivingAccount":"shoukuannongye","ReceivingName":"shoukuannongye","ReceivingImg":"","CreatedTime":"2020-01-07T16:27:46.304747","Id":10}
         * UserUserName : null
         * ReceivePayModeId : 4
         * RReceivingAccount : 622425124221255222
         * RReceivingName : 哇哈哈
         * IntegralNum : 0
         * Id : 40
         * UserId : 1
         * PayModeId : 10
         * Remark :
         * OrderNo : 20200114095800844
         * RealAmount : -100
         * Amount : -100
         * TotalIntegral : 0
         * UseIntegral : 0
         * CreatedTime : 2020-01-14T09:58:00.8472401
         */

        private int OrderType;
        private int IsState;
        private String UpdateTime;
        private PayModeBean PayMode;
        private Object UserUserName;
        private int ReceivePayModeId;
        private String RReceivingAccount;
        private String RReceivingName;
        private int IntegralNum;
        private int Id;
        private int UserId;
        private int PayModeId;
        private String Remark;
        private String OrderNo;
        private int RealAmount;
        private int Amount;
        private int TotalIntegral;
        private int UseIntegral;
        private String CreatedTime;

        public int getOrderType() {
            return OrderType;
        }

        public void setOrderType(int OrderType) {
            this.OrderType = OrderType;
        }

        public int getIsState() {
            return IsState;
        }

        public void setIsState(int IsState) {
            this.IsState = IsState;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public PayModeBean getPayMode() {
            return PayMode;
        }

        public void setPayMode(PayModeBean PayMode) {
            this.PayMode = PayMode;
        }

        public Object getUserUserName() {
            return UserUserName;
        }

        public void setUserUserName(Object UserUserName) {
            this.UserUserName = UserUserName;
        }

        public int getReceivePayModeId() {
            return ReceivePayModeId;
        }

        public void setReceivePayModeId(int ReceivePayModeId) {
            this.ReceivePayModeId = ReceivePayModeId;
        }

        public String getRReceivingAccount() {
            return RReceivingAccount;
        }

        public void setRReceivingAccount(String RReceivingAccount) {
            this.RReceivingAccount = RReceivingAccount;
        }

        public String getRReceivingName() {
            return RReceivingName;
        }

        public void setRReceivingName(String RReceivingName) {
            this.RReceivingName = RReceivingName;
        }

        public int getIntegralNum() {
            return IntegralNum;
        }

        public void setIntegralNum(int IntegralNum) {
            this.IntegralNum = IntegralNum;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getPayModeId() {
            return PayModeId;
        }

        public void setPayModeId(int PayModeId) {
            this.PayModeId = PayModeId;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public int getRealAmount() {
            return RealAmount;
        }

        public void setRealAmount(int RealAmount) {
            this.RealAmount = RealAmount;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
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

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public static class PayModeBean {
            /**
             * UserId : 1
             * ReceivingType : 1
             * ReceivingBank : 2
             * IsDefault : 1
             * PayModeType : 2
             * IsState : 0
             * ReceivingLevel : 0
             * BankBranch : shoukuannongye
             * ReceivingAccount : shoukuannongye
             * ReceivingName : shoukuannongye
             * ReceivingImg :
             * CreatedTime : 2020-01-07T16:27:46.304747
             * Id : 10
             */

            private int UserId;
            private int ReceivingType;
            private int ReceivingBank;
            private int IsDefault;
            private int PayModeType;
            private int IsState;
            private int ReceivingLevel;
            private String BankBranch;
            private String ReceivingAccount;
            private String ReceivingName;
            private String ReceivingImg;
            private String CreatedTime;
            private int Id;

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public int getReceivingType() {
                return ReceivingType;
            }

            public void setReceivingType(int ReceivingType) {
                this.ReceivingType = ReceivingType;
            }

            public int getReceivingBank() {
                return ReceivingBank;
            }

            public void setReceivingBank(int ReceivingBank) {
                this.ReceivingBank = ReceivingBank;
            }

            public int getIsDefault() {
                return IsDefault;
            }

            public void setIsDefault(int IsDefault) {
                this.IsDefault = IsDefault;
            }

            public int getPayModeType() {
                return PayModeType;
            }

            public void setPayModeType(int PayModeType) {
                this.PayModeType = PayModeType;
            }

            public int getIsState() {
                return IsState;
            }

            public void setIsState(int IsState) {
                this.IsState = IsState;
            }

            public int getReceivingLevel() {
                return ReceivingLevel;
            }

            public void setReceivingLevel(int ReceivingLevel) {
                this.ReceivingLevel = ReceivingLevel;
            }

            public String getBankBranch() {
                return BankBranch;
            }

            public void setBankBranch(String BankBranch) {
                this.BankBranch = BankBranch;
            }

            public String getReceivingAccount() {
                return ReceivingAccount;
            }

            public void setReceivingAccount(String ReceivingAccount) {
                this.ReceivingAccount = ReceivingAccount;
            }

            public String getReceivingName() {
                return ReceivingName;
            }

            public void setReceivingName(String ReceivingName) {
                this.ReceivingName = ReceivingName;
            }

            public String getReceivingImg() {
                return ReceivingImg;
            }

            public void setReceivingImg(String ReceivingImg) {
                this.ReceivingImg = ReceivingImg;
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
        }
    }
}
