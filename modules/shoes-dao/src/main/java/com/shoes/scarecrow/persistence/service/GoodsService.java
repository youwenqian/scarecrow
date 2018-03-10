package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.mappers.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}