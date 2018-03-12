package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Color;

public interface ColorMapper {
    int insert(Color record);

    int insertSelective(Color record);
}