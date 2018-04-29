package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-28
 * Time 上午12:53
 */
@Data
public class Order implements Serializable {
    private Integer id;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodsId;
    private Integer qty;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private Date completeTime;
    private Integer status;
    private Integer yn;
}
