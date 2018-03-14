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
        if(brand == null) return 0;
        if(brandMapper.queryByName(brand.getName()) != null)
            return 0;
        return brandMapper.insert(brand);
    }

    public List<Brand> queryByCondition(BrandCondition condition){
        return brandMapper.queryByCondition(condition);
    }

    public int queryCountByCondition(BrandCondition condition){
        return brandMapper.queryCountByCondition(condition);
    }

    public Brand queryById(int id){
        return brandMapper.queryById(id);
    }

    public Brand queryByName(String name){
        return brandMapper.queryByName(name);
    }

    public int updateBrand(Brand brand){
        Brand oriBrand = brandMapper.queryByName(brand.getName());
        if(oriBrand == null || oriBrand.getId() == brand.getId());
            return brandMapper.update(brand);
    }

    public int delBrand(int id){
        return brandMapper.delById(id);
    }

}