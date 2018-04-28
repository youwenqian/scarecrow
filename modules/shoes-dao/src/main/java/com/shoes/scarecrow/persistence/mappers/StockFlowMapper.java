package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.StockFlow;
import org.springframework.stereotype.Repository;

@Repository("stockFlowMapper")
public interface StockFlowMapper {
    int insert(StockFlow record);

    int insertSelective(StockFlow record);
}