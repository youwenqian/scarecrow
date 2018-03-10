package com.shoes.scarecrow.common.enums;

/**
 * 进货性质
 * @author wangyucheng
 * @description
 * @create 2018/3/6 19:45
 */
public enum StockType {

    TAKE_GOODS_ABROAD(1, "国外拿货"),
    MICRO_BUSINESS(2, "国内微商"),
    TAOBAO_SELLER(3,"淘宝销售");

    private  int key;

    private String desc;

    StockType(int key, String desc) {
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