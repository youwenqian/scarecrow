package com.shoes.scarecrow.web.controller;

import com.shoes.scarecrow.persistence.domain.ShoesType;
import com.shoes.scarecrow.persistence.domain.TypeCondition;
import com.shoes.scarecrow.persistence.service.TypeService;
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
 * Date 18-4-25
 * Time 上午1:35
 */
@Controller
@RequestMapping("/type")
public class TypeController {
    private Logger log = Logger.getLogger(TypeController.class);
    @Autowired
    private TypeService typeService;
    @RequestMapping("/allDetail")
    public void getAllTypeDetailByPage(int page, int limit,String typeName, HttpSession session, HttpServletResponse response){
        log.info(session.getAttribute("userName")+"进入到分页获取分组信息的方法，limit="+limit+",page="+page+",name="+typeName);
        Map<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeCondition condition = new TypeCondition();
        condition.setTypeName(typeName);
        condition.setPage(null);
        condition.setPageSize(null);
        condition.setStartRow(null);
        int total = typeService.queryCountByCondition(condition);
        condition.setPage(page);
        condition.setPageSize(limit);
        int start = (page-1)*limit;
        condition.setStartRow(start);
        List<ShoesType> list = typeService.queryByCondition(condition);
//        list = new ArrayList<>();
//        for(int i=0;i<50;i++){
//            Type type = new Type();
//            type.setId(i);
//            type.setName("分组"+i);
//            type.setCreateUser("创建人"+i);
//            type.setCreateTime(new Date());
//            type.setUpdateUser("更新人"+i);
//            type.setUpdateTime(new Date());
//            type.setRemark("备注"+i);
//            list.add(type);
//        }
        map.put("code","0");
        map.put("msg","");
        map.put("count",50);
//        start = (page-1)*limit;
//        int end = page*limit<=list.type()?page*limit:list.type();
//        List<Type> list2 = list.subList(start,end);
        map.put("data",list);
        try {
            log.info(session.getAttribute("userName")+"退出分页获取分组信息的方法，result="+mapper.writeValueAsString(map));
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
        TypeCondition condition = new TypeCondition();
        condition.setTypeName(null);
        condition.setPage(null);
        condition.setPageSize(null);
        condition.setStartRow(null);
        ObjectMapper mapper = new ObjectMapper();
        List<ShoesType> list = typeService.queryByCondition(condition);
        map.put("code","0");
        map.put("msg","");
        map.put("count",list.size());
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
    @RequestMapping("/addType")
    @ResponseBody
    public Map addType(HttpSession session,ShoesType type){
        ObjectMapper mapper = new ObjectMapper();
        try{
            log.info(session.getAttribute("userName")+"进入添加分组方法"+mapper.writeValueAsString(type));
        }catch(IOException e ){
            e.printStackTrace();
        }
        int n = typeService.saveType(type);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开添加分组方法，添加结果影响行数:"+n);
        return map;
    }

    @RequestMapping("/updateType")
    @ResponseBody
    public Map updateType(ShoesType type,HttpSession session){
        ObjectMapper mapper = new ObjectMapper();
        try{
            log.info(session.getAttribute("userName")+"进入修改分组方法"+mapper.writeValueAsString(type));
        }catch(IOException e ){
            e.printStackTrace();
        }
        int n = typeService.updateType(type);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开修改分组方法，结果影响行数:"+n);
        return map;
    }
    @RequestMapping("/deleteType/{id}")
    @ResponseBody
    public Map deleteType(@PathVariable("id") Integer id, HttpSession session){
        log.info(session.getAttribute("userName")+"进入删除分组方法，删除分组id="+id);
        int n = typeService.delType(id);
        Map map = new HashMap<String,Object>();
        if(n==0){
            map.put("success",false);
        }else{
            map.put("success",true);
        }
        log.info(session.getAttribute("userName")+"离开删除分组方法，结果影响行数:"+n);
        return map;
    }
}
