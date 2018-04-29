package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.Stock;
import com.shoes.scarecrow.persistence.domain.StockFlow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class StockFlowServiceTest extends BaseTest {


    @Autowired
    protected StockFlowService stockFlowService;


    @Test
    public void saveStockFlow() throws Exception {
        StockFlow stockFlow = new StockFlow();
        stockFlow.setBatchNo("123456");
        stockFlow.setCreateTime(new Date());
        stockFlow.setStockId(1);
        stockFlow.setPrice(165.9999);
        int count = stockFlowService.saveStockFlow(stockFlow);
        System.out.println(count);
    }

}