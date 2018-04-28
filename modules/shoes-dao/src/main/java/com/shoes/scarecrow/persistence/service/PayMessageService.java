package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.PayMessage;
import com.shoes.scarecrow.persistence.mappers.PayMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:52
 */
@Service
public class PayMessageService {

    @Autowired
    protected PayMessageMapper payMessageMapper;

    public int savePayMessage(PayMessage payMessage){
        return payMessageMapper.insert(payMessage);
    }

}