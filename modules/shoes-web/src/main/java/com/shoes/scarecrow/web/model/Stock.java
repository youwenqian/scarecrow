package com.shoes.scarecrow.web.model;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-3-10
 * Time 下午6:56
 */
public class Stock {
    private String id;
    private String name;

    public Stock() {
    }

    public Stock(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
