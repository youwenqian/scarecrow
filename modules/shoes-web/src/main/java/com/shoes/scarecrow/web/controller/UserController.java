package com.shoes.scarecrow.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangyucheng
 * @description
 * @create 2018/2/12 11:52
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class);
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        log.info("xiaotong");
        System.out.println("说不上爱就别说谎，就一点喜欢！");
        return "test";
    }

}