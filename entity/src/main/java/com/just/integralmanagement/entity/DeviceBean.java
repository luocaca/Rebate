package com.just.integralmanagement.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class DeviceBean {

    /**
     * Message : 获取成功
     * Succeeded : true
     * Error : false
     * ResultType : 3
     * Data : [{"Id":4,"UserName":"wcn1","NickName":"wcn1","CreatedTime":"2019-11-28 16:46:06","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":5,"UserName":"wcn2","NickName":"wcn2","CreatedTime":"2019-11-28 16:47:05","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":6,"UserName":"wcn3","NickName":"wcn3","CreatedTime":"2019-11-28 16:48:09","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":7,"UserName":"wcn4","NickName":"wcn4","CreatedTime":"2019-11-28 16:49:36","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":8,"UserName":"wcn5","NickName":"wcn5","CreatedTime":"2019-11-28 16:50:00","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":9,"UserName":"wcn6","NickName":"wcn6","CreatedTime":"2019-11-28 17:10:29","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":10,"UserName":"wcn7","NickName":"wcn7","CreatedTime":"2019-11-28 17:10:43","TotalIntegral":5400,"ParentId":1,"ParentName":"admin","DivideIntegral":1080,"listAgent":[{"Id":11,"UserName":"wcn7_1","NickName":"wcn7_1","CreatedTime":"2019-11-28 17:20:07","TotalIntegral":2000,"ParentId":10,"ParentName":"wcn7","DivideIntegral":200,"listAgent":null},{"Id":12,"UserName":"wcn7_2","NickName":"wcn7_2","CreatedTime":"2019-11-28 17:20:15","TotalIntegral":0,"ParentId":10,"ParentName":"wcn7","DivideIntegral":0,"listAgent":null},{"Id":13,"UserName":"wcn7_3","NickName":"wcn7_3","CreatedTime":"2019-11-28 17:20:20","TotalIntegral":0,"ParentId":10,"ParentName":"wcn7","DivideIntegral":0,"listAgent":null}]},{"Id":14,"UserName":"justsky","NickName":"justsky","CreatedTime":"2019-11-29 09:56:12","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":15,"UserName":"18584802545","NickName":"18584802545","CreatedTime":"2019-12-03 09:59:26","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":16,"UserName":"wang123","NickName":"wang123","CreatedTime":"2019-12-03 10:05:43","TotalIntegral":0,"ParentId":1,"ParentName":"admin","DivideIntegral":0,"listAgent":[]},{"Id":17,"UserName":"just","NickName":"just","CreatedTime":"2019-12-03 18:06:11","TotalIntegral":1000,"ParentId":1,"ParentName":"admin","DivideIntegral":200,"listAgent":[]}]
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





    public static class DataBean extends AbstractExpandableItem<ListAgentBean> implements MultiItemEntity{
        /**
         * Id : 4
         * UserName : wcn1
         * NickName : wcn1
         * CreatedTime : 2019-11-28 16:46:06
         * TotalIntegral : 0
         * ParentId : 1
         * ParentName : admin
         * DivideIntegral : 0
         * listAgent : []
         */

        private int Id;
        private String UserName;
        private String NickName;
        private String CreatedTime;
        private int TotalIntegral;
        private int ParentId;
        private String ParentName;
        private int DivideIntegral;
        private List<ListAgentBean> listAgent;


        public List<ListAgentBean> getSubItems() {
            return listAgent;
        }

        @Override
        public int getLevel() {
            return 0;
        }


        public int getItemType() {
            return 0;
        }


        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String NickName) {
            this.NickName = NickName;
        }

        public String getCreatedTime() {
            return CreatedTime;
        }

        public void setCreatedTime(String CreatedTime) {
            this.CreatedTime = CreatedTime;
        }

        public int getTotalIntegral() {
            return TotalIntegral;
        }

        public void setTotalIntegral(int TotalIntegral) {
            this.TotalIntegral = TotalIntegral;
        }

        public int getParentId() {
            return ParentId;
        }

        public void setParentId(int ParentId) {
            this.ParentId = ParentId;
        }

        public String getParentName() {
            return ParentName;
        }

        public void setParentName(String ParentName) {
            this.ParentName = ParentName;
        }

        public int getDivideIntegral() {
            return DivideIntegral;
        }

        public void setDivideIntegral(int DivideIntegral) {
            this.DivideIntegral = DivideIntegral;
        }

        public List<ListAgentBean> getListAgent() {
            return listAgent;
        }

        public void setListAgent(List<ListAgentBean> listAgent) {
            this.listAgent = listAgent;
        }

    }
}
