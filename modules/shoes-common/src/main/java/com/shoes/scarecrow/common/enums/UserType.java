package com.shoes.scarecrow.common.enums;

public enum UserType {

    ADMIN(0, "管理员"),
    BUSINESS(1,"商家");

    private  int key;

    private String desc;

    UserType(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
