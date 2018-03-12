package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Size;

public interface SizeMapper {
    int insert(Size record);

    int insertSelective(Size record);
}