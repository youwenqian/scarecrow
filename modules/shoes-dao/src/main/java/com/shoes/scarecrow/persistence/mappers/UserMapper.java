package com.shoes.scarecrow.persistence.mappers;

import com.shoes.scarecrow.persistence.domain.User;

public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);
}