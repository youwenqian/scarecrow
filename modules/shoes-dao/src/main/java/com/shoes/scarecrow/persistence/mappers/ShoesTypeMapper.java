package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.ShoesType;
import com.shoes.scarecrow.persistence.domain.TypeCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("shoesTypeMapper")
public interface ShoesTypeMapper {
    int insert(ShoesType record);

    List<ShoesType> queryByCondition(TypeCondition condition);

    int queryCountByCondition(TypeCondition condition);

    ShoesType queryById(int id);

    ShoesType queryByName(String name);

    int update(ShoesType record);

    int delById(int id);
}