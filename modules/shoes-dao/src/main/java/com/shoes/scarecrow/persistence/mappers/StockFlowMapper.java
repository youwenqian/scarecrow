package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.StockFlow;

public interface StockFlowMapper {
    int insert(StockFlow record);

    int insertSelective(StockFlow record);
}