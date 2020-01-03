package com.just.integralmanagement.entity;

import java.util.List;

public class LoginDataBean {

    /**
     * Id : 1
     * UserName : admin
     * NickName : 大站长
     * Email : i66soft@qq.com
     * HeadImg : null
     * IsAdmin : true
     * Roles : ["系统管理员"]
     * ExtendData : {}
     */

    private int Id;
    private String UserName;
    private String NickName;
    private String Email;
    private Object HeadImg;
    private boolean IsAdmin;
    private ExtendDataBean ExtendData;
    private List<String> Roles;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Object getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(Object HeadImg) {
        this.HeadImg = HeadImg;
    }

    public boolean isIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(boolean IsAdmin) {
        this.IsAdmin = IsAdmin;
    }

    public ExtendDataBean getExtendData() {
        return ExtendData;
    }

    public void setExtendData(ExtendDataBean ExtendData) {
        this.ExtendData = ExtendData;
    }

    public List<String> getRoles() {
        return Roles;
    }

    public void setRoles(List<String> Roles) {
        this.Roles = Roles;
    }

    public static class ExtendDataBean {
    }
}
