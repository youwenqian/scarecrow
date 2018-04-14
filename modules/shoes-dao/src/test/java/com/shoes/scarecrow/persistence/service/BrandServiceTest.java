package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.BrandCondition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class BrandServiceTest extends BaseTest {


    @Autowired
    private BrandService brandService;


    @Test
    public void saveBrank() throws Exception {
        Brand brand = new Brand();
        brand.setCreateTime(new Date());
        brand.setCreateUser("me");
        brand.setName("品牌4");
        brand.setYn(0);
        int count = brandService.saveBrand(brand);
        System.out.println(count);
    }

    @Test
    public void queryByCondition() throws Exception {
        BrandCondition condition = new BrandCondition();
        condition.setName("%品牌%");
        condition.setPageSize(2);
        List<Brand>  brands = brandService.queryByCondition(condition);
        for(Brand brand : brands){
            System.out.println(brand.toString());
        }
    }

    @Test
    public void queryCountByCondition() throws Exception {
        BrandCondition condition = new BrandCondition();
        condition.setName("%品牌%");
        int count = brandService.queryCountByCondition(condition);
        System.out.println(count);
    }

    @Test
    public void updateBrand() throws Exception {
        Brand brand = brandService.queryById(3);
        if(brand == null) return;
        brand.setUpdateTime(new Date());
        brand.setUpdateUser("阿飞");
        brand.setRemark("remarkupdate");
        brand.setName("耐克");
        brand.setStatus(0);
        int count = brandService.updateBrand(brand);
        System.out.println(count);
    }

    @Test
    public void queryByName() throws Exception {
        Brand brand =brandService.queryByName("耐克");
        System.out.println(brand.toString());
    }

    @Test
    public void delBrand() throws Exception {
//        int count = brandService.delBrand(3);
//        System.out.println(count);

    }

}