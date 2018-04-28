package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author wangyucheng
 * @description
 * @create 2018/4/2 20:47
 */
@Data
public class UserCondition extends BaseCondition implements Serializable {

    private static final long serialVersionUID = -3743102381622618561L;

    private String  nickName;

    private Date startTime;

    private Date endTime;

    private Integer isPayment;

    private Integer status;

    private String userName;

    private String phoneNo;

    private Integer userType;

    private String taobaoName;

    private Integer sex;

    private Integer id;
}