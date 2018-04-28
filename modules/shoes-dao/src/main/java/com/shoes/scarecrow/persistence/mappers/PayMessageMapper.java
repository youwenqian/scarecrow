package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.PayMessage;
import org.springframework.stereotype.Repository;

@Repository("payMessageMapper")
public interface PayMessageMapper {
    int insert(PayMessage record);

    int insertSelective(PayMessage record);
}