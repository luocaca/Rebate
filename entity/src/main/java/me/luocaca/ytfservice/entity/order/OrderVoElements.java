package me.luocaca.ytfservice.entity.order;

import com.google.gson.JsonElement;

import org.litepal.crud.LitePalSupport;

/**
 *
 *
 *
 * LitePalSupport
 */
public class OrderVoElements extends LitePalSupport {
    /**
     * code : 200
     * msg : success
     * obj : {"content":{"2019-10-25":{"orders":[{"id":"1187613377996075008","orderNumber":"201910251414287485","totalPrice":null,"disCountPrice":null,"actPayPrice":1,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-25 14:14:28","payTime":"2019-10-25 14:14:28","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1187554540794490880","orderNumber":"201910251020401811","totalPrice":null,"disCountPrice":null,"actPayPrice":3,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-25 10:20:40","payTime":"2019-10-25 10:20:40","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1187553784662142976","orderNumber":"201910251017400811","totalPrice":null,"disCountPrice":null,"actPayPrice":1,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-25 10:17:40","payTime":"2019-10-25 10:17:40","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null}],"actPrice":null,"refundPrice":null},"2019-10-21":{"orders":[{"id":"1186277286626013184","orderNumber":"201910212145196865","totalPrice":null,"disCountPrice":null,"actPayPrice":1,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-21 21:45:19","payTime":"2019-10-21 21:45:32","status":2,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1186232153419362304","orderNumber":"201910211845599915","totalPrice":null,"disCountPrice":null,"actPayPrice":1,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-21 18:45:59","payTime":"2019-10-21 18:45:59","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1186225880917422080","orderNumber":"201910211821030392","totalPrice":null,"disCountPrice":null,"actPayPrice":1,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-21 18:21:03","payTime":"2019-10-21 18:21:03","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1186225207177981952","orderNumber":"201910211818239489","totalPrice":null,"disCountPrice":null,"actPayPrice":100,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-21 18:18:23","payTime":"2019-10-21 18:18:23","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1186225121677094912","orderNumber":"201910211818028064","totalPrice":null,"disCountPrice":null,"actPayPrice":100,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-21 18:18:02","payTime":"2019-10-21 18:18:02","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null}],"actPrice":null,"refundPrice":null},"2019-10-20":{"orders":[{"id":"1185897399113297920","orderNumber":"201910202035479232","totalPrice":null,"disCountPrice":null,"actPayPrice":10,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-20 20:35:47","payTime":"2019-10-20 20:35:47","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null},{"id":"1185897179671506944","orderNumber":"201910202034555776","totalPrice":null,"disCountPrice":null,"actPayPrice":1,"refundPrice":0,"payWay":2,"payWayName":null,"createTime":"2019-10-20 20:34:55","payTime":"2019-10-20 20:34:55","status":1,"statusName":null,"traceNo":null,"prepayFlag":null,"prepayFlagName":null,"storeName":null,"userName":null,"authorizationCode":null,"date":null}],"actPrice":null,"refundPrice":null}},"totalElements":16}
     */
    public int code;
    public String msg;
    public ObjBean obj;

    public static class ObjBean {
        public JsonElement content;
    }

}
