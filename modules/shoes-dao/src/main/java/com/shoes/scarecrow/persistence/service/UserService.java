package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangyucheng
 * @description
 * @create 2018/2/12 10:17
 */
@Service
public class UserService {

    @Autowired
    protected UserMapper userMapper;


    public int saveUser(User user){
        return userMapper.insert(user);
    }



}