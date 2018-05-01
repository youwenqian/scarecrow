package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.Message;

import java.util.List;
import java.util.Map;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectByMap(Map<String, Object> params);

    int selectCountByMap(Map<String, Object> params);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}