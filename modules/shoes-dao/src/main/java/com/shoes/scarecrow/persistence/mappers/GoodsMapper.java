package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.GoodsCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("goodsMapper")
public interface GoodsMapper {
    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> queryByCondition(GoodsCondition condition);

    int queryCountByCondition(GoodsCondition condition);

    Goods queryById(int id);

    int update(Goods record);

    int delById(int id);
}