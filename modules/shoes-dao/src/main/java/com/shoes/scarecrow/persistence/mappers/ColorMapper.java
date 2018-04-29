package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Color;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ColorMapper {

    int insert(Color color);

    List<Color> queryByCondition(Map<String, Object> param);

    int queryCountByCondition(Map<String, Object> param);

    Color queryById(int id);

    int update(Color color);

    int delById(@Param("ids") List<Integer> ids);

    Color queryByName(@Param("name") String name);
}
