package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User getUserByNameAndPassword(@Param("userName") String userName, @Param("passWord") String passWord);
}