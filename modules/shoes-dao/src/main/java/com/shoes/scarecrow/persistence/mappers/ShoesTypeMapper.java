package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.ShoesType;

public interface ShoesTypeMapper {
    int insert(ShoesType record);

    int insertSelective(ShoesType record);
}