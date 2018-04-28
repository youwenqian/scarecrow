package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.StockFlow;
import com.shoes.scarecrow.persistence.mappers.StockFlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:51
 */
@Service
public class StockFlowService {

    @Autowired
    protected StockFlowMapper stockFlowMapper;

    public int saveStockFlow(StockFlow stockFlow){
        return stockFlowMapper.insert(stockFlow);
    }



}