package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String userName;

    private String nickName;

    private Date birthday;

    private Integer isPayment;

    private Byte sex;

    private Integer userType;

    private String phoneNo;

    private String address;

    private Integer stockId;

    private String taobaoName;

    private String imageAddress;

    private String password;

    private String confirmPassword;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String remark;

    private Byte yn;

    private Integer status;
}