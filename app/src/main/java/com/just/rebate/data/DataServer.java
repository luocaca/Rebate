package com.just.rebate.data;


import java.util.List;

public class DataServer {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"RebateLevel":1,"OrderNo":"123123123","UserId":1,"RebateAmount":100,"TotalRebateAmount":100,"UseRebateAmount":0,"Remark":"123","CreatedTime":"2020-01-13T18:19:56.2976584","Id":6}]
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
         * RebateLevel : 1
         * OrderNo : 123123123
         * UserId : 1
         * RebateAmount : 100
         * TotalRebateAmount : 100
         * UseRebateAmount : 0
         * Remark : 123
         * CreatedTime : 2020-01-13T18:19:56.2976584
         * Id : 6
         */

        private int RebateLevel;
        private String OrderNo;
        private int UserId;
        private int RebateAmount;
        private int TotalRebateAmount;
        private int UseRebateAmount;
        private String Remark;
        private String CreatedTime;
        private int Id;

        public int getRebateLevel() {
            return RebateLevel;
        }

        public void setRebateLevel(int RebateLevel) {
            this.RebateLevel = RebateLevel;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getRebateAmount() {
            return RebateAmount;
        }

        public void setRebateAmount(int RebateAmount) {
            this.RebateAmount = RebateAmount;
        }

        public int getTotalRebateAmount() {
            return TotalRebateAmount;
        }

        public void setTotalRebateAmount(int TotalRebateAmount) {
            this.TotalRebateAmount = TotalRebateAmount;
        }

        public int getUseRebateAmount() {
            return UseRebateAmount;
        }

        public void setUseRebateAmount(int UseRebateAmount) {
            this.UseRebateAmount = UseRebateAmount;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
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
