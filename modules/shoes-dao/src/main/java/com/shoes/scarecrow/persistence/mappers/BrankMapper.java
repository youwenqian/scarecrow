package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Brank;

public interface BrankMapper {
    int insert(Brank record);

    int insertSelective(Brank record);
}