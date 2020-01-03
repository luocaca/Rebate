package com.just.integralmanagement.entity;

public class DetailBean {

    private int TotalIntegral;
    private int UseIntegral;
    private String ClientId;
    private String Account;
    private String OrderNo;
    private int IntegralNum;
    private int AppType;
    private String CreatedTime;
    private int Id;


    public int getTotalIntegral() {
        return TotalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        TotalIntegral = totalIntegral;
    }

    public int getUseIntegral() {
        return UseIntegral;
    }

    public void setUseIntegral(int useIntegral) {
        UseIntegral = useIntegral;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public int getIntegralNum() {
        return IntegralNum;
    }

    public void setIntegralNum(int integralNum) {
        IntegralNum = integralNum;
    }

    public int getAppType() {
        return AppType;
    }

    public void setAppType(int appType) {
        AppType = appType;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
