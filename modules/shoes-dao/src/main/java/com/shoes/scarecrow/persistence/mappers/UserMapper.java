package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.domain.UserCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    List<User> queryListByCondition(Map<String, Object> param);

    int getCountByCondition(Map<String, Object> param);

    User getUserByName(@Param("userName") String name, @Param("password") String password);

    User getUserById(@Param("id") Long id);

    int update(User user);

    int updatePass(@Param("id") Long id, @Param("password") String password,@Param("updateUser") String name);

    int del(@Param("ids") List<Long> ids);
}