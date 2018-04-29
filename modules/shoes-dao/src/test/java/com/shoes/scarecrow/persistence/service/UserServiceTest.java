package com.shoes.scarecrow.persistence.service;

import com.shoes.scarecrow.persistence.BaseTest;
import com.shoes.scarecrow.persistence.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class UserServiceTest extends BaseTest{

    @Autowired
    private UserService userService;

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setUserName("scarecrow");
        user.setAddress("address");
        user.setBirthday(new Date());
        int count = userService.saveUser(user);
        System.out.println("数量count:"+ count);
    }

}