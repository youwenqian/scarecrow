package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.PayMessage;

public interface PayMessageMapper {
    int insert(PayMessage record);

    int insertSelective(PayMessage record);
}