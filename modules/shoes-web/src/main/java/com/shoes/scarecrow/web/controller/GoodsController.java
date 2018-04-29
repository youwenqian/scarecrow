package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.Brand;
import com.shoes.scarecrow.persistence.domain.Goods;
import com.shoes.scarecrow.persistence.domain.GoodsCondition;
import com.shoes.scarecrow.persistence.service.GoodsService;
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
import java.io.PrintWriter;
import java.util.*;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-24
 * Time 下午9:32
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    private static Logger log = Logger.getLogger(BrandController.class);

    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/allDetail")
    @ResponseBody
    public Map<String, Object> getBrandDetailByPage(int page, int limit, HttpSession session, HttpServletResponse response) {
        log.info(session.getAttribute("userName") + "进入到分页获取商品信息的方法，limit=" + limit + ",page=" + page);
        Map<String,Object> map = new HashMap<>();
        GoodsCondition goodsCondition = new GoodsCondition();
        Integer userId = Integer.valueOf(String.valueOf(session.getAttribute("userId")));
        goodsCondition.setUserId(userId);
        goodsCondition.setStatus(1);
        goodsCondition.setPage(null);
        goodsCondition.setStartRow(null);
        goodsCondition.setPageSize(null);
        int total = goodsService.queryCountByCondition(goodsCondition);
        goodsCondition.setPage(page);
        goodsCondition.setPageSize(limit);
        int start = (page-1)*limit;
        goodsCondition.setStartRow(start);
//        List<Goods> list = goodsService.queryByCondition(goodsCondition);
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<50;i++){
            Map<String,Object> map1 = new HashMap<String,Object>();
            map1.put("id",i);
            map1.put("userId",i);
            map1.put("price",987.98d);
            map1.put("keyword","关键字");
            map1.put("goodsClass",i);
            map1.put("goodsName","商品"+i);
            map1.put("brandId",i);
            map1.put("goodsSize",i);
            map1.put("goodsColor","颜色");
            map1.put("sex","男鞋");map1.put("goodsColor","颜色");
            map1.put("createTime",new Date());
            map1.put("createUser","创建人");
            map1.put("updateTime",new Date());
            map1.put("updateUser","更新人");
            map1.put("remark","颜色很好看");
            list.add(map1);
        }
        int end = page*limit<=list.size()?page*limit:list.size();
        List<Map<String,Object>> list2 = list.subList(start,end);
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
        map.put("count",total);
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取商品信息的方法，result="+mapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求goods/allDetail.do。"+e.getMessage());
        }*/
        return map;
    }
    @RequestMapping("/deleteType/{id}")
    @ResponseBody
    public Map deleteGoodsById(@PathVariable("id") Integer goodId,HttpServletResponse response,HttpSession session){
        log.info(session.getAttribute("userName") + "进入到删除商品信息的方法，删除商品id="+goodId);
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
    @RequestMapping("/updateGoods")
    @ResponseBody
    public Map putGoods(Goods goods,String goodsSize,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ObjectMapper objMapper = new ObjectMapper();
        try {
            log.info(session.getAttribute("userName")+"进入修改商品信息方法,修改的商品信息="+objMapper.writeValueAsString(goods)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("success",true);
        try {
            log.info(session.getAttribute("userName")+"离开修改商品信息方法,修改的商品信息="+objMapper.writeValueAsString(goods)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map ;
    }
    @RequestMapping("/addGoods")
    @ResponseBody
    public Map postGoods(Goods goods,String goodsSize,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Integer goodSize = Integer.valueOf(goodsSize);
        ObjectMapper objMapper = new ObjectMapper();
        try {
            log.info(session.getAttribute("userName")+"进入添加商品信息方法,修改的商品信息="+objMapper.writeValueAsString(goods)+" goodsSize="+goodSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        goodsService.saveGoods(goods,goodSize);
        map.put("success",true);
        try {
            log.info(session.getAttribute("userName")+"离开添加商品信息方法,修改的商品信息="+objMapper.writeValueAsString(goods)+" goodsSize="+goodsSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map ;
    }
    @RequestMapping("/getGoods")
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
