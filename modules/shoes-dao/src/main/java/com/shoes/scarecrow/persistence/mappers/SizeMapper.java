package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Size;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SizeMapper {

    int insert(Size size);

    List<Size> queryByCondition(Map<String, Object> param);

    int queryCountByCondition(Map<String, Object> param);

    Size queryById(int id);

    int update(Size size);

    int delById(@Param("ids") List<Integer> ids);

    Size queryByName(@Param("name") String name);
}
