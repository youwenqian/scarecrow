package com.shoes.scarecrow.persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wangyucheng
 * @description
 * @create 2018/2/12 10:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/jdbc.properties")
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext*.xml"})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Configuration
    @ComponentScan(basePackages = {"com.shoes.scarecrow"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
    static class ComponentScanConfig {

    }

    public void assertNotNull(Object obj) {
        System.out.println(obj);
        Assert.assertNotNull(obj);
    }


    @Before
    public void before(){
        System.out.println("测试开始...");
    }
    @After
    public void after(){
        System.out.println("测试结束...");
    }
}