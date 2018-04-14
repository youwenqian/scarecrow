package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        if(user == null) return 0;
        if(user.getCreateTime() == null) user.setCreateTime(new Date());
        if(user.getUpdateTime() == null) user.setUpdateTime(new Date());
        return userMapper.insert(user);
    }

    public int updateUser(User user){
        if(user == null) return  0;
        user.setUpdateTime(new Date());
        return userMapper.update(user);
    }

    public List<User> queryListByCondition(Map<String, Object> param){
        return userMapper.queryListByCondition(param);
    }
    public int getCountByCondition(Map<String, Object> param){
        return userMapper.getCountByCondition(param);
    }

    public User getUserByName(String name, String password){
        return userMapper.getUserByName(name, password);
    }

    public User getUserById(Long id){
        return userMapper.getUserById(id);
    }

    public int delUser(List<Long> ids){
        return userMapper.del(ids);
    }

}