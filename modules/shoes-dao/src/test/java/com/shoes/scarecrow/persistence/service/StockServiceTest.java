package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.Stock;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class StockServiceTest extends BaseTest {

    @Autowired
    protected StockService stockService;


    @Test
    public void saveStock() throws Exception {
        Stock stock = new Stock();
        stock.setBatchNo("123456");
        stock.setCreateTime(new Date());
        stock.setIntoPrice(1652.3333);
        int count = stockService.saveStock(stock);
        System.out.println(count);
        System.out.println( stock.toString());
    }

}