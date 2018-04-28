package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.ShoesType;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Create by zz
 * Date 18-4-25
 * Time 上午1:35
 */
@Controller
@RequestMapping("/type")
public class TypeController {
    private Logger log = Logger.getLogger(TypeController.class);

    @RequestMapping("/allDetail")
    public void getAllTypeDetailByPage(int page, int limit, HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取分类信息的方法");
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        List<ShoesType> list = new ArrayList<ShoesType>();
        for(int i=0;i<5;i++){
            ShoesType type = new ShoesType();
            type.setId(i);
            type.setTypeName("分类"+i);
            list.add(type);
        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
        int start = (page-1)*limit;
        int end = page*limit<=list.size()?page*limit:list.size();
        List<ShoesType> list2 = new ArrayList<ShoesType>();
        map.put("data",list2);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取分类信息的方法，result="+mapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求type/allDetail.do 写返回数据时发生错误。"+e.getMessage());
        }
    }
    @RequestMapping("/shortType")
    public void getAllTypeDetail(HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取所有分类信息的方法");
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        List<ShoesType> list = new ArrayList<ShoesType>();
        for(int i=0;i<5;i++){
            ShoesType type = new ShoesType();
            type.setId(i);
            type.setTypeName("分类"+i);
            list.add(type);
        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取所有分类信息的方法，result="+mapper.writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String retStr = objectMapper.writeValueAsString(map);
            response.setCharacterEncoding("utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(retStr);
        }catch (IOException e){
            log.error("请求type/allDetail.do 写返回数据时发生错误。"+e.getMessage());
        }
    }
}
