package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class StockFlow {
    private Integer id;

    private Integer userId;

    private Integer stockId;

    private Integer goodsId;

    private Integer goodsExtendId;

    private String batchNo;

    private String flowType;

    private Long num;

    private Double price;

    private String stockAddress;

    private Date stockDate;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String remark;

}