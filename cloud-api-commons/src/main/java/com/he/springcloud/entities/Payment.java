package com.he.springcloud.entities;

import java.io.Serializable;

public class Payment implements Serializable {
    private long id;//数据库是bigint
    private String serial;

    public Payment() {
    }

    public Payment(long id, String serial) {
        this.id = id;
        this.serial = serial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}