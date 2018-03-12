package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.Brank;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class BrankServiceTest extends BaseTest {

    @Autowired
    private BrankService brankService;


    @Test
    public void saveBrank() throws Exception {
        Brank brank = new Brank();
        brank.setCreateTime(new Date());
        brank.setCreateUser("me");
        brank.setName("品牌3");
        brank.setYn(0);
        int count = brankService.saveBrank(brank);
        System.out.println(count);
    }

}