package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.web.service.IndexService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author youwenqian
 * @description
 * @time 2018/3/8 14:22
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private static Logger log = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private IndexService indexService;
    @RequestMapping("/login")
    public void login(String userName,String passWord,HttpServletResponse response){
        log.info("username="+userName+",password="+passWord);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",true);
        map.put("message","登录成功");
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求index/login.do 写返回数据时发生错误。"+e.getMessage());
        }
//        boolean checkUserFlag = indexService.login(userName,passWord);
//        return "true";//+checkUserFlag;
    }
    @RequestMapping("/register")
    public String register(){
        log.info("这是你要的物质生活");
        boolean flag = false;
//        flag = indexService.register();
        return "register";//+flag;
    }
}
