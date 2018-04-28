package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author li
 * @description
 * @time 2018/4/28 14:16
 */
@Data
public class SizeCondition extends BaseCondition implements Serializable{
    private static final long serialVersionUID = -6036660988814972212L;
    private Integer id;
    private String sizeName;

    private Integer page;

    private Integer pageSize;

    private Integer startRow;
}
