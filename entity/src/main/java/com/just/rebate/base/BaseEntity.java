package com.just.rebate.base;

import java.io.Serializable;

/**
 * 基础entity
 */
public class BaseEntity implements Serializable {


    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
