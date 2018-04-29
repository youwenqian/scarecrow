package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.common.enums.UserType;
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

    public int updatePass(Long id, String password, String userName){
        return userMapper.updatePass(id, password, userName);
    }

    public List<User> queryListByCondition(Map<String, Object> param){
        return userMapper.queryListByCondition(param);
    }
    public int getCountByCondition(Map<String, Object> param){
        return userMapper.getCountByCondition(param);
    }

    public User getUser(String name, String password){
        return userMapper.getUserByName(name, password, UserType.BUSINESS.getKey());
    }
    public User getUserByName(String name, String password, int type){
        return userMapper.getUserByName(name, password, type);
    }


    public User getUserById(Long id){
        return userMapper.getUserById(id);
    }

    public int delUser(List<Long> ids){
        return userMapper.del(ids);
    }

}