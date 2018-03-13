package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Goods {
    private Integer id;

    private Integer userId;

    private Double price;

    private String keyword;

    private Integer goodsClass;

    private String goodsName;

    private String goodsDesc;

    private String remark;

    private Integer brandId;

    private Integer sex;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer status;

    private Integer yn;

    private Integer version;

}