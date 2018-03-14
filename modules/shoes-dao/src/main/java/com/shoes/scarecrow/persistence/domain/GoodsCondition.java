package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/14 20:58
 */
@Data
public class GoodsCondition extends BaseCondition implements Serializable {

    private static final long serialVersionUID = -1203687943734944473L;

    private Integer userId;

    private String keyword;

    private Integer goodsClass;

    private String goodsName;

    private Integer brandId;

    private Integer sex;

    private Integer status;

}