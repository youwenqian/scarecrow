package com.shoes.scarecrow.common.enums;

/**
 * 尺码
 */
public enum ShoeSize {

    size_34(34,"34码"),
    size_35(35,"35码"),
    size_36(36,"36码"),
    size_37(37,"37码"),
    size_38(38,"38码"),
    size_39(39,"39码"),
    size_40(40,"40码"),
    size_41(41,"41码"),
    size_42(42,"42码"),
    size_43(43,"43码"),
    size_44(44,"44码"),
    size_45(45,"45码"),
    size_46(46,"46码");

    private int size;
    private String desc;

    ShoeSize(int size, String desc) {
        this.size = size;
        this.desc = desc;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
