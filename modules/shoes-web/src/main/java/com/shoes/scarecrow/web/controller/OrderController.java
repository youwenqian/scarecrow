package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Order;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-28
 * Time 上午12:43
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    private static Logger log = Logger.getLogger(OrderController.class);

    @RequestMapping("/addOrder")
    @ResponseBody
    public Map addOrder(Order order, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info(session.getAttribute("userName")+"进入到增加订单方法,订单详情:"+mapper.writeValueAsString(order));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            log.info(session.getAttribute("userName")+"离开到增加订单方法,订单详情:"+mapper.writeValueAsString(order));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("success",true);
        return map;
    }
    @RequestMapping("/getOrder")
    @ResponseBody
    public Map getOrder(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        log.info(session.getAttribute("userName")+"进入到查询订单方法,用户id="+session.getAttribute("userId"));

        log.info(session.getAttribute("userName")+"离开到查询订单方法,用户id="+session.getAttribute("userId"));
        map.put("success",true);
        return map;
    }
}
