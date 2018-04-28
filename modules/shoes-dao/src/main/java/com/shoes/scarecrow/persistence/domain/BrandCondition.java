package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌插叙条件
 * @author wangyucheng
 * @description
 * @create 2018/3/13 21:05
 */
@Data
public class BrandCondition extends BaseCondition implements Serializable{

    private static final long serialVersionUID = -1380792238465131203L;

    private Integer id;

    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String brandName) {
        this.name = brandName;
    }
}