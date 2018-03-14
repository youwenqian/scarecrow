package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.GoodsCondition;
import com.shoes.scarecrow.persistence.domain.PayMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class GoodsServiceTest extends BaseTest {

    @Autowired
    private GoodsService goodsService;


    @Test
    public void saveGoods() throws Exception {
        Goods  goods = new Goods();
        goods.setUserId(1);
        goods.setCreateTime(new Date());
        goods.setBrandId(1);
        goods.setGoodsClass(1);
        goods.setGoodsDesc("goodsDesc");
        goods.setKeyword("keyword");
        goods.setPrice(98.099);
        goods.setRemark("remark");
        goods.setSex(0);
        goods.setStatus(0);
        goods.setVersion(0);
        goods.setCreateUser("me");
        goods.setGoodsName("商品1");
        goods.setYn(0);
        int count = goodsService.saveGoods(goods);
        System.out.println(count);
    }

    @Test
    public void queryBrandByCondition() throws Exception {
        GoodsCondition condition = new GoodsCondition();
        condition.setBrandId(1);
        condition.setGoodsClass(1);
        condition.setKeyword("keyword");
        condition.setSex(0);
        condition.setStatus(0);
        condition.setGoodsName("%商品%");
        List<Goods> goodsList = goodsService.queryByCondition(condition);
        for(Goods goods : goodsList){
            System.out.println(goods.toString());
        }
    }

    @Test
    public void queryCountByCondition() throws Exception {
        GoodsCondition condition = new GoodsCondition();
        condition.setBrandId(1);
        condition.setGoodsClass(1);
        condition.setKeyword("keyword");
        condition.setSex(0);
        condition.setStatus(0);
        condition.setGoodsName("%商品%");
        int count = goodsService.queryCountByCondition(condition);
        System.out.println(count);
    }

    @Test
    public void queryById() throws Exception {
    }

    @Test
    public void queryByName() throws Exception {
    }

    @Test
    public void updateGoods() throws Exception {
    }

    @Test
    public void delBrand() throws Exception {
    }
}