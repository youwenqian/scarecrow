package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PayMessage {
    private Integer id;

    private Integer userId;

    private Date payTime;

    private Double payFee;

    private String remark;

    private Date createTime;

    private String createUser;

    private Integer status;

    private Integer yn;

}