package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/13 21:02
 */
@Data
public class BaseCondition implements Serializable {

    private static final long serialVersionUID = -6036660988814972212L;

    private Integer page = 1;

    private Integer pageSize = 10;

    private Integer startRow = 0;
}