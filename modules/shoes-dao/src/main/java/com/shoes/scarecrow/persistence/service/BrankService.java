package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Brank;
import com.shoes.scarecrow.persistence.mappers.BrankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:45
 */
@Service
public class BrankService {

    @Autowired
    protected BrankMapper brankMapper;

    public int saveBrank(Brank brank){
        return brankMapper.insert(brank);
    }


}