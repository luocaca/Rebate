package com.just.integralmanagement.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ListAgentBean extends AbstractExpandableItem implements MultiItemEntity {

    private int Id;
    private String UserName;
    private String NickName;
    private String CreatedTime;
    private int TotalIntegral;
    private int ParentId;
    private String ParentName;
    private int DivideIntegral;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public int getTotalIntegral() {
        return TotalIntegral;
    }

    public void setTotalIntegral(int totalIntegral) {
        TotalIntegral = totalIntegral;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public int getDivideIntegral() {
        return DivideIntegral;
    }

    public void setDivideIntegral(int divideIntegral) {
        DivideIntegral = divideIntegral;
    }

    @Override
    public List getSubItems() {
        return null;
    }

    @Override
    public int getItemType() {
        return 1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
