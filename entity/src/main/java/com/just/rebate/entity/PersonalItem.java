package com.just.rebate.entity;

public class PersonalItem  {
    public String  headImage ;
    public int invitationCode ;
    public int account ;
    public int integral;

    public PersonalItem(String headImage,int invitationCode,int account,int integral){

    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public int getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(int invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }


}
