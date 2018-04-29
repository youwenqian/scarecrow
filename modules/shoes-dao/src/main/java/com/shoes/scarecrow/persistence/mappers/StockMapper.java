package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Stock;

public interface StockMapper {
    int insert(Stock record);

    int insertSelective(Stock record);
}