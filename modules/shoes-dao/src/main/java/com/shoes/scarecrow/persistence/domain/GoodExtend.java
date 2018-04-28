package com.shoes.scarecrow.persistence.domain;

import lombok.Data;

@Data
public class GoodExtend {
    private Integer extendId;

    private Integer goodId;

    private Integer colorId;

    private Integer sizeId;

    private String remarks;

}