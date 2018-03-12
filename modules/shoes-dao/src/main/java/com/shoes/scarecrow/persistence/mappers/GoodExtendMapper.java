package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.GoodExtend;

public interface GoodExtendMapper {
    int insert(GoodExtend record);

    int insertSelective(GoodExtend record);
}