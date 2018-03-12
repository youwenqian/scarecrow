package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.PayMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class GoodsServiceTest extends BaseTest {

    @Autowired
    private GoodsService goodsService;


    @Test
    public void saveGoods() throws Exception {
        Goods  goods = new Goods();
        goods.setCreateTime(new Date());
        goods.setCreateUser("swift");
        goods.setGoodsName("商品3");
        goods.setYn(0);
        int count = goodsService.saveGoods(goods);
        System.out.println(count);
    }
}