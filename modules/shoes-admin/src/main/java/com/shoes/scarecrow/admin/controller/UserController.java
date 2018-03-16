package com.shoes.scarecrow.admin.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyucheng
 * @description
 * @create 2018/3/12 20:55
 */
@Controller
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String name, String password, HttpServletRequest request, HttpServletResponse response){
        long tid = System.nanoTime();
        LOGGER.info("tid:{} 登录 name:{},password:{} ", tid, name, password);
        return "index";
    }

}