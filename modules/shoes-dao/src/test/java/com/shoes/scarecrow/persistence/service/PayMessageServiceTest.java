package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.PayMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class PayMessageServiceTest extends BaseTest {

    @Autowired
    private PayMessageService payMessageService;


    @Test
    public void savePayMessage() throws Exception {
        PayMessage payMessage = new PayMessage();
        payMessage.setCreateTime(new Date());
        payMessage.setCreateUser("me");
        payMessage.setPayFee(136.987);
        int count = payMessageService.savePayMessage(payMessage);
        System.out.println(count);
    }

}