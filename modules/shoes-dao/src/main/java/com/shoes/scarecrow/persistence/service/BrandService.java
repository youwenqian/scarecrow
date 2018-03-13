package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.BrandCondition;
import com.shoes.scarecrow.persistence.mappers.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/10 18:45
 */
@Service
public class BrandService {

    @Autowired
    protected BrandMapper brandMapper;

    public int saveBrand(Brand brand){
        return brandMapper.insert(brand);
    }

    public List<Brand> queryBrandByCondition(BrandCondition condition){
        return brandMapper.queryByCondition(condition);
    }

    public int queryCountByCondition(BrandCondition condition){
        return brandMapper.queryCountByCondition(condition);
    }

    public int updateBrand(int id){
        return brandMapper.updateBrand(id);
    }

    public int delBrand(int id){
        return brandMapper.delBrand(id);
    }

}