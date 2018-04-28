package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Size;
import com.shoes.scarecrow.persistence.domain.SizeCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sizeMapper")
public interface SizeMapper {
    int insert(Size record);

    List<Size> queryByCondition(SizeCondition condition);

    int queryCountByCondition(SizeCondition condition);

    Size queryById(int id);

    Size queryByName(String name);

    int update(Size record);

    int delById(int id);
}