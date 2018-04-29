package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Brand {
    private Integer id;

    private String name;

    private String remark;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer status;

    private Integer yn;

    private Integer version;

}