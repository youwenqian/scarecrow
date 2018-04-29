package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.BrandCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("brandMapper")
public interface BrandMapper {
    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> queryByCondition(BrandCondition condition);

    int queryCountByCondition(BrandCondition condition);

    Brand queryById(int id);

    Brand queryByName(@Param("name") String name);

    int update(Brand record);

    int delById(int id);
}