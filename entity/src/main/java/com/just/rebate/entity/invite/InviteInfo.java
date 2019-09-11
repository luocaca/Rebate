package com.just.rebate.entity.invite;

import com.just.rebate.base.BaseEntity;

/**
 * 邀请信息
 */
public class InviteInfo extends BaseEntity {



    /**
     * 预计总收益
     */
    public String totalProfit;


    /**
     * 即将到账  钱
     */
    public String timelyProfit;


    /**
     * 合伙人数量
     */
    public String numberOfPartners;

    /**
     * 粉丝数量
     */
    public String numberOfFans;


    /**
     * 邀请码
     */
    public String inviteCode;


    /**
     * 分享能挣多少钱
     */
    public String shareEarn;


}
