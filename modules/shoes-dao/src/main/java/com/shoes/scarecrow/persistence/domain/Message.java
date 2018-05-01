package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Integer id;

    private String title;

    private String auther;

    private String keyword;

    private String summary;

    private String picture;

    private Integer status;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private String content;
}