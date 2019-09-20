package com.just.rebate.base;

import java.io.Serializable;
import java.util.Map;

/**
 * 基础entity
 */
public class BaseEntity implements Serializable {


    private String id;


    private Map map;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }



}
