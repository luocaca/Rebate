//package com.just.rebate.adapter.recycle;
//
//import com.just.rebate.entity.order.ReturnOrder;
//import com.just.rebate.entity.order.ReturnPlatform;
//import com.just.rebate.entity.order.ReturnShop;
//
//public class MultiItemEntity implements com.chad.library.adapter.base.entity.MultiItemEntity {
//    public static final int FIRST_TYPE = 0;
//    public static final int SECOND_TYPE = 1;
//    public static final int NORMAL_TYPE =2;
//
//    private int itemType;
//    private ReturnPlatform returnPlatform;
//    private ReturnShop returnShop;
//    private ReturnOrder returnOrder;
//
//
//    public MultiItemEntity(int itemType ,ReturnPlatform returnPlatform,ReturnShop returnShop,ReturnOrder returnOrder) {
//        this.itemType = itemType;
//        this.returnPlatform=returnPlatform;
//        this.returnShop=returnShop;
//        this.returnOrder=returnOrder;
//    }
//    @Override
//    public int getItemType() {
//        return itemType;
//    }
//
//    public ReturnPlatform getReturnPlatform(){
//        return returnPlatform;
//    }
//    public ReturnShop getReturnShop(){
//        return returnShop;
//    }
//    private ReturnOrder getReturnOrder(){
//        return returnOrder;
//    }
//
//}
