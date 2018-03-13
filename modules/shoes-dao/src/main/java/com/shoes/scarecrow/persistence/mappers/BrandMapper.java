package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.BrandCondition;

import java.util.List;

public interface BrandMapper {
    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> queryByCondition(BrandCondition condition);

    int queryCountByCondition(BrandCondition condition);

    int updateBrand(int id);

    int delBrand(int id);
}