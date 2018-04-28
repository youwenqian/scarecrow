package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.GoodsCondition;
import com.shoes.scarecrow.persistence.mappers.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:49
 */
@Service
public class GoodsService {

    @Autowired
    protected GoodsMapper goodsMapper;

    public int saveGoods(Goods goods){
        return goodsMapper.insert(goods);
    }

    public int saveGoods(Goods goods, Integer goodsSize){
        return goodsMapper.insert(goods);
    }

    public List<Goods> queryByCondition(GoodsCondition condition){
        return goodsMapper.queryByCondition(condition);
    }

    public int queryCountByCondition(GoodsCondition condition){
        return goodsMapper.queryCountByCondition(condition);
    }

    public Goods queryById(int id){
        return goodsMapper.queryById(id);
    }

    public int updateGoods(Goods record){
        return goodsMapper.update(record);
    }

    public int delBrand(int id){
        return goodsMapper.delById(id);
    }

}