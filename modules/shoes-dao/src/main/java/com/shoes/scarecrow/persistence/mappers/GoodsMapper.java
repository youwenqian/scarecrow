package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Goods;

public interface GoodsMapper {
    int insert(Goods record);

    int insertSelective(Goods record);
}