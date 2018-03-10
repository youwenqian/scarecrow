package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Stock;
import com.shoes.scarecrow.persistence.mappers.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:50
 */
@Service
public class StockService {

    @Autowired
    protected StockMapper stockMapper;

    public int saveStock(Stock stock){
        return stockMapper.insert(stock);
    }


}