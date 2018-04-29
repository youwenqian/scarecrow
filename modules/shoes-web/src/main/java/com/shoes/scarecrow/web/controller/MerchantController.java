package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.User;
import com.shoes.scarecrow.persistence.domain.UserCondition;
import com.shoes.scarecrow.persistence.service.UserService;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-4
 * Time 上午1:54
 */
@Controller
public class MerchantController {
    Logger log = Logger.getLogger(MerchantController.class);
    @Autowired
    private UserService userService;
    @RequestMapping("/merchant/{path}")
    public String dispathchPath(@PathVariable("path") String path){
        log.info("跳转到"+path);
        return path;
    }
    @RequestMapping("/merchant/allDetail")
    @ResponseBody
    public Map getDetail(int page, int limit, UserCondition userCondition, HttpSession session) {
        log.info(session.getAttribute("userName") + "进入到分页获取商家信息的方法，limit=" + limit + ",page=" + page);
        Map<String,Object> map = new HashMap<>();
        userCondition.setPage(null);
        userCondition.setStartRow(null);
        userCondition.setPageSize(null);
        int total = userService.queryCountByCondition(userCondition);
        userCondition.setPage(page);
        userCondition.setPageSize(limit);
        int start = (page-1)*limit;
        userCondition.setStartRow(start);
        List<User> list = userService.queryByCondition(userCondition);
        ObjectMapper mapper = new ObjectMapper();
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
        map.put("count",total);
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取商家信息的方法，result="+mapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("/merchant/deleteUser/{id}")
    @ResponseBody
    public Map deleteGoodsById(@PathVariable("id") Integer id,HttpServletResponse response,HttpSession session){
        log.info(session.getAttribute("userName") + "进入到删除商家信息的方法，删除商家id="+id);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    @RequestMapping("/merchant/updateUser")
    @ResponseBody
    public Map putGoods(User user,String goodsSize,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper objMapper = new ObjectMapper();
        try {
            log.info(session.getAttribute("userName")+"进入修改商品信息方法,修改的商品信息="+objMapper.writeValueAsString(user)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("success",true);
        try {
            log.info(session.getAttribute("userName")+"离开修改商品信息方法,修改的商品信息="+objMapper.writeValueAsString(user)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map ;
    }
    @RequestMapping("/merchant/addUser")
    @ResponseBody
    public Map postGoods(User user,String goodsSize,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Integer goodSize = Integer.valueOf(goodsSize);
        ObjectMapper objMapper = new ObjectMapper();
        try {
            log.info(session.getAttribute("userName")+"进入添加商品信息方法,修改的商品信息="+objMapper.writeValueAsString(user)+" goodsSize="+goodSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.saveUser(user);
        map.put("success",true);
        try {
            log.info(session.getAttribute("userName")+"离开添加商品信息方法,修改的商品信息="+objMapper.writeValueAsString(user)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map ;
    }
    @RequestMapping("/merchant/getUser")
    @ResponseBody
    public Map getGoods(int page, int limit, Goods goods,String goodsSize,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper objMapper = new ObjectMapper();
        try {
            log.info(session.getAttribute("userName")+"进入查找商品信息方法,查找的商品信息="+objMapper.writeValueAsString(goods)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("id",i);
            map1.put("userId",i);
            map1.put("price",987.98d);
            map1.put("keyword","关键字");
            map1.put("goodsType","商品分类");
            map1.put("goodsName","商品"+i);
            map1.put("goodsBrand","品牌"+i);
            map1.put("goodsSize",i+"码");
            map1.put("goodsColor","颜色");
            map1.put("sex","男鞋");map1.put("goodsColor","颜色");
            map1.put("createTime",new Date());
            map1.put("createUser","创建人");
            map1.put("updateTime",new Date());
            map1.put("updateUser","更新人");
            map1.put("remark","颜色很好看");
            list.add(map1);
        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
        int start = (page-1)*limit;
        int end = page*limit<=list.size()?page*limit:list.size();
        List<Map<String,Object>> list2 = list.subList(start,end);
        map.put("data",list2);
        try {
            log.info(session.getAttribute("userName")+"离开查找商品信息方法,修改的商品信息="+objMapper.writeValueAsString(goods));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map ;
    }
}
